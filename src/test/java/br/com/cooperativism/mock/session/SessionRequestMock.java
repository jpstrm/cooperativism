package br.com.cooperativism.mock.session;

import br.com.cooperativism.request.SessionRequest;

public class SessionRequestMock {

  public static SessionRequest getRandom(Integer duration, Long topicId) {

    return new SessionRequest(duration, topicId);
  }

  public static SessionRequest getRandom(Integer duration) {

    return getRandom(duration, 1L);
  }

}
