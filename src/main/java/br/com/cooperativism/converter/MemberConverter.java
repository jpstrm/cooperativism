package br.com.cooperativism.converter;

import br.com.cooperativism.dto.MemberDto;
import br.com.cooperativism.model.Member;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class MemberConverter extends DefaultConverter<Member, MemberDto> {

  @PostConstruct
  public void setup() {
    this.setClazz(Member.class);
    this.setDtoClazz(MemberDto.class);
  }

}
