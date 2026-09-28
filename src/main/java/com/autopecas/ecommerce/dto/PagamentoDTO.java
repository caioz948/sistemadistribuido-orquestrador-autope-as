package com.autopecas.ecommerce.dto;

import java.math.BigDecimal;

public record PagamentoDTO(
    BigDecimal valorTotal,
    String numeroCartao,
    String nomeTitular,
    String validade,
    String cvv
) {}