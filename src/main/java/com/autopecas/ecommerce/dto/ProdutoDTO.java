package com.autopecas.ecommerce.dto;



import java.math.BigDecimal;

public record ProdutoDTO(
    Long id,
    String nome,
    String codigoFabricante, // Ex: "NGK-BKR6E", "COBREQ-N-123"
    BigDecimal preco
) {}