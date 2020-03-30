package br.com.cooperativism.response.vote;

import br.com.cooperativism.dto.VoteDto;
import br.com.cooperativism.enums.VoteEnum;
import io.swagger.annotations.ApiModel;

import java.io.Serializable;

@ApiModel("Voto response")
public class VoteResponse extends VoteDto implements Serializable {

  private static final long serialVersionUID = -6560211302949874441L;

  public String getVote() {
    return VoteEnum.find(vote)
        .map(VoteEnum::getValue)
        .orElse(vote);
  }
}
