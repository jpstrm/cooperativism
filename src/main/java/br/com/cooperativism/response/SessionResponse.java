package br.com.cooperativism.response;

import br.com.cooperativism.dto.SessionDto;
import br.com.cooperativism.enums.SessionStatus;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("Sessão response")
public class SessionResponse extends SessionDto {

  @ApiModelProperty("Status")
  private String status = SessionStatus.STARTED.getValue();

  public SessionResponse() {
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

}
