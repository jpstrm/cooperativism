package br.com.cooperativism.response.topic;

import br.com.cooperativism.dto.TopicDto;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.ArrayList;
import java.util.List;

@ApiModel("Lita de Pautas response")
public class TopicListResponse {

  @ApiModelProperty("Total de Pautas")
  private Integer total = 0;

  @ApiModelProperty("Lista de Pautas")
  @JsonProperty("pautas")
  private List<TopicDto> topicDtos = new ArrayList<>();

  public TopicListResponse() {
  }

  public TopicListResponse(Integer total, List<TopicDto> topicDtos) {
    this.total = total;
    this.topicDtos = topicDtos;
  }

  public Integer getTotal() {
    return total;
  }

  public void setTotal(Integer total) {
    this.total = total;
  }

  public List<TopicDto> getTopicDtos() {
    return topicDtos;
  }

  public void setTopicDtos(List<TopicDto> topicDtos) {
    this.topicDtos = topicDtos;
  }

}
