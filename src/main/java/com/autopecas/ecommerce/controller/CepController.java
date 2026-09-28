package com.autopecas.ecommerce.controller;

import com.autopecas.ecommerce.dto.EnderecoDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/cep")
public class CepController {

    @GetMapping("/{cep}")
    public ResponseEntity<EnderecoDTO> consultarCep(@PathVariable String cep) {
        System.out.println("[SERVIÇO CEP] Consultando CEP: " + cep);
        
        // Simulação de resposta de busca por CEP
        EnderecoDTO endereco = new EnderecoDTO(
            cep,
            "Avenida Parigot de Souza",
            "Centro",
            "Toledo",
            "PR",
            "",
            ""
        );
        return ResponseEntity.ok(endereco);
    }
}