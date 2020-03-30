package br.com.cooperativism.mock.session;

import br.com.cooperativism.helper.ApiHelper;
import br.com.cooperativism.mock.topic.TopicMock;
import br.com.cooperativism.model.Session;

import java.time.LocalDateTime;

public class SessionMock {

  public static Session getRandom(Integer duration, String topicName) {
    LocalDateTime currentDate = ApiHelper.getNow();

    return new Session(currentDate, currentDate.plusMinutes(duration), TopicMock.getRandom(topicName));
  }

}
