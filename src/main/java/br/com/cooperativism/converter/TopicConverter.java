package br.com.cooperativism.converter;

import br.com.cooperativism.dto.TopicDto;
import br.com.cooperativism.model.Topic;
import br.com.cooperativism.request.TopicRequest;
import br.com.cooperativism.response.TopicListResponse;
import br.com.cooperativism.response.TopicResponse;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

@Component
public class TopicConverter extends DefaultConverter<Topic, TopicDto> {

  @PostConstruct
  public void setup() {
    this.setClazz(Topic.class);
    this.setDtoClazz(TopicDto.class);
  }

  public Topic fromRequest(TopicRequest topicRequest) {
    return toAny(topicRequest, Topic.class);
  }

  public TopicResponse toResponse(TopicDto topicDto) {
    final TopicResponse response = toAny(topicDto, TopicResponse.class);

    return response;
  }

  public TopicListResponse toListResponse(List<Topic> topics) {
    final List<TopicResponse> topicsResponse = toList(topics, TopicResponse.class);

    return new TopicListResponse(topicsResponse.size(), topicsResponse);
  }
}
