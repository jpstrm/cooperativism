package br.com.cooperativism.controller.swagger;

import br.com.cooperativism.dto.TopicDto;
import br.com.cooperativism.request.TopicRequest;
import br.com.cooperativism.response.topic.TopicListResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;

@Api(value = "Pautas", tags = "Informações sobre as Pautas das Assembleias")
public interface TopicApi {

  @ApiOperation(value = "Listar todos as Pautas",
      notes = "Operação para listar todas as pautas.",
      consumes = "application/json", produces = "application/json")
  @ApiResponses({
      @ApiResponse(code = 200, message = "Requisição realizada com sucesso.",
          response = TopicListResponse.class)})
  ResponseEntity<TopicListResponse> findAll();

  @ApiOperation(value = "Criar uma Pauta",
      notes = "Operação para criar uma pauta.",
      consumes = "application/json", produces = "application/json")
  @ApiResponses({
      @ApiResponse(code = 201, message = "Requisição realizada com sucesso.",
          response = Void.class)})
  ResponseEntity<Void> create(@ApiParam(name = "Request", required = true) TopicRequest topicRequest);

  @ApiOperation(value = "Encontrar uma Paula",
      notes = "Operação para encontrar uma pauta pelo nome.",
      consumes = "application/json", produces = "application/json")
  @ApiResponses({
      @ApiResponse(code = 201, message = "Requisição realizada com sucesso.",
          response = TopicDto.class)})
  ResponseEntity<TopicDto> findByName(@ApiParam(name = "Request", example = "Test", required = true) String topicName);

}
