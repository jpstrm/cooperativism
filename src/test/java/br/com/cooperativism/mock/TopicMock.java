package br.com.cooperativism.mock;


import br.com.cooperativism.model.Topic;

public class TopicMock {

  public static Topic getRandom(String topicName) {

    return new Topic(topicName);
  }

}
