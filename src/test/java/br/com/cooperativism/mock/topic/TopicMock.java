package br.com.cooperativism.mock.topic;


import br.com.cooperativism.model.Topic;

import java.util.ArrayList;
import java.util.List;

public class TopicMock {

  public static Topic getRandom(String topicName) {

    return new Topic(topicName);
  }

  public static List<Topic> getListRandom(String topicName) {
    final List<Topic> topics = new ArrayList<>();
    topics.add(getRandom(topicName));

    return topics;
  }

}
