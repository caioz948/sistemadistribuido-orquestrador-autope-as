package com.autopecas.ecommerce.controller;

import com.autopecas.ecommerce.dto.ProdutoDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/catalogo")
public class CatalogoController {

    @GetMapping("/produtos")
    public List<ProdutoDTO> listarProdutos() {
        return List.of(
            new ProdutoDTO(1L, "Jogo de Velas de Ignição NGK", "NGK-BKR6E-11", new BigDecimal("149.90")),
            new ProdutoDTO(2L, "Jogo de Pastilhas de Freio Dianteiras Cobreq", "COBREQ-N-2030", new BigDecimal("189.50")),
            new ProdutoDTO(3L, "Amortecedor Dianteiro Cofap", "COFAP-GP32981", new BigDecimal("350.00")),
            new ProdutoDTO(4L, "Filtro de Óleo Mann-Filter", "MANN-W610/82", new BigDecimal("45.00")),
            new ProdutoDTO(5L, "Disco de Freio Ventilado Fremax (Par)", "FREMAX-BD4512", new BigDecimal("280.00"))
        );
    }
}