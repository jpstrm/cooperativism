package br.com.cooperativism.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@ApiModel("Pauta request")
public class VoteRequest implements Serializable {

  private static final long serialVersionUID = 4753561587146476862L;

  @ApiModelProperty(value = "Nome da Pauta", example = "Test")
  @NotBlank(message = "O campo `nome` não poder ser vazio")
  @JsonProperty("nomePauta")
  private String topicName;

  @ApiModelProperty(value = "CPF do Associado", example = "12345678912")
  @NotBlank(message = "O campo `cpf` não poder ser vazio")
  @JsonProperty("cpf")
  private String memberCpf;

  @ApiModelProperty(value = "Voto", example = "SIM")
  @NotBlank(message = "O campo `voto` não poder ser vazio")
  @JsonProperty("voto")
  private String vote;

  public VoteRequest() {
  }

  public String getTopicName() {
    return topicName;
  }

  public void setTopicName(String topicName) {
    this.topicName = topicName;
  }

  public String getMemberCpf() {
    return memberCpf;
  }

  public void setMemberCpf(String memberCpf) {
    this.memberCpf = memberCpf;
  }

  public String getVote() {
    return vote;
  }

  public void setVote(String vote) {
    this.vote = vote;
  }

}
