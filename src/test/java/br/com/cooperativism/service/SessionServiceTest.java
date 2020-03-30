package br.com.cooperativism.service;

import br.com.cooperativism.converter.SessionConverter;
import br.com.cooperativism.exception.BusinessException;
import br.com.cooperativism.exception.NotFoundException;
import br.com.cooperativism.helper.ApiHelper;
import br.com.cooperativism.mock.session.SessionMock;
import br.com.cooperativism.mock.session.SessionRequestMock;
import br.com.cooperativism.mock.topic.TopicMock;
import br.com.cooperativism.model.Session;
import br.com.cooperativism.repository.SessionRepository;
import br.com.cooperativism.request.SessionRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SessionServiceTest {

  @InjectMocks
  private SessionService sessionService;

  @Mock
  private SessionRepository sessionRepository;

  @Mock
  private SessionConverter sessionConverter;

  @Mock
  private TopicService topicService;

  private Integer duration = 1;
  private String topicName = "Test";
  private Session session;

  @BeforeEach
  public void beforeEach() {
    session = SessionMock.getRandom(duration, topicName);

    when(sessionRepository.findFirstByTopicId(anyLong()))
        .thenReturn(Optional.of(session));
  }

  @Test
  public void shouldCreate() {
    when(topicService.findById(anyLong()))
        .thenReturn(TopicMock.getRandom(topicName));

    ArgumentCaptor<Session> captor = ArgumentCaptor.forClass(Session.class);
    duration = 5;
    SessionRequest sessionRequest = SessionRequestMock.getRandom(duration);
    LocalDateTime votingEnd = ApiHelper.getNow().plusMinutes(duration);
    sessionService.create(sessionRequest);

    verify(sessionRepository, times(1)).save(captor.capture());

    Session sessionToSave = captor.getValue();

    assertNotNull(sessionToSave);
    assertNotNull(sessionToSave.getTopic());
    assertEquals(votingEnd, sessionToSave.getVotingEnd());
  }

  @Test
  public void throwErrorIfSessionIsExpired() {
    session.setVotingEnd(ApiHelper.getNow().minusMinutes(10));

    SessionRequest sessionRequest = SessionRequestMock.getRandom(duration);

    assertThrows(BusinessException.class, () -> sessionService.create(sessionRequest));
  }

  @Test
  public void throwErrorIfSessionNotFound() {
    Session session = SessionMock.getRandom(duration, topicName);
    session.setVotingEnd(ApiHelper.getNow().minusMinutes(10));

    when(sessionRepository.findFirstByTopicId(anyLong()))
        .thenReturn(Optional.empty());

    assertThrows(NotFoundException.class, () -> sessionService.findValidByTopicId(1L));
  }

}
