package br.com.cooperativism.service;

import br.com.cooperativism.converter.TopicConverter;
import br.com.cooperativism.dto.TopicDto;
import br.com.cooperativism.dto.VoteResult;
import br.com.cooperativism.exception.NotFoundException;
import br.com.cooperativism.mock.topic.TopicDtoMock;
import br.com.cooperativism.mock.topic.TopicMock;
import br.com.cooperativism.model.Topic;
import br.com.cooperativism.repository.TopicRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TopicServiceTest {

  @InjectMocks
  private TopicService topicService;

  @Mock
  private TopicRepository topicRepository;

  @Mock
  private TopicConverter topicConverter;

  @Mock
  private VoteService voteService;

  private String topicName = "Test";

  @Test
  public void shouldComputeVotesInFindAllToDto() {
    TopicDto topicDto = TopicDtoMock.getRandom(topicName);
    ReflectionTestUtils.setField(topicDto, "id", 1L);
    List<Topic> topics = TopicMock.getListRandom(topicName);
    Integer votesYes = 2;
    Integer votesNo = 0;
    VoteResult voteResult = new VoteResult(votesYes, votesNo);

    when(topicRepository.findAll())
        .thenReturn(topics);
    when(topicConverter.toDto(isA(Topic.class)))
        .thenReturn(topicDto);
    when(voteService.sumVotesByTopicId(anyLong()))
        .thenReturn(voteResult);

    List<TopicDto> result = topicService.findAllToDto();

    assertNotNull(result);
    assertFalse(result.isEmpty());
    assertEquals(votesYes, result.get(0).getVotesYes());
    assertEquals(votesNo, result.get(0).getVotesNo());
  }

  @Test
  public void throwErrorIfTopicNotFoundInFindByName() {
    when(topicRepository.findFirstByNameIgnoreCase(anyString()))
        .thenReturn(Optional.empty());

    assertThrows(NotFoundException.class, () -> topicService.findByName(topicName));
  }

  @Test
  public void throwErrorIfTopicNotFoundInFindById() {
    when(topicRepository.findById(anyLong()))
        .thenReturn(Optional.empty());

    assertThrows(NotFoundException.class, () -> topicService.findById(1L));
  }


}
