package com.autopecas.ecommerce.controller;

import com.autopecas.ecommerce.dto.PagamentoDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/pagamento")
public class PagamentoController {

    @PostMapping("/processar")
    public ResponseEntity<Map<String, Object>> processarPagamento(@RequestBody PagamentoDTO pagamento) {
        String transacaoId = "TX-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
        System.out.println("[SERVIÇO PAGAMENTO] Processando " + pagamento.valorTotal() + " | Transação: " + transacaoId);

        Map<String, Object> resposta = Map.of(
            "transacaoId", transacaoId,
            "status", "APROVADO",
            "mensagem", "Transação autorizada com sucesso pela operadora do cartão."
        );

        return ResponseEntity.ok(resposta);
    }
}