package br.com.cooperativism.mock.member;


import br.com.cooperativism.model.Member;

import java.util.ArrayList;
import java.util.List;

public class MemberMock {

  public static Member getRandom(String cpf) {

    return new Member(cpf);
  }

  public static Member getRandom() {

    return getRandom("12345678912");
  }

  public static List<Member> getListRandom(String cpf) {
    final List<Member> members = new ArrayList<>();
    members.add(getRandom(cpf));

    return members;
  }

}
