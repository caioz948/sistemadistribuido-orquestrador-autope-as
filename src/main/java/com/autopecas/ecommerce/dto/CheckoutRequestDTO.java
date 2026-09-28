package com.autopecas.ecommerce.dto;

import java.util.List;

public record CheckoutRequestDTO(
    String nomeCliente,
    String emailCliente,
    List<ProdutoDTO> itens,
    PagamentoDTO dadosPagamento,
    EnderecoDTO enderecoEntrega
) {}