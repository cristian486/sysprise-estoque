package br.com.sysprise.estoque.controller;

import br.com.sysprise.estoque.model.DadosListagemEstoque;
import br.com.sysprise.estoque.service.EstoqueService;
import lombok.AllArgsConstructor;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pb.ProdutoId;
import pb.ProdutoServiceGrpc;

@CrossOrigin
@RestController
@RequestMapping("/estoque")
public class EstoqueController {

    @Autowired
    private EstoqueService estoqueService;

    @GrpcClient("produto")
    private ProdutoServiceGrpc.ProdutoServiceBlockingStub produtoStub;

    @GetMapping
    public ResponseEntity<Page<DadosListagemEstoque>> listar(@PageableDefault(sort = "id", size = 5) Pageable pageable) {
        Page<DadosListagemEstoque> dadosListagem = estoqueService.listar(pageable).map(e -> {
            String nomeDoProduto = produtoStub.getProductname(ProdutoId.newBuilder().setProdutoId(e.getProdutoId()).build()).getNome();
            return new DadosListagemEstoque(e.getId(), nomeDoProduto, e.estoqueDisponivel(), e.getReservado(), e.getQuantidade());
        });
        return ResponseEntity.status(HttpStatus.OK).body(dadosListagem);
    }
}
