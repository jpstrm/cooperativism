package br.com.cooperativism.mock.topic;

import br.com.cooperativism.dto.TopicDto;

public class TopicDtoMock {

  public static TopicDto getRandom(String topicName, Integer voteYes, Integer voteNo) {

    return new TopicDto("Test", voteYes, voteNo);
  }

  public static TopicDto getRandom(String topicName) {

    return new TopicDto(topicName, 0, 0);
  }

  public static TopicDto getRandom() {

    return getRandom("Test", 0, 0);
  }

}
