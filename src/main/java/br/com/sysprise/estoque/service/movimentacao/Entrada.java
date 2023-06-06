package br.com.sysprise.estoque.service.movimentacao;

import br.com.sysprise.estoque.model.DadosMovimentacao;
import br.com.sysprise.estoque.model.Estoque;
import br.com.sysprise.estoque.repository.EstoqueRepository;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class Entrada implements MovimentacaoEstoque {

    @Override
    public void executar(EstoqueRepository repository, DadosMovimentacao... dadosMovimentacao) {
        Arrays.stream(dadosMovimentacao).forEach(mov -> {
            repository.findByProdutoId(mov.produto_id())
                    .ifPresentOrElse(estoque -> estoque.adicionarQuantidade(mov.quantidade()),
                            () -> {
                                Estoque estoque = new Estoque(null, mov.produto_id(), mov.quantidade(), 0d);
                                repository.save(estoque);
                            });
        });
    }
}
