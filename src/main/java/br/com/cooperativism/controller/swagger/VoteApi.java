package br.com.cooperativism.controller.swagger;

import br.com.cooperativism.request.VoteRequest;
import br.com.cooperativism.response.vote.VoteListResponse;
import br.com.cooperativism.response.vote.VoteResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;

@Api(value = "Votos", tags = "Informações sobre os Votos das Pautas")
public interface VoteApi {

  @ApiOperation(value = "Listar todos os Votos",
      notes = "Operação para listar todas os votos.",
      consumes = "application/json", produces = "application/json")
  @ApiResponses({
      @ApiResponse(code = 200, message = "Requisição realizada com sucesso.",
          response = VoteListResponse.class)})
  ResponseEntity<VoteListResponse> findAll();

  @ApiOperation(value = "Votar",
      notes = "Operação para votar em uma pauta.",
      consumes = "application/json", produces = "application/json")
  @ApiResponses({
      @ApiResponse(code = 200, message = "Requisição realizada com sucesso.",
          response = Void.class)})
  ResponseEntity<Void> vote(@ApiParam(name = "Request", required = true) VoteRequest voteRequest);

  @ApiOperation(value = "Encontrar um voto por CPF",
      notes = "Operação para encontrar uma voto pelo CPF do Associado.",
      consumes = "application/json", produces = "application/json")
  @ApiResponses({
      @ApiResponse(code = 201, message = "Requisição realizada com sucesso.",
          response = VoteResponse.class)})
  ResponseEntity<VoteResponse> findByMemberCpf(
      @ApiParam(name = "Request", example = "12345678912", required = true) String memberCpf);

  @ApiOperation(value = "Encontrar um voto por Id da Sessão",
      notes = "Operação para encontrar uma voto pelo Id de uma Sessão.",
      consumes = "application/json", produces = "application/json")
  @ApiResponses({
      @ApiResponse(code = 201, message = "Requisição realizada com sucesso.",
          response = VoteListResponse.class)})
  ResponseEntity<VoteListResponse> findBySessionId(
      @ApiParam(name = "Id da Sessão", example = "1", required = true) Long sessionId);

}
