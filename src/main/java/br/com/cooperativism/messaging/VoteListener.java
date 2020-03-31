package br.com.cooperativism.messaging;

import br.com.cooperativism.converter.VoteConverter;
import br.com.cooperativism.model.Vote;
import br.com.cooperativism.service.VoteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class VoteListener {

  private Logger logger = LoggerFactory.getLogger(VoteListener.class);

  @Autowired
  private VoteService voteService;

  @Autowired
  private VoteConverter voteConverter;

  @RabbitListener(queues = "${spring.rabbitmq.queue}")
  public void primary(Message voteIn) {
    logger.info("Computando voto: " + voteIn.toString());
    final Vote vote = voteConverter.fromMessage(voteIn);
    voteService.vote(vote);
    logger.info("Voto computado com sucesso - {}", vote);
  }

}
