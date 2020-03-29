package br.com.cooperativism.request;

import br.com.cooperativism.model.Topic;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("Sessão request")
public class SessionRequest {

  @ApiModelProperty("Duração em minutos")
  private Integer duration = 1;

  private Topic topic;

  public SessionRequest() {
  }

  public Integer getDuration() {
    return duration;
  }

  public void setDuration(Integer duration) {
    this.duration = duration;
  }

  public Topic getTopic() {
    return topic;
  }

  public void setTopic(Topic topic) {
    this.topic = topic;
  }

}
