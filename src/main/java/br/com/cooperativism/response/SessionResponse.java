package br.com.cooperativism.response;

import br.com.cooperativism.dto.SessionDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("Sessão response")
public class SessionResponse extends SessionDto {

  @ApiModelProperty("Votos a favor da puta")
  private Integer votesYes = 0;

  @ApiModelProperty("Votos contra a puta")
  private Integer votesNo = 0;

  public SessionResponse() {
  }

  public SessionResponse(Integer votesYes, Integer votesNo) {
    this.votesYes = votesYes;
    this.votesNo = votesNo;
  }

  public Integer getVotesYes() {
    return votesYes;
  }

  public void setVotesYes(Integer votesYes) {
    this.votesYes = votesYes;
  }

  public Integer getVotesNo() {
    return votesNo;
  }

  public void setVotesNo(Integer votesNo) {
    this.votesNo = votesNo;
  }

}
