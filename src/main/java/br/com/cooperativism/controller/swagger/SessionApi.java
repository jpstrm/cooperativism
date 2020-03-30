package br.com.cooperativism.controller.swagger;

import br.com.cooperativism.request.SessionRequest;
import br.com.cooperativism.response.SessionListResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;

@Api(value = "Sessões", tags = "Informações sobre as Sessções de votação")
public interface SessionApi {

  @ApiOperation(value = "Listar todos as Sessões",
      notes = "Operação para listar todas as sessões.",
      consumes = "application/json", produces = "application/json")
  @ApiResponses({
      @ApiResponse(code = 200, message = "Requisição realizada com sucesso.",
          response = SessionListResponse.class)})
  ResponseEntity<SessionListResponse> findAll();

  @ApiOperation(value = "Criar uma Sessão",
      notes = "Operação para criar uma sessão.",
      consumes = "application/json", produces = "application/json")
  @ApiResponses({
      @ApiResponse(code = 201, message = "Requisição realizada com sucesso.",
          response = Void.class)})
  ResponseEntity<Void> create(@ApiParam(name = "Request", required = true) SessionRequest sessionRequest);

}
