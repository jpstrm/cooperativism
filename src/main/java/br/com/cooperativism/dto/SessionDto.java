package br.com.cooperativism.dto;

import br.com.cooperativism.helper.ApiHelper;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.time.LocalDateTime;

@ApiModel("Sessão")
public class SessionDto implements Serializable {

  private static final long serialVersionUID = -5418987957147472154L;

  @ApiModelProperty("Id")
  private Long id;

  @ApiModelProperty("Início da votação")
  @JsonProperty("inicioVotacao")
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy'T'HH:mm")
  private LocalDateTime votingStart;

  @ApiModelProperty("Fim da votação")
  @JsonProperty("fimVotacao")
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy'T'HH:mm")
  private LocalDateTime votingEnd;

  @ApiModelProperty("Pauta")
  @JsonProperty("pauta")
  private TopicDto topic;

  public SessionDto() {
  }

  public SessionDto(final LocalDateTime votingStart, final LocalDateTime votingEnd, final TopicDto topic) {
    this.votingStart = votingStart;
    this.votingEnd = votingEnd;
    this.topic = topic;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public LocalDateTime getVotingStart() {
    return votingStart;
  }

  public void setVotingStart(LocalDateTime votingStart) {
    this.votingStart = votingStart;
  }

  public LocalDateTime getVotingEnd() {
    return votingEnd;
  }

  public void setVotingEnd(LocalDateTime votingEnd) {
    this.votingEnd = votingEnd;
  }

  public TopicDto getTopic() {
    return topic;
  }

  public void setTopic(TopicDto topic) {
    this.topic = topic;
  }

  @JsonIgnore
  public boolean isExpired() {
    return ApiHelper.isDateExpired(votingEnd);
  }

}
