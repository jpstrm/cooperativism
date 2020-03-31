package br.com.cooperativism.messaging;

import br.com.cooperativism.converter.VoteConverter;
import br.com.cooperativism.model.Vote;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class VoteRunner {

  private Logger logger = LoggerFactory.getLogger(VoteRunner.class);

  @Value("${spring.rabbitmq.queue}")
  private String queueName;

  @Autowired
  private RabbitTemplate rabbitTemplate;

  @Autowired
  private VoteConverter voteConverter;

  @Async
  public void sendVote(final Vote vote) {
    logger.info("Enviando voto para a fila - {}", vote);
    rabbitTemplate.convertAndSend(queueName, voteConverter.fromVoteToMessage(vote));
    logger.info("Voto enviado com sucesso - {}", vote);
  }

}
