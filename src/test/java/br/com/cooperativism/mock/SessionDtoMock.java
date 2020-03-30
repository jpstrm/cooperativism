package br.com.cooperativism.mock;

import br.com.cooperativism.dto.SessionDto;
import br.com.cooperativism.helper.ApiHelper;

import java.time.LocalDateTime;

public class SessionDtoMock {

  public static SessionDto getRandom(Integer duration) {
    LocalDateTime currentDate = ApiHelper.getNow();

    return new SessionDto(currentDate, currentDate.plusMinutes(duration), TopicDtoMock.getRandom());
  }

}
