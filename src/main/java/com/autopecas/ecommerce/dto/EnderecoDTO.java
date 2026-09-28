package com.autopecas.ecommerce.dto;

public record EnderecoDTO(
	    String cep,
	    String logradouro,
	    String bairro,
	    String cidade,
	    String uf,
	    String numero,
	    String complemento
	) {}