package com.autopecas.ecommerce.controller;

import com.autopecas.ecommerce.dto.ProdutoDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/estoque")
public class EstoqueController {

    @PostMapping("/baixa-e-nf")
    public ResponseEntity<Map<String, Object>> darBaixaEEmitirNF(@RequestBody Map<String, Object> requisicao) {
        String pedidoId = (String) requisicao.get("pedidoId");
        String nfId = "NF-2026-" + UUID.randomUUID().toString().substring(0, 6).toUpperCase();

        System.out.println("[SERVIÇO ESTOQUE/FISCAL] Baixa no estoque efetuada e NF gerada (" + nfId + ") para pedido: " + pedidoId);

        Map<String, Object> resposta = Map.of(
            "notaFiscalId", nfId,
            "status", "EMITIDA",
            "chaveAcesso", "412609" + UUID.randomUUID().toString().replaceAll("-", "")
        );

        return ResponseEntity.ok(resposta);
    }
}