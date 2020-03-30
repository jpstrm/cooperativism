package br.com.cooperativism.mock.vote;


import br.com.cooperativism.dto.VoteDto;
import br.com.cooperativism.mock.member.MemberDtoMock;
import br.com.cooperativism.mock.session.SessionDtoMock;

import java.util.ArrayList;
import java.util.List;

public class VoteDtoMock {

  public static VoteDto getRandom(String memberCpf, String vote) {

    return new VoteDto(MemberDtoMock.getRandom(memberCpf), SessionDtoMock.getRandom(1), vote);
  }

  public static List<VoteDto> getListRandom(String memberCpf, String vote) {
    final List<VoteDto> voteDtos = new ArrayList<>();
    voteDtos.add(getRandom(memberCpf, vote));

    return voteDtos;
  }

}
