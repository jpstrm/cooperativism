package br.com.cooperativism.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

@ApiModel("Sessão")
public class SessionDto implements Serializable {

  private static final long serialVersionUID = -5418987957147472154L;

  @ApiModelProperty("Id")
  private Long id;

  @ApiModelProperty("Início da votação")
  @JsonProperty("inicioVotacao")
  private Date votingStart;

  @ApiModelProperty("Fim da votação")
  @JsonProperty("fimVotacao")
  private Date votingEnd;

  @ApiModelProperty("Topico")
  @JsonProperty("topico")
  private TopicDto topic;

  public SessionDto() {
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Date getVotingStart() {
    return votingStart;
  }

  public void setVotingStart(Date votingStart) {
    this.votingStart = votingStart;
  }

  public Date getVotingEnd() {
    return votingEnd;
  }

  public void setVotingEnd(Date votingEnd) {
    this.votingEnd = votingEnd;
  }

  public TopicDto getTopic() {
    return topic;
  }

  public void setTopic(TopicDto topic) {
    this.topic = topic;
  }

}
