package br.com.cooperativism.converter;

import br.com.cooperativism.dto.SessionDto;
import br.com.cooperativism.model.Session;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class SessionConverter extends DefaultConverter<Session, SessionDto> {

  @PostConstruct
  public void setup() {
    this.setClazz(Session.class);
    this.setDtoClazz(SessionDto.class);
  }

}
