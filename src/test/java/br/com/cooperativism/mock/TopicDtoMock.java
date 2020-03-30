package br.com.cooperativism.mock;

import br.com.cooperativism.dto.TopicDto;

public class TopicDtoMock {

  public static TopicDto getRandom(Integer voteYes, Integer voteNo) {

    return new TopicDto("Test", voteYes, voteNo);
  }

  public static TopicDto getRandom() {

    return getRandom(0, 0);
  }

}
