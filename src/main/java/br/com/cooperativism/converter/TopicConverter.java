package br.com.cooperativism.converter;

import br.com.cooperativism.dto.TopicDto;
import br.com.cooperativism.model.Topic;
import br.com.cooperativism.request.TopicRequest;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class TopicConverter extends DefaultConverter<Topic, TopicDto> {

  @PostConstruct
  public void setup() {
    this.setClazz(Topic.class);
    this.setDtoClazz(TopicDto.class);
  }

  public Topic fromRequest(TopicRequest topicRequest) {
    return modelMapper.map(topicRequest, Topic.class);
  }
}
