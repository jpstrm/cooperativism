package br.com.cooperativism.response.member;

import br.com.cooperativism.dto.MemberDto;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.ArrayList;
import java.util.List;

@ApiModel("Lita de Associados response")
public class MemberListResponse {

  @ApiModelProperty("Total de Associados")
  private Integer total = 0;

  @ApiModelProperty("List de Associados")
  @JsonProperty("associados")
  private List<MemberDto> memberDtos = new ArrayList<>();

  public MemberListResponse() {
  }

  public MemberListResponse(final Integer total,
      final List<MemberDto> memberDtos) {
    this.total = total;
    this.memberDtos = memberDtos;
  }

  public Integer getTotal() {
    return total;
  }

  public void setTotal(Integer total) {
    this.total = total;
  }

  public List<MemberDto> getMemberDtos() {
    return memberDtos;
  }

  public void setMemberDtos(
      List<MemberDto> memberDtos) {
    this.memberDtos = memberDtos;
  }

}
