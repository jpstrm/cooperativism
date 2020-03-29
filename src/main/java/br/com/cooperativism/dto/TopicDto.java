package br.com.cooperativism.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;

import java.io.Serializable;

@ApiModel("Topico")
public class TopicDto implements Serializable {

  private static final long serialVersionUID = -1123891832793669121L;

  private Long id;

  @JsonProperty("nome")
  private String name;

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

}
