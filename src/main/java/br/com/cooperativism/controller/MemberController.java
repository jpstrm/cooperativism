package br.com.cooperativism.controller;

import br.com.cooperativism.controller.swagger.MemberApi;
import br.com.cooperativism.dto.MemberDto;
import br.com.cooperativism.response.MemberListResponse;
import br.com.cooperativism.service.MemberService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/associados")
public class MemberController implements MemberApi {

  private Logger logger = LoggerFactory.getLogger(MemberController.class);

  @Autowired
  private MemberService memberService;

  @Override
  @GetMapping
  public ResponseEntity<MemberListResponse> findAll() {
    logger.info("Request GET /associados");
    final List<MemberDto> memberDtos = memberService.findAll();
    final MemberListResponse response = new MemberListResponse(memberDtos.size(), memberDtos);
    logger.info("Response GET /associados - tamanho: {}", memberDtos.size());

    return ResponseEntity.status(HttpStatus.OK).body(response);
  }

}
