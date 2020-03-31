package br.com.cooperativism.controller;

import br.com.cooperativism.controller.swagger.VoteApi;
import br.com.cooperativism.converter.VoteConverter;
import br.com.cooperativism.dto.VoteDto;
import br.com.cooperativism.request.VoteRequest;
import br.com.cooperativism.response.vote.VoteListResponse;
import br.com.cooperativism.response.vote.VoteResponse;
import br.com.cooperativism.service.VoteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/votos")
public class VoteController implements VoteApi {

  private Logger logger = LoggerFactory.getLogger(VoteController.class);

  @Autowired
  private VoteService voteService;

  @Autowired
  private VoteConverter voteConverter;

  @Override
  @GetMapping
  public ResponseEntity<VoteListResponse> findAll() {
    logger.info("Request GET /votos");
    final VoteListResponse response = voteConverter.toListResponse(voteService.findAll());
    logger.info("Response GET /votos - tamanho: {}", response.getTotal());

    return ResponseEntity.status(HttpStatus.OK).body(response);
  }

  @Override
  @PostMapping
  public ResponseEntity<Void> vote(@Valid @RequestBody VoteRequest voteRequest) {
    logger.info("Request POST /votos");
    voteService.sendVote(voteRequest);
    logger.info("Response POST /votos - enviado para fila");

    return ResponseEntity.status(HttpStatus.CREATED).build();
  }

  @Override
  @GetMapping("/{voteId}")
  public ResponseEntity<VoteResponse> findById(@PathVariable final Long voteId) {
    logger.info("Request GET /votos/{}", voteId);
    final VoteDto voteDto = voteService.findById(voteId);
    final VoteResponse voteResponse = voteConverter.toResponse(voteDto);
    logger.info("Response POST /votos/{} - sucesso.", voteId);

    return ResponseEntity.status(HttpStatus.OK).body(voteResponse);
  }

  @Override
  @GetMapping("/associados/cpf/{memberCpf}")
  public ResponseEntity<VoteResponse> findByMemberCpf(@PathVariable String memberCpf) {
    logger.info("Request GET /votos/{}", memberCpf);
    final VoteResponse response = voteConverter.toResponse(voteService.findByMemberCpfToDto(memberCpf));
    logger.info("Response GET /votos/{} - {}", memberCpf, response);

    return ResponseEntity.status(HttpStatus.OK).body(response);
  }

  @Override
  @GetMapping("/sessoes/{sessionId}")
  public ResponseEntity<VoteListResponse> findBySessionId(@PathVariable final Long sessionId) {
    logger.info("Request GET /votos/sessoes/{}", sessionId);
    final VoteListResponse response = voteConverter.toListResponse(voteService.findBySessionId(sessionId));
    logger.info("Response GET /votos/sessoes/{} - {}", sessionId, response);

    return ResponseEntity.status(HttpStatus.OK).body(response);
  }

}
