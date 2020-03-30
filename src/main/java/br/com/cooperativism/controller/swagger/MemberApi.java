package br.com.cooperativism.controller.swagger;

import br.com.cooperativism.response.MemberListResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;

@Api(value = "Associados", tags = "Informações sobre os Associados da cooperativa")
public interface MemberApi {

  @ApiOperation(value = "Listar todos os Associados",
      notes = "Operação para listar todos os associados da cooperativa.",
      consumes = "application/json", produces = "application/json")
  @ApiResponses({
      @ApiResponse(code = 200, message = "Requisição realizada com sucesso.",
          response = MemberListResponse.class)})
  ResponseEntity<MemberListResponse> findAll();

}
