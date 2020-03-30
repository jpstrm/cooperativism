package br.com.cooperativism.service;

import br.com.cooperativism.base.AbstractUnitTest;
import br.com.cooperativism.converter.SessionConverter;
import br.com.cooperativism.helper.ApiHelper;
import br.com.cooperativism.mock.SessionMock;
import br.com.cooperativism.mock.SessionRequestMock;
import br.com.cooperativism.mock.TopicMock;
import br.com.cooperativism.model.Session;
import br.com.cooperativism.repository.SessionRepository;
import br.com.cooperativism.request.SessionRequest;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class SessionServiceTest extends AbstractUnitTest {

  @InjectMocks
  private SessionService sessionService;

  @Mock
  private SessionRepository sessionRepository;

  @Mock
  private SessionConverter sessionConverter;

  @Mock
  private TopicService topicService;

  @Test
  void create() {
    ArgumentCaptor<Session> captor = ArgumentCaptor.forClass(Session.class);
    Integer duration = 5;
    String topicName = "Test";
    Session session = SessionMock.getRandom(duration, topicName);

    when(sessionRepository.findFirstByTopicId(anyLong()))
        .thenReturn(Optional.of(session));
    when(topicService.findById(anyLong()))
        .thenReturn(TopicMock.getRandom(topicName));

    SessionRequest sessionRequest = SessionRequestMock.getRandom(duration);
    LocalDateTime votingEnd = ApiHelper.getNow().plusMinutes(duration);
    sessionService.create(sessionRequest);

    verify(sessionRepository, times(1)).save(captor.capture());

    Session sessionToSave = captor.getValue();

    assertNotNull(sessionToSave);
    assertNotNull(sessionToSave.getTopic());
    assertEquals(votingEnd, sessionToSave.getVotingEnd());
  }

}
