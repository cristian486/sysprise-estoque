package br.com.sysprise.estoque.service;

import br.com.sysprise.estoque.model.Estoque;
import br.com.sysprise.estoque.repository.EstoqueRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EstoqueService {

    private final EstoqueRepository estoqueRepository;

    public Page<Estoque> listar(Pageable pageable) {
        return estoqueRepository.findAll(pageable);
    }

}
