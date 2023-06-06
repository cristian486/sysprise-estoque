package br.com.sysprise.estoque.model;

import java.io.Serializable;

public record DadosMovimentacao(Long venda_id, Long produto_id, Double quantidade, Movimentacao movimentacao) implements Serializable {
}
