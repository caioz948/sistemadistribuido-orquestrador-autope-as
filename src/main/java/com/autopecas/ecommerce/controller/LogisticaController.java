package com.autopecas.ecommerce.controller;

import com.autopecas.ecommerce.dto.EnderecoDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/logistica")
public class LogisticaController {

    @PostMapping("/agendar")
    public ResponseEntity<Map<String, Object>> agendarEntrega(@RequestBody Map<String, Object> requisicao) {
        String pedidoId = (String) requisicao.get("pedidoId");
        String codigoRastreio = "BR" + UUID.randomUUID().toString().substring(0, 9).toUpperCase() + "AUTO";

        System.out.println("[SERVIÇO LOGÍSTICA] Pedido " + pedidoId + " despachado para transportadora. Rastreio: " + codigoRastreio);

        Map<String, Object> resposta = Map.of(
            "codigoRastreio", codigoRastreio,
            "transportadora", "AutoPeças Express / Correios",
            "prazoDiasUteis", 3,
            "status", "DISPONIBILIZADO_PARA_ENTREGA"
        );

        return ResponseEntity.ok(resposta);
    }
}