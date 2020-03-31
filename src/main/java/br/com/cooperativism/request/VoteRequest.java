package br.com.cooperativism.request;

import br.com.cooperativism.enums.VoteEnum;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@ApiModel("Voto request")
public class VoteRequest implements Serializable {

  private static final long serialVersionUID = 4753561587146476862L;

  @ApiModelProperty(value = "Id da Pauta", example = "1")
  @NotNull(message = "O campo `idPauta` não poder ser nulo")
  @JsonProperty("idPauta")
  private Long topicId;

  @ApiModelProperty(value = "CPF do Associado", example = "12345678912")
  @NotBlank(message = "O campo `cpf` não poder ser vazio")
  @JsonProperty("cpf")
  @Size(min = 11, max = 11, message = "O campo 'cpf' deve possuir 11 dígitos.")
  private String memberCpf;

  @ApiModelProperty(value = "Voto", example = "SIM")
  @NotBlank(message = "O campo `voto` não poder ser vazio")
  @JsonProperty("voto")
  private String vote;

  public VoteRequest() {
  }

  public VoteRequest(final Long topicId, final String memberCpf, final String vote) {
    this.topicId = topicId;
    this.memberCpf = memberCpf;
    this.vote = vote;
  }

  public Long getTopicId() {
    return topicId;
  }

  public void setTopicId(Long topicId) {
    this.topicId = topicId;
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

  public VoteEnum getVoteEnum() {
    return VoteEnum.find(this.vote).get();
  }

}
