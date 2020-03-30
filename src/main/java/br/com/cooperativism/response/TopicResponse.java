package br.com.cooperativism.response;

import br.com.cooperativism.dto.TopicDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("Topico response")
public class TopicResponse extends TopicDto {

  @ApiModelProperty("Votos a favor da puta")
  private Integer votesYes = 0;

  @ApiModelProperty("Votos contra a puta")
  private Integer votesNo = 0;

  public TopicResponse() {
  }

  public TopicResponse(Integer votesYes, Integer votesNo) {
    this.votesYes = votesYes;
    this.votesNo = votesNo;
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
