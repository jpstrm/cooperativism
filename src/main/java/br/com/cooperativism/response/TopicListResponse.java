package br.com.cooperativism.response;

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
  private List<TopicResponse> topicResponses = new ArrayList<>();

  public TopicListResponse() {
  }

  public TopicListResponse(Integer total, List<TopicResponse> topicsResponse) {
    this.total = total;
    this.topicResponses = topicsResponse;
  }

  public Integer getTotal() {
    return total;
  }

  public void setTotal(Integer total) {
    this.total = total;
  }

  public List<TopicResponse> getTopicResponses() {
    return topicResponses;
  }

  public void setTopicResponses(List<TopicResponse> topicResponses) {
    this.topicResponses = topicResponses;
  }

}
