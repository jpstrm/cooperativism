package br.com.cooperativism.mock.member;


import br.com.cooperativism.dto.MemberDto;

import java.util.ArrayList;
import java.util.List;

public class MemberDtoMock {

  public static MemberDto getRandom(String username, String cpf) {

    return new MemberDto(username, cpf);
  }

  public static MemberDto getRandom(String cpf) {

    return getRandom("User test", cpf);
  }

  public static List<MemberDto> getListRandom(String cpf) {
    final List<MemberDto> memberDtos = new ArrayList<>();
    memberDtos.add(getRandom(cpf));

    return memberDtos;
  }

}
