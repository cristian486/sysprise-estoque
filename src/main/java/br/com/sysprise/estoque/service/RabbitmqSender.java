package br.com.sysprise.estoque.service;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RabbitmqSender {


    private static RabbitTemplate rabbitTemplate;

    @Autowired
    public RabbitmqSender(RabbitTemplate rabbitTemplate) {
        RabbitmqSender.rabbitTemplate = rabbitTemplate;
    }

    public static void enviarMensagem(String exchange, String routinKey, String message) {
        rabbitTemplate.convertAndSend(exchange, routinKey, message);
    }
}
