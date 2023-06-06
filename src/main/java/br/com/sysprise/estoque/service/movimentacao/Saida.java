package br.com.sysprise.estoque.service.movimentacao;

import br.com.sysprise.estoque.model.DadosMovimentacao;
import br.com.sysprise.estoque.repository.EstoqueRepository;

import java.util.Arrays;

public class Saida implements MovimentacaoEstoque {
    @Override
    public void executar(EstoqueRepository repository, DadosMovimentacao... dadosMovimentacao) {

        Arrays.stream(dadosMovimentacao).forEach(mov -> {
            repository.findByProdutoId(mov.produto_id())
                    .orElseThrow(() -> new IllegalArgumentException("Não foi encontrado um registro para o produto com ID " + mov.produto_id()))
                    .descontarQuantidade(mov.quantidade());
        });
    }
}
