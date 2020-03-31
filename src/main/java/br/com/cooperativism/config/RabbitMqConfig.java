package br.com.cooperativism.config;


import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;


@Component
public class RabbitMqConfig {

  @Value("${spring.rabbitmq.queue}")
  private String queueName;

  @Value("${spring.rabbitmq.exchange}")
  private String exchangeName;

  @Value("${spring.rabbitmq.deadQueue}")
  private String deadQueueName;

  @Bean
  Queue voteQueue() {
    return QueueBuilder.durable(queueName).build();
  }

  @Bean
  TopicExchange exchange() {
    return new TopicExchange(exchangeName);
  }

  @Bean
  Queue deadQueue() {
    return QueueBuilder.durable(deadQueueName).build();
  }

  @Bean
  Binding binding(final Queue voteQueue, final TopicExchange topicExchange) {
    return BindingBuilder.bind(voteQueue)
        .to(topicExchange)
        .with(queueName);
  }

  @Bean
  Binding bindingDeadQueue(final Queue deadQueue, final TopicExchange exchange) {
    return BindingBuilder.bind(deadQueue)
        .to(exchange)
        .with(deadQueueName);
  }

  @Bean
  RabbitTemplate container(final ConnectionFactory connectionFactory) {
    RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
    rabbitTemplate.setConnectionFactory(connectionFactory);
    rabbitTemplate.setExchange(exchangeName);
    rabbitTemplate.setMessageConverter(producerJackson2MessageConverter());

    return rabbitTemplate;
  }

  @Bean
  public Jackson2JsonMessageConverter producerJackson2MessageConverter() {
    return new Jackson2JsonMessageConverter();
  }

}
