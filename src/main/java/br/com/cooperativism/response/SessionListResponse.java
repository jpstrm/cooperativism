package br.com.cooperativism.response;

import br.com.cooperativism.dto.SessionDto;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.ArrayList;
import java.util.List;

@ApiModel("Lista de Sessões response")
public class SessionListResponse {

  @ApiModelProperty("Total de Sessões")
  private Integer total = 0;

  @ApiModelProperty("Lista de Sessões")
  @JsonProperty("sessoes")
  private List<SessionDto> sessionDtos = new ArrayList<>();

  public SessionListResponse() {}

  public SessionListResponse(Integer total, List<SessionDto> sessionDtos) {
    this.total = total;
    this.sessionDtos = sessionDtos;
  }

  public Integer getTotal() {
    return total;
  }

  public void setTotal(Integer total) {
    this.total = total;
  }

  public List<SessionDto> getSessionDtos() {
    return sessionDtos;
  }

  public void setSessionDtos(List<SessionDto> sessionDtos) {
    this.sessionDtos = sessionDtos;
  }

}
