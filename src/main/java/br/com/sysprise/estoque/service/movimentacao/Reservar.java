package br.com.sysprise.estoque.service.movimentacao;

import br.com.sysprise.estoque.model.DadosMovimentacao;
import br.com.sysprise.estoque.repository.EstoqueRepository;
import br.com.sysprise.estoque.service.RabbitmqSender;

import java.util.Arrays;

public class Reservar implements MovimentacaoEstoque {

    @Override
    public void executar(EstoqueRepository repository, DadosMovimentacao... dadosMovimentacao) {
        Arrays.stream(dadosMovimentacao).forEach(mov -> {
            repository.findByProdutoId(mov.produto_id())
                    .ifPresentOrElse(est -> {
                                try {
                                    est.reservarQuantidade(mov.quantidade());
                                } catch (RuntimeException ex) {
                                RabbitmqSender.enviarMensagem("my_direct_exchange", "estoque_insuficiente", mov.venda_id().toString());
                                }
                            },
                            () -> {
                                RabbitmqSender.enviarMensagem("my_direct_exchange", "estoque_insuficiente", mov.venda_id().toString());
                            });
        });
    }
}