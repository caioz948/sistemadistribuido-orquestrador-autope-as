package com.autopecas.ecommerce.dto;

public record NotificacaoRequestDTO(
	    String emailDestinatario,
	    String assunto,
	    String mensagem
	) {}