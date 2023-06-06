package br.com.sysprise.estoque.model;

import br.com.sysprise.estoque.service.movimentacao.Entrada;
import br.com.sysprise.estoque.service.movimentacao.Reservar;
import br.com.sysprise.estoque.service.movimentacao.Saida;

public enum Movimentacao {

    ENTRADA {
        @Override
        public Object retornarClasseMovimentacao() {
            return new Entrada();
        }
    },
    SAIDA {
        @Override
        public Object retornarClasseMovimentacao() {
            return new Saida();
        }
    },
    RESERVAR {
        @Override
        public Object retornarClasseMovimentacao() {
            return new Reservar();
        }
    };

    public abstract Object retornarClasseMovimentacao();
}
