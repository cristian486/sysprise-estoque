package br.com.sysprise.estoque.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = {"produtoId"})
public class Estoque {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long produtoId;
    private Double quantidade;
    private Double reservado;

    public Double estoqueDisponivel() {
        return this.quantidade - this.reservado;
    }

    public void adicionarQuantidade(Double quantidade) {
        this.quantidade += Math.abs(quantidade);
    }

    public void descontarQuantidade(Double quantidade) {
        Double quantidadeSerRetirada = Math.abs(quantidade);

        if((this.reservado.equals(quantidadeSerRetirada)) || (this.reservado > quantidadeSerRetirada)) {
            this.quantidade -= quantidadeSerRetirada;
            this.reservado -= quantidadeSerRetirada;
        } else {
            double temp = this.reservado;
            this.reservado = 0d;
            this.quantidade -= (quantidadeSerRetirada - temp);
        }
    }

    public void reservarQuantidade(Double quantidade) {
        Double quantidadeSerReservada = Math.abs(quantidade);

        if((this.reservado + quantidadeSerReservada) > this.quantidade)
            throw new RuntimeException("A quantidade a ser reservada resulta em uma quantidade maior do que a quantidade do estoque");

        this.reservado += Math.abs(quantidade);
    }
}
