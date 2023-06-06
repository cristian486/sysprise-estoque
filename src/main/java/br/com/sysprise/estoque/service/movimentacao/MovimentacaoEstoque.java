package br.com.sysprise.estoque.service.movimentacao;

import br.com.sysprise.estoque.model.DadosMovimentacao;
import br.com.sysprise.estoque.repository.EstoqueRepository;

public interface MovimentacaoEstoque {

    void executar(EstoqueRepository repository, DadosMovimentacao... dadosMovimentacao);
}
