package com.autopecas.ecommerce.controller;

import com.autopecas.ecommerce.dto.CheckoutRequestDTO;
import com.autopecas.ecommerce.dto.CheckoutResponseDTO;
import com.autopecas.ecommerce.service.OrquestradorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/checkout")
public class OrquestradorCheckoutController {

    private final OrquestradorService orquestradorService;

    public OrquestradorCheckoutController(OrquestradorService orquestradorService) {
        this.orquestradorService = orquestradorService;
    }

    @PostMapping
    public ResponseEntity<CheckoutResponseDTO> finalizarCompra(@RequestBody CheckoutRequestDTO checkoutRequest) {
        CheckoutResponseDTO resposta = orquestradorService.executarFluxoCheckout(checkoutRequest);
        return ResponseEntity.ok(resposta);
    }
}