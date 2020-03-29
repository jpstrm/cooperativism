package br.com.cooperativism.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

@ApiModel("Sessão")
public class SessionDto {

  @ApiModelProperty("Id")
  private Long id;

  @ApiModelProperty(value = "Duração da votação em minutos", example = "1")
  private Date duration;

  @ApiModelProperty(value = "Id da Pauta", example = "1")
  private TopicDto topic;

  public SessionDto() {
  }

  public Date getDuration() {
    return duration;
  }

  public void setDuration(Date duration) {
    this.duration = duration;
  }

  public TopicDto getTopic() {
    return topic;
  }

  public void setTopic(TopicDto topic) {
    this.topic = topic;
  }

}
