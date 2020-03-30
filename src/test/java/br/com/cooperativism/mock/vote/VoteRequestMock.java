package br.com.cooperativism.mock.vote;

import br.com.cooperativism.request.VoteRequest;

import java.util.ArrayList;
import java.util.List;

public class VoteRequestMock {

  public static VoteRequest getRandom(String memberCpf, String vote) {

    return new VoteRequest(1L, memberCpf, vote);
  }

  public static VoteRequest getRandom(String vote) {

    return new VoteRequest(1L, "12345678912", vote);
  }

  public static List<VoteRequest> getListRandom(String vote) {
    final List<VoteRequest> voteRequests = new ArrayList<>();
    voteRequests.add(getRandom(vote));

    return voteRequests;
  }

}
