package br.com.cooperativism.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

@ApiModel("Pauta")
public class TopicDto implements Serializable {

  private static final long serialVersionUID = -1123891832793669121L;

  @ApiModelProperty("Votos a favor da puta")
  private Long id;

  @ApiModelProperty("Nome")
  @JsonProperty("nome")
  private String name;

  @ApiModelProperty("Votos a favor da puta")
  @JsonInclude(JsonInclude.Include.NON_NULL)
  private Integer votesYes;

  @ApiModelProperty("Votos contra a puta")
  @JsonInclude(JsonInclude.Include.NON_NULL)
  private Integer votesNo;

  public TopicDto() {
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

  public Integer getVotesYes() {
    return votesYes;
  }

  public void setVotesYes(Integer votesYes) {
    this.votesYes = votesYes;
  }

  public Integer getVotesNo() {
    return votesNo;
  }

  public void setVotesNo(Integer votesNo) {
    this.votesNo = votesNo;
  }

}
