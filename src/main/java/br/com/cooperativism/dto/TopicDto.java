package br.com.cooperativism.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

@ApiModel("Pauta")
public class TopicDto extends VoteResult implements Serializable {

  private static final long serialVersionUID = -1123891832793669121L;

  @ApiModelProperty("Votos a favor da puta")
  private Long id;

  @ApiModelProperty("Nome")
  @JsonProperty("nome")
  private String name;

  public TopicDto() {
  }

  public TopicDto(final String name, final Integer votesYes, final Integer votesNo) {
    this.name = name;
    this.votesYes = votesYes;
    this.votesNo = votesNo;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Override
  public String toString() {
    return "{\"TopicDto\":"
        + super.toString()
        + ", \"id\":\"" + id + "\""
        + ", \"name\":\"" + name + "\""
        + ", \"votesYes\":\"" + votesYes + "\""
        + ", \"votesNo\":\"" + votesNo + "\""
        + "}";
  }

}
