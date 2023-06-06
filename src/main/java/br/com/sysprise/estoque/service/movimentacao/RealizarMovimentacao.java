package br.com.sysprise.estoque.service.movimentacao;

import br.com.sysprise.estoque.model.DadosMovimentacao;
import br.com.sysprise.estoque.repository.EstoqueRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class RealizarMovimentacao {

    private final EstoqueRepository repository;

    public void executar(MovimentacaoEstoque movimentacaoEstoque, DadosMovimentacao... dadosMovimentacao) {
        if(dadosMovimentacao.length == 0) return;

        movimentacaoEstoque.executar(repository, dadosMovimentacao);
    }
}
