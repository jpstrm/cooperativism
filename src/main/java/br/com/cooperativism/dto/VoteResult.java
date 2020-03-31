package br.com.cooperativism.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;

public class VoteResult {

  @ApiModelProperty("Votos a favor da puta")
  @JsonInclude(JsonInclude.Include.NON_NULL)
  protected Integer votesYes;

  @ApiModelProperty("Votos contra a puta")
  @JsonInclude(JsonInclude.Include.NON_NULL)
  protected Integer votesNo;

  public VoteResult() {
  }

  public VoteResult(Integer votesYes, Integer votesNo) {
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

  @Override
  public String toString() {
    return "{\"VoteResult\":{"
        + "\"votesYes\":\"" + votesYes + "\""
        + ", \"votesNo\":\"" + votesNo + "\""
        + "}}";
  }

}
