package com.autopecas.ecommerce.dto;

public record CheckoutResponseDTO(
	    String pedidoId,
	    String status,
	    String notaFiscalId,
	    String codigoRastreio,
	    String mensagem
	) {}