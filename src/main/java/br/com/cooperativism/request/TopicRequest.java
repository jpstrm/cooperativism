package br.com.cooperativism.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@ApiModel("Pauta request")
public class TopicRequest implements Serializable {

  private static final long serialVersionUID = -8458575684129896178L;

  @ApiModelProperty(value = "Nome da Pauta", example = "Test")
  @NotBlank(message = "O campo `nome` não poder ser vazio")
  @JsonProperty("nome")
  private String name;

  public TopicRequest() {
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

}
