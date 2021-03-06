package br.com.cooperativism.controller;

import br.com.cooperativism.controller.swagger.SessionApi;
import br.com.cooperativism.converter.SessionConverter;
import br.com.cooperativism.dto.SessionDto;
import br.com.cooperativism.request.SessionRequest;
import br.com.cooperativism.response.session.SessionListResponse;
import br.com.cooperativism.response.session.SessionResponse;
import br.com.cooperativism.service.SessionService;
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
import java.util.List;

@RestController
@RequestMapping("/sessoes")
public class SessionController implements SessionApi {

  private Logger logger = LoggerFactory.getLogger(SessionController.class);

  @Autowired
  private SessionService sessionService;

  @Autowired
  private SessionConverter sessionConverter;

  @Override
  @GetMapping
  public ResponseEntity<SessionListResponse> findAll() {
    logger.info("Request GET /sessoes");
    final List<SessionDto> sessionDtos = sessionService.findAll();
    final SessionListResponse response =  sessionConverter.toListResponse(sessionDtos);
    logger.info("Response GET /sessoes - tamanho: {}", sessionDtos.size());

    return ResponseEntity.status(HttpStatus.OK).body(response);
  }

  @Override
  @PostMapping
  public ResponseEntity<Void> create(@Valid @RequestBody final SessionRequest sessionRequest) {
    logger.info("Request POST /sessoes");
    sessionService.create(sessionRequest);
    logger.info("Response POST /sessoes - msg: sucesso");

    return ResponseEntity.status(HttpStatus.CREATED).build();
  }

  @Override
  @GetMapping("/pautas/{topicId}")
  public ResponseEntity<SessionResponse> findByTopicId(@PathVariable final Long topicId) {
    logger.info("Request GET /sessoes/pautas/{}", topicId);
    final SessionResponse response = sessionConverter.toResponse(sessionService.findByTopicIdToDto(topicId));
    logger.info("Response GET /sessoes/pautas/{} - msg: sucesso", topicId);

    return ResponseEntity.status(HttpStatus.OK).body(response);
  }

}
