package br.com.cooperativism.converter;

import br.com.cooperativism.dto.VoteDto;
import br.com.cooperativism.model.Vote;
import br.com.cooperativism.request.VoteRequest;
import br.com.cooperativism.response.vote.VoteListResponse;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

@Component
public class VoteConverter extends DefaultConverter<Vote, VoteDto> {

  @PostConstruct
  public void setup() {
    this.setClazz(Vote.class);
    this.setDtoClazz(VoteDto.class);
  }

  public Vote fromRequest(VoteRequest voteRequest) {
    return toAny(voteRequest, Vote.class);
  }

  public VoteListResponse toListResponse(List<Vote> votes) {
    final List<VoteDto> votesResponse = toDtoList(votes);

    return new VoteListResponse(votesResponse.size(), votesResponse);
  }
}
