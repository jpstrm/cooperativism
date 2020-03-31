package br.com.cooperativism.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

@ApiModel("Voto")
public class VoteDto implements Serializable {

  private static final long serialVersionUID = -5584847643964089453L;

  @ApiModelProperty("Id")
  private Long id;

  @ApiModelProperty("Associado")
  @JsonProperty("associado")
  private MemberDto member;

  @ApiModelProperty("Sessão")
  @JsonProperty("sessao")
  private SessionDto session;

  @ApiModelProperty(value = "Voto", example = "SIM")
  @JsonProperty("voto")
  protected String vote;

  @ApiModelProperty(value = "Status do voto", example = "VALIDO")
  private String status;

  @ApiModelProperty("Mensagem de erro")
  @JsonInclude(JsonInclude.Include.NON_NULL)
  @JsonProperty("mensagemErro")
  private String errorMsg;

  public VoteDto() {
  }

  public VoteDto(final MemberDto member, final SessionDto session, final String vote) {
    this.member = member;
    this.session = session;
    this.vote = vote;
  }

  public VoteDto(final MemberDto member, final SessionDto session, final String vote, final String status) {
    this.member = member;
    this.session = session;
    this.vote = vote;
    this.status = status;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public MemberDto getMember() {
    return member;
  }

  public void setMember(MemberDto member) {
    this.member = member;
  }

  public SessionDto getSession() {
    return session;
  }

  public void setSession(SessionDto session) {
    this.session = session;
  }

  public String getVote() {
    return vote;
  }

  public void setVote(String vote) {
    this.vote = vote;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public String getErrorMsg() {
    return errorMsg;
  }

  public void setErrorMsg(String errorMsg) {
    this.errorMsg = errorMsg;
  }

}
