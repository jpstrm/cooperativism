package br.com.cooperativism.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@ApiModel("Sessão request")
public class SessionRequest {

  @ApiModelProperty(value = "Duração em minutos", example = "1")
  @JsonProperty("duracao")
  @Min(value = 1, message = "O campo 'duracao' deve ser maior que 1")
  private Integer duration = 1;

  @ApiModelProperty(value = "Id da Pauta", example = "1")
  @NotNull(message = "O campo 'idPauta' não pode ser nulo")
  @JsonProperty("idPauta")
  private Long topicId;

  public SessionRequest() {
  }

  public Integer getDuration() {
    return duration;
  }

  public void setDuration(Integer duration) {
    this.duration = duration;
  }

  public Long getTopicId() {
    return topicId;
  }

  public void setTopicId(Long topicId) {
    this.topicId = topicId;
  }

}
