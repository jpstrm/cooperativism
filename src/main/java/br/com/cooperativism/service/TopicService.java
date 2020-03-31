package br.com.cooperativism.service;

import br.com.cooperativism.converter.TopicConverter;
import br.com.cooperativism.dto.TopicDto;
import br.com.cooperativism.enums.VoteEnum;
import br.com.cooperativism.exception.NotFoundException;
import br.com.cooperativism.model.Topic;
import br.com.cooperativism.model.Vote;
import br.com.cooperativism.repository.TopicRepository;
import br.com.cooperativism.repository.VoteRepository;
import br.com.cooperativism.request.TopicRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TopicService {

  @Autowired
  private TopicRepository topicRepository;

  @Autowired
  private TopicConverter topicConverter;

  @Autowired
  private VoteRepository voteRepository;

  public List<TopicDto> findAllToDto() {
    return topicRepository.findAll().stream()
        .map(t -> fillVotesToDto(topicConverter.toDto(t)))
        .collect(Collectors.toList());
  }

  public void create(final TopicRequest topicRequest) {
    final Topic topic = topicRepository.findFirstByNameIgnoreCase(topicRequest.getName())
        .orElse(topicConverter.fromRequest(topicRequest));
    topicRepository.save(topic);
  }

  public Topic findByName(final String topicName) {
    return topicRepository.findFirstByNameIgnoreCase(topicName)
        .orElseThrow(this::throwNotFound);
  }

  public TopicDto findByNameToDto(final String topicName) {
    final TopicDto topicDto = topicConverter.toDto(findByName(topicName));
    fillVotesToDto(topicDto);

    return topicDto;
  }

  public Topic findById(final Long topicId) {

    return topicRepository.findById(topicId)
        .orElseThrow(this::throwNotFound);
  }

  public TopicDto findByIdToDto(final Long topicId) {
    final TopicDto topicDto = topicConverter.toDto(findById(topicId));

    return fillVotesToDto(topicDto);
  }

  private TopicDto fillVotesToDto(final TopicDto topicDto) {
    final List<Vote> votes = voteRepository.findBySessionTopicId(topicDto.getId());
    final Integer votesNo = Math.toIntExact(votes.stream()
        .filter(v -> v.getVote().equals(VoteEnum.NO))
        .count());
    final Integer votesYes = Math.toIntExact(votes.stream()
        .filter(v -> v.getVote().equals(VoteEnum.YES))
        .count());
    topicDto.setVotesNo(votesNo);
    topicDto.setVotesYes(votesYes);

    return topicDto;
  }

  private NotFoundException throwNotFound() {
    throw new NotFoundException("Pauta não encontrada.");
  }

}
