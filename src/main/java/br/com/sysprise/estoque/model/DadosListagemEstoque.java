package br.com.sysprise.estoque.model;

public record DadosListagemEstoque(Long id, String nomeDoProduto, Double estoque, Double reservado, Double estoqueTotal) {


    public DadosListagemEstoque(Estoque estoque) {
        this(estoque.getId(), "", estoque.estoqueDisponivel(), estoque.getReservado(), estoque.getQuantidade());
    }
}
