package br.com.cooperativism.response.vote;

import br.com.cooperativism.dto.VoteDto;
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
  private List<VoteDto> voteDtos = new ArrayList<>();

  public VoteListResponse() {
  }

  public VoteListResponse(Integer total, List<VoteDto> voteDtos) {
    this.total = total;
    this.voteDtos = voteDtos;
  }

  public Integer getTotal() {
    return total;
  }

  public void setTotal(Integer total) {
    this.total = total;
  }

  public List<VoteDto> getVoteDtos() {
    return voteDtos;
  }

  public void setVoteDtos(List<VoteDto> voteDtos) {
    this.voteDtos = voteDtos;
  }

}
