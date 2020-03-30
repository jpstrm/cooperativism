package br.com.cooperativism.mock.session;

import br.com.cooperativism.dto.SessionDto;
import br.com.cooperativism.helper.ApiHelper;
import br.com.cooperativism.mock.topic.TopicDtoMock;

import java.time.LocalDateTime;

public class SessionDtoMock {

  public static SessionDto getRandom(Integer duration) {
    LocalDateTime currentDate = ApiHelper.getNow();

    return new SessionDto(currentDate, currentDate.plusMinutes(duration), TopicDtoMock.getRandom());
  }

}
