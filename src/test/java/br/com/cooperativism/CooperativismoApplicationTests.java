package br.com.cooperativism;

import br.com.cooperativism.config.RabbitMqConfig;
import br.com.cooperativism.messaging.VoteListener;
import br.com.cooperativism.messaging.VoteRunner;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
class CooperativismoApplicationTests {

  @MockBean
  private RabbitTemplate rabbitTemplate;

  @MockBean
  private RabbitMqConfig rabbitMqConfig;

  @MockBean
  private VoteListener voteListener;

  @MockBean
  private VoteRunner voteRunner;

  @Test
  void contextLoads() {
  }

}
