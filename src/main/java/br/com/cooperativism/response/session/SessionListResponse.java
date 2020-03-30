package br.com.cooperativism.response.session;

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
  private List<SessionResponse> sessions = new ArrayList<>();

  public SessionListResponse() {}

  public SessionListResponse(Integer total, List<SessionResponse> sessionsResponse) {
    this.total = total;
    this.sessions = sessionsResponse;
  }

  public Integer getTotal() {
    return total;
  }

  public void setTotal(Integer total) {
    this.total = total;
  }

  public List<SessionResponse> getSessions() {
    return sessions;
  }

  public void setSessions(List<SessionResponse> sessions) {
    this.sessions = sessions;
  }

}
