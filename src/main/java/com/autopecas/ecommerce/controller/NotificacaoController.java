package com.autopecas.ecommerce.controller;

import com.autopecas.ecommerce.dto.NotificacaoRequestDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/notificacao")
public class NotificacaoController {

    @PostMapping("/enviar-email")
    public ResponseEntity<Map<String, Object>> enviarEmail(@RequestBody NotificacaoRequestDTO notificacao) {
        System.out.println("\n📧 [SERVIÇO NOTIFICAÇÃO - ENVIANDO E-MAIL]");
        System.out.println("   Para: " + notificacao.emailDestinatario());
        System.out.println("   Assunto: " + notificacao.assunto());
        System.out.println("   Mensagem: " + notificacao.mensagem());
        System.out.println("--------------------------------------------------\n");

        return ResponseEntity.ok(Map.of(
            "enviado", true,
            "mensagem", "E-mail simulado enviado com sucesso para " + notificacao.emailDestinatario()
        ));
    }
}