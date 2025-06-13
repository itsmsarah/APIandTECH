/*package com.techrep.apitechrep.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.techrep.apitechrep.entity.LocacaoEntity;
import com.techrep.apitechrep.repository.LocacaoRepository;

@RestController
@RequestMapping("api/locacao")
public class LocacaoController {

    private final LocacaoRepository locacaoRepository;

    public LocacaoController(LocacaoRepository locacaoRepository) {
        this.locacaoRepository = locacaoRepository;
    }

    @PostMapping
    public LocacaoEntity criarLocacao(@RequestBody LocacaoEntity locacao) {
        return locacaoRepository.save(locacao);
    }
} */

package com.techrep.apitechrep.controller;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.techrep.apitechrep.entity.LocacaoEntity;
import com.techrep.apitechrep.repository.LocacaoRepository;
import com.techrep.apitechrep.repository.ClientesRepository;
import com.techrep.apitechrep.repository.ProdutoRepository;
import com.techrep.apitechrep.entity.ProdutoEntity;

import com.techrep.apitechrep.entity.ClientesEntity;

@RestController
@RequestMapping("api/locacao")
public class LocacaoController {

    private final LocacaoRepository locacaoRepository;
    private final ClientesRepository clienteRepository;
    private final ProdutoRepository produtoRepository;

    public LocacaoController(LocacaoRepository locacaoRepository, ClientesRepository clienteRepository, ProdutoRepository produtoRepository) {
        this.locacaoRepository = locacaoRepository;
        this.clienteRepository = clienteRepository;
        this.produtoRepository = produtoRepository;
    }

    @PostMapping
    public LocacaoEntity criarLocacao(@RequestBody LocacaoEntity locacao) {
        // 1. Verificar se cliente é físico e limitar a 1 item
        ClientesEntity cliente = clienteRepository.findById(locacao.getClientesId())
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
        
        if (cliente.getCpf() != null && locacao.getQuantidade() > 1) {
            throw new RuntimeException("Clientes com CPF (físicos) só podem alugar 1 notebook por vez.");
        }


        // 2. Verificar disponibilidade
        List<LocacaoEntity> conflitos = locacaoRepository.findByProdutoIdAndDataFimAfterAndDataInicioBefore(
                locacao.getProdutoId(),
                locacao.getDataInicio(),
                locacao.getDataFim());

        if (!conflitos.isEmpty()) {
            locacao.setStatusLocacao("INDISPONIVEL");
        } else {
            locacao.setStatusLocacao("DISPONIVEL");
        }

        // 3. Calcular valor da locação (se você tiver um campo para isso)
        com.techrep.apitechrep.controller.ProdutoController produto = produtoRepository.findById(locacao.getProdutoId())
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

        BigDecimal precoDia = produto.getPrecoDia(); // Assumindo que existe esse campo

        long dias = ChronoUnit.DAYS.between(
                locacao.getDataInicio().toLocalDate(), 
                locacao.getDataFim().toLocalDate());
        dias = dias == 0 ? 1 : dias;

        BigDecimal total = precoDia.multiply(BigDecimal.valueOf(dias))
                                   .multiply(BigDecimal.valueOf(locacao.getQuantidade()));

        // Você pode salvar esse valor em outro campo, ou retornar como parte do DTO
        //System.out.println("Valor total da locação: R$ " + total);

        return locacaoRepository.save(locacao);
    }
}

