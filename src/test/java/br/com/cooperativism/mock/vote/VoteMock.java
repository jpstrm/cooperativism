package br.com.cooperativism.mock.vote;


import br.com.cooperativism.enums.VoteEnum;
import br.com.cooperativism.mock.member.MemberMock;
import br.com.cooperativism.mock.session.SessionMock;
import br.com.cooperativism.model.Vote;

import java.util.ArrayList;
import java.util.List;

public class VoteMock {

  public static Vote getRandom(String voteName, VoteEnum voteEnum) {

    return new Vote(MemberMock.getRandom(), SessionMock.getRandom(1, "Test"), voteEnum);
  }

  public static List<Vote> getListRandom(String voteName, VoteEnum voteEnum) {
    final List<Vote> votes = new ArrayList<>();
    votes.add(getRandom(voteName, voteEnum));

    return votes;
  }

}
