package br.com.cooperativism.converter;

import br.com.cooperativism.dto.SessionDto;
import br.com.cooperativism.enums.SessionStatusEnum;
import br.com.cooperativism.model.Session;
import br.com.cooperativism.response.session.SessionListResponse;
import br.com.cooperativism.response.session.SessionResponse;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
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
    if (sessionDto.isExpired()) {
      response.setStatus(SessionStatusEnum.EXPIRED.getValue());
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
