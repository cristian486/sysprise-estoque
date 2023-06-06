package br.com.sysprise.estoque.infra.rabbitmq;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfig {

    // CODIGO RESPONSÁVEL POR CRIAR AUTOMATICAMENTE UMA FILA
    @Value("${spring.rabbitmq.queue}")
    private String queueMovimentacaoEstoque;

    @Value("${spring.rabbitmq.exchange_name}")
    private String exchangeName;

    @Value("${spring.rabbitmq.queue_estoque_insuficiente}")
    private String queueEstoqueInsuficiente;



    @Bean
    public Queue queueMovimentacaoEstoque() {
        return new Queue(queueMovimentacaoEstoque, true);
    }

    @Bean
    public Queue queueEstoqueInsuficiente() {
        return new Queue(queueEstoqueInsuficiente, true);
    }

    // ESTE BEAN SERVE PARA ADICIONAR UMA CLASSE QUE PERMITE A CONVERSÃO DO PAYLOAD DA MENSAGEM DO RABBIT PARA UM CLASSE
    @Bean
    public Jackson2JsonMessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    // PARA CRIAR UMA EXCHANGE
    @Bean
    DirectExchange directExchange() {
        return new DirectExchange(exchangeName);
    }

    // PARA FAZE O BIND DO QUEUE A EXCHANGE
    @Bean
    Binding bindQueueMovimentacaoEstoque(@Value("${spring.rabbitmq.routing_key}") String routingKey) {
        return BindingBuilder.bind(queueMovimentacaoEstoque()).to(directExchange()).with(routingKey);
    }

    @Bean
    Binding bindQueueEstoqueInsuficiente(@Value("${spring.rabbitmq.routing_key_estoque_insuficiente}") String routingKey) {
        return BindingBuilder.bind(queueEstoqueInsuficiente()).to(directExchange()).with(routingKey);
    }

    @Bean
    public RabbitTemplate rabbitTemplate(final ConnectionFactory connectionFactory) {
        final var rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(messageConverter());
        return rabbitTemplate;
    }
}
