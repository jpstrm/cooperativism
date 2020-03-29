package br.com.cooperativism.service;

import br.com.cooperativism.converter.TopicConverter;
import br.com.cooperativism.dto.TopicDto;
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

  public List<TopicDto> findAll() {
    final List<Topic> members = topicRepository.findAll();
    return topicConverter.toDtoList(members);
  }

  public void create(TopicRequest topicRequest) {
    final Topic topic = topicRepository.findFirstByNameIgnoreCase(topicRequest.getName())
        .orElse(topicConverter.fromRequest(topicRequest));
    topicRepository.save(topic);
  }

}
