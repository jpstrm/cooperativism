package br.com.cooperativism.controller;

import br.com.cooperativism.controller.swagger.TopicApi;
import br.com.cooperativism.dto.TopicDto;
import br.com.cooperativism.request.TopicRequest;
import br.com.cooperativism.response.TopicListResponse;
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
import java.util.List;

@RestController
@RequestMapping("/pautas")
public class TopicController implements TopicApi {

  private Logger logger = LoggerFactory.getLogger(TopicController.class);

  @Autowired
  private TopicService topicService;

  @Override
  @GetMapping
  public ResponseEntity<TopicListResponse> findAll() {
    logger.info("Request GET /pautas");
    final List<TopicDto> topicDtos = topicService.findAll();
    final TopicListResponse response = new TopicListResponse(topicDtos.size(), topicDtos);
    logger.info("Response GET /pautas - tamanho: {}", topicDtos.size());

    return ResponseEntity.status(HttpStatus.OK).body(response);
  }

  @Override
  @PostMapping
  public ResponseEntity<Void> create(@Valid @RequestBody TopicRequest topicRequest) {
    logger.info("Request POST /pautas");
    topicService.create(topicRequest);
    logger.info("Response POST /pautas - msg: sucesso");

    return ResponseEntity.status(HttpStatus.CREATED).build();
  }

  @Override
  @GetMapping("/{topicName}")
  public ResponseEntity<TopicDto> findByName(@PathVariable String topicName) {
    logger.info("Request POST /pautas/{}", topicName);
    final TopicDto topicDto = topicService.findByNameToDto(topicName);
    logger.info("Response POST /pautas/{} - {}", topicName, topicDto);

    return ResponseEntity.status(HttpStatus.OK).body(topicDto);
  }


}
