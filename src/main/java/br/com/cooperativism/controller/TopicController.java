package br.com.cooperativism.controller;

import br.com.cooperativism.controller.swagger.TopicApi;
import br.com.cooperativism.converter.TopicConverter;
import br.com.cooperativism.dto.TopicDto;
import br.com.cooperativism.request.TopicRequest;
import br.com.cooperativism.response.topic.TopicListResponse;
import br.com.cooperativism.service.TopicService;
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
@RequestMapping("/pautas")
public class TopicController implements TopicApi {

  private Logger logger = LoggerFactory.getLogger(TopicController.class);

  @Autowired
  private TopicService topicService;

  @Autowired
  private TopicConverter topicConverter;

  @Override
  @GetMapping
  public ResponseEntity<TopicListResponse> findAll() {
    logger.info("Request GET /pautas");
    final TopicListResponse response = topicConverter.toListResponse(topicService.findAllToDto());
    logger.info("Response GET /pautas - tamanho: {}", response.getTotal());

    return ResponseEntity.status(HttpStatus.OK).body(response);
  }

  @Override
  @PostMapping
  public ResponseEntity<Void> create(@Valid @RequestBody final TopicRequest topicRequest) {
    logger.info("Request POST /pautas");
    topicService.create(topicRequest);
    logger.info("Response POST /pautas - msg: sucesso");

    return ResponseEntity.status(HttpStatus.CREATED).build();
  }

  @Override
  @GetMapping("/{topicId}")
  public ResponseEntity<TopicDto> findById(@PathVariable final Long topicId) {
    logger.info("Request GET /pautas/{}", topicId);
    final TopicDto response = topicService.findByIdToDto(topicId);
    logger.info("Response GET /pautas/{} - {}", topicId, response);

    return ResponseEntity.status(HttpStatus.OK).body(response);
  }

  @Override
  @GetMapping("/nome/{topicName}")
  public ResponseEntity<TopicDto> findByName(@PathVariable final String topicName) {
    logger.info("Request GET /pautas/{}", topicName);
    final TopicDto response = topicService.findByNameToDto(topicName);
    logger.info("Response GET /pautas/{} - {}", topicName, response);

    return ResponseEntity.status(HttpStatus.OK).body(response);
  }


}
