package br.com.cooperativism.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

@ApiModel("Membro")
public class MemberDto implements Serializable {

  private static final long serialVersionUID = -3330252217189335135L;

  @ApiModelProperty("Id")
  private Long id;

  @ApiModelProperty(value = "nome", example = "João")
  @JsonProperty("nome")
  private String name;

  @ApiModelProperty(value = "cpf", example = "12345678912")
  private String cpf;

  public MemberDto() {
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getCpf() {
    return cpf;
  }

  public void setCpf(String cpf) {
    this.cpf = cpf;
  }

}
