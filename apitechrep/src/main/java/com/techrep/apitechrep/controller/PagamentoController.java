/*package com.techrep.apitechrep.controller;


import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.techrep.apitechrep.entity.PagamentoEntity;
import com.techrep.apitechrep.repository.PagamentoRepository;

@RestController
@RequestMapping("api/pagamentos")
public class PagamentoController {

    @Autowired
    private PagamentoRepository pagamentoRepository;

    @PostMapping
    
    public ResponseEntity<String> criarPagamentos(@RequestBody PagamentoEntity pagamento) {
        pagamento.setDataPagamento(LocalDateTime.now()); //coloca a data e hora atual
        pagamentoRepository.save(pagamento); // salva no banco de dados
        return ResponseEntity.ok("Pagamento realizado com sucesso!");
    }
    
    //aqui vai buscar os pagamentos feito por um cliente
    
    @GetMapping("/cliente/{clientesId}")
    public ResponseEntity<List<PagamentoEntity>> buscarPagamentosPorCliente(@PathVariable int clientesId) {
        List<PagamentoEntity> pagamentos = pagamentoRepository.findByCliente_ClientesId(clientesId);
        return ResponseEntity.ok(pagamentos);
    }
}
*/

package com.techrep.apitechrep.controller;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.techrep.apitechrep.entity.PagamentoEntity;
import com.techrep.apitechrep.entity.LocacaoEntity;
import com.techrep.apitechrep.repository.PagamentoRepository;
import com.techrep.apitechrep.repository.LocacaoRepository;

@RestController
@RequestMapping("api/pagamentos")
public class PagamentoController {

    @Autowired
    private PagamentoRepository pagamentoRepository;

    @Autowired
    private LocacaoRepository locacaoRepository;

    @PostMapping
    public ResponseEntity<String> criarPagamentos(@RequestBody PagamentoEntity pagamento) {
        if (pagamento.getLocacao() == null || pagamento.getLocacao().getLocacaoId() == 0) {
            return ResponseEntity.badRequest().body("Locação obrigatória para o pagamento");
        }

        // Buscar a locação no banco
        LocacaoEntity locacao = locacaoRepository.findById(pagamento.getLocacao().getLocacaoId())
                .orElseThrow(() -> new RuntimeException("Locação não encontrada"));

        // Calcular dias
        long dias = ChronoUnit.DAYS.between(
                locacao.getDataInicio().toLocalDate(),
                locacao.getDataFim().toLocalDate());
        dias = dias == 0 ? 1 : dias;

        // Calcular valor
        double total = locacao.getPrecoDia() * dias * locacao.getQuantidade();

        // Preencher pagamento
        pagamento.setValorTotal(total);
        pagamento.setDataPagamento(LocalDateTime.now());

        pagamentoRepository.save(pagamento);

        return ResponseEntity.ok("Pagamento no valor de R$ " + total + " realizado com sucesso!");
    }

    @GetMapping("/cliente/{clientesId}")
    public ResponseEntity<List<PagamentoEntity>> buscarPagamentosPorCliente(@PathVariable int clientesId) {
        List<PagamentoEntity> pagamentos = pagamentoRepository.findByCliente_ClientesId(clientesId);
        return ResponseEntity.ok(pagamentos);
    }
}
