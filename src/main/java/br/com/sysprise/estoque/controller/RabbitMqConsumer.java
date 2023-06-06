package br.com.sysprise.estoque.controller;

import br.com.sysprise.estoque.model.DadosMovimentacao;
import br.com.sysprise.estoque.service.movimentacao.MovimentacaoEstoque;
import br.com.sysprise.estoque.service.movimentacao.RealizarMovimentacao;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class RabbitMqConsumer {

    private final RealizarMovimentacao movimentacao;

    @Transactional
    @RabbitListener(queues = "${spring.rabbitmq.queue}")
    public void teste(@Payload DadosMovimentacao dadosMovimentacao) {
        MovimentacaoEstoque tipoDaMovimentacao = (MovimentacaoEstoque) dadosMovimentacao.movimentacao().retornarClasseMovimentacao();
        movimentacao.executar( tipoDaMovimentacao, dadosMovimentacao);
    }
}
