package br.com.cooperativism.dto;

import br.com.cooperativism.enums.UserStatusEnum;

public class UserInfoDto {

  private UserStatusEnum status;

  public UserStatusEnum getStatus() {
    return status;
  }

  public void setStatus(UserStatusEnum status) {
    this.status = status;
  }

}
