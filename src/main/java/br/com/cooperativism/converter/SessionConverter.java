package br.com.cooperativism.converter;

import br.com.cooperativism.dto.SessionDto;
import br.com.cooperativism.enums.SessionStatus;
import br.com.cooperativism.helper.ApiHelper;
import br.com.cooperativism.model.Session;
import br.com.cooperativism.response.SessionListResponse;
import br.com.cooperativism.response.SessionResponse;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class SessionConverter extends DefaultConverter<Session, SessionDto> {

  @PostConstruct
  public void setup() {
    this.setClazz(Session.class);
    this.setDtoClazz(SessionDto.class);
  }

  public SessionResponse toResponse(SessionDto sessionDto) {
    final SessionResponse response = toAny(sessionDto, SessionResponse.class);
    final LocalDateTime currentDate = ApiHelper.getNow();
    if (currentDate.compareTo(sessionDto.getVotingEnd()) > 0) {
      response.setStatus(SessionStatus.EXPIRED.getValue());
    }

    return response;
  }

  public SessionListResponse toListResponse(List<SessionDto> sessionDtos) {
    List<SessionResponse> sessionsResponse = sessionDtos.stream()
        .map(this::toResponse)
        .collect(Collectors.toList());

    return new SessionListResponse(sessionsResponse.size(), sessionsResponse);
  }

}
