package br.com.cooperativism.response.vote;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@ApiModel("Lita de Votos response")
public class VoteListResponse implements Serializable {

  private static final long serialVersionUID = -5699561640115032822L;

  @ApiModelProperty("Total de Votos")
  private Integer total = 0;

  @ApiModelProperty("Lista de Votos")
  @JsonProperty("votos")
  private List<VoteResponse> votes = new ArrayList<>();

  public VoteListResponse() {
  }

  public VoteListResponse(Integer total, List<VoteResponse> votes) {
    this.total = total;
    this.votes = votes;
  }

  public Integer getTotal() {
    return total;
  }

  public void setTotal(Integer total) {
    this.total = total;
  }

  public List<VoteResponse> getVotes() {
    return votes;
  }

  public void setVotes(List<VoteResponse> votes) {
    this.votes = votes;
  }

}
