package br.com.cooperativism.converter;

import br.com.cooperativism.dto.VoteDto;
import br.com.cooperativism.model.Vote;
import br.com.cooperativism.request.VoteRequest;
import br.com.cooperativism.response.vote.VoteListResponse;
import br.com.cooperativism.service.MemberService;
import br.com.cooperativism.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

@Component
public class VoteConverter extends DefaultConverter<Vote, VoteDto> {

  @Autowired
  private MemberService memberService;

  @Autowired
  private SessionService sessionService;

  @PostConstruct
  public void setup() {
    this.setClazz(Vote.class);
    this.setDtoClazz(VoteDto.class);
  }

  public Vote fromRequest(VoteRequest voteRequest) {
    final Vote vote = new Vote();
    vote.setMember(memberService.getOrSave(voteRequest.getMemberCpf()));
    vote.setSession(sessionService.findValidByTopicName(voteRequest.getTopicName()));
    vote.setVote(voteRequest.getVoteEnum());

    return vote;
  }

  public VoteListResponse toListResponse(List<Vote> votes) {
    final List<VoteDto> votesResponse = toDtoList(votes);

    return new VoteListResponse(votesResponse.size(), votesResponse);
  }
}
