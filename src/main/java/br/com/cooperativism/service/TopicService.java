package br.com.cooperativism.service;

import br.com.cooperativism.converter.TopicConverter;
import br.com.cooperativism.dto.TopicDto;
import br.com.cooperativism.exception.NotFoundException;
import br.com.cooperativism.model.Topic;
import br.com.cooperativism.repository.TopicRepository;
import br.com.cooperativism.request.TopicRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TopicService {

  @Autowired
  private TopicRepository topicRepository;

  @Autowired
  private TopicConverter topicConverter;

  public List<Topic> findAll() {
    return topicRepository.findAll();
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

    return topicConverter.toDto(findByName(topicName));
  }

  public Topic findById(final Long topicId) {
    return topicRepository.findById(topicId)
        .orElseThrow(this::throwNotFound);
  }

  private NotFoundException throwNotFound() {
    throw new NotFoundException("Pauta não encontrada.");
  }

}
