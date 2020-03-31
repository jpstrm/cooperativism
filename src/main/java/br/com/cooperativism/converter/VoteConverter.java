package br.com.cooperativism.converter;

import br.com.cooperativism.dto.VoteDto;
import br.com.cooperativism.messaging.VoteRunner;
import br.com.cooperativism.model.Member;
import br.com.cooperativism.model.Vote;
import br.com.cooperativism.request.VoteRequest;
import br.com.cooperativism.response.vote.VoteListResponse;
import br.com.cooperativism.response.vote.VoteResponse;
import br.com.cooperativism.service.SessionService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.List;

@Component
public class VoteConverter extends DefaultConverter<Vote, VoteDto> {

  private Logger logger = LoggerFactory.getLogger(VoteRunner.class);

  @Autowired
  private SessionService sessionService;

  @Autowired
  private ObjectMapper objectMapper;

  @PostConstruct
  public void setup() {
    this.setClazz(Vote.class);
    this.setDtoClazz(VoteDto.class);
  }

  public Vote fromRequest(VoteRequest voteRequest) {
    final Vote vote = new Vote();
    vote.setMember(new Member(voteRequest.getMemberCpf()));
    vote.setSession(sessionService.findValidByTopicId(voteRequest.getTopicId()));
    vote.setVote(voteRequest.getVoteEnum());

    return vote;
  }

  public VoteResponse toResponse(VoteDto voteDto) {
    return toAny(voteDto, VoteResponse.class);
  }


  public VoteListResponse toListResponse(final List<Vote> votes) {
    final List<VoteResponse> votesResponse = toList(votes, VoteResponse.class);

    return new VoteListResponse(votesResponse.size(), votesResponse);
  }

  public Message fromVoteToMessage(final Vote vote) {
    try {
      final String content = objectMapper.writeValueAsString(vote);

      return MessageBuilder
          .withBody(content.getBytes())
          .setContentType(MessageProperties.CONTENT_TYPE_JSON)
          .build();
    } catch (final JsonProcessingException ex) {
      logger.error("Erro ao converter Voto - {}", ex.getLocalizedMessage());

      return null;
    }
  }

  public Vote fromMessage(final Message message) {

    try {
      return objectMapper.readValue(message.getBody(), Vote.class);
    } catch (final IOException ex) {
      final String msg = "Erro ao converter o Voto da fila - ";
      logger.error("{} {} - {}", msg, message.getBody(), ex.getLocalizedMessage());

      throw new IllegalArgumentException(msg.concat(ex.getLocalizedMessage()));
    }
  }

}
