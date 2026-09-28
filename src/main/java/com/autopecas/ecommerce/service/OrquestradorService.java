package com.autopecas.ecommerce.service;

import java.util.Map;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.autopecas.ecommerce.dto.CheckoutRequestDTO;
import com.autopecas.ecommerce.dto.CheckoutResponseDTO;
import com.autopecas.ecommerce.dto.NotificacaoRequestDTO;

@Service
public class OrquestradorService {

    private final RestTemplate restTemplate;
    
    
    private final String BASE_URL = "http://localhost:8080/api";

    public OrquestradorService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public CheckoutResponseDTO executarFluxoCheckout(CheckoutRequestDTO checkout) {
        String pedidoId = "PED-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
        System.out.println("\n%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
        System.out.println("🎬 INICIANDO ORQUESTRAÇÃO DE COMPRA | PEDIDO: " + pedidoId);
        System.out.println("¨&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&\n");

        try {
            // PASSO 6: Enviar e-mail de confirmação de recebimento da compra
            NotificacaoRequestDTO emailConfirmacao = new NotificacaoRequestDTO(
                checkout.emailCliente(),
                "Pedido Recebido - AutoPeças",
                "Olá " + checkout.nomeCliente() + ", recebemos seu pedido " + pedidoId + " e estamos processando!"
            );
            restTemplate.postForEntity(BASE_URL + "/notificacao/enviar-email", emailConfirmacao, Map.class);

            // PASSO 7: Realizar a transação de pagamento
            Map<String, Object> respPagamento = restTemplate.postForObject(
                BASE_URL + "/pagamento/processar", 
                checkout.dadosPagamento(), 
                Map.class
            );
            String statusPagamento = (String) respPagamento.get("status");

            // PASSO 8: Enviar e-mail com o resultado do pagamento
            NotificacaoRequestDTO emailPagamento = new NotificacaoRequestDTO(
                checkout.emailCliente(),
                "Status do Pagamento - Pedido " + pedidoId,
                "Seu pagamento foi processado com o status: " + statusPagamento
            );
            restTemplate.postForEntity(BASE_URL + "/notificacao/enviar-email", emailPagamento, Map.class);

            // PASSO 9: Gerar nota fiscal e dar baixa no estoque de peças
            Map<String, Object> reqEstoque = Map.of(
                "pedidoId", pedidoId,
                "itens", checkout.itens()
            );
            Map<String, Object> respEstoque = restTemplate.postForObject(
                BASE_URL + "/estoque/baixa-e-nf", 
                reqEstoque, 
                Map.class
            );
            String notaFiscalId = (String) respEstoque.get("notaFiscalId");

            // PASSO 10: Enviar e-mail com a nota fiscal
            NotificacaoRequestDTO emailNF = new NotificacaoRequestDTO(
                checkout.emailCliente(),
                "Nota Fiscal do Pedido " + pedidoId,
                "Sua Nota Fiscal de autopeças foi emitida com sucesso! Número: " + notaFiscalId
            );
            restTemplate.postForEntity(BASE_URL + "/notificacao/enviar-email", emailNF, Map.class);

            // PASSO 11: Disponibilizar produtos para entrega (Logística)
            Map<String, Object> reqLogistica = Map.of(
                "pedidoId", pedidoId,
                "endereco", checkout.enderecoEntrega()
            );
            Map<String, Object> respLogistica = restTemplate.postForObject(
                BASE_URL + "/logistica/agendar", 
                reqLogistica, 
                Map.class
            );
            String codigoRastreio = (String) respLogistica.get("codigoRastreio");

            // PASSO 12: Enviar e-mail com dados da entrega
            NotificacaoRequestDTO emailEntrega = new NotificacaoRequestDTO(
                checkout.emailCliente(),
                "Envio do Pedido " + pedidoId,
                "Suas peças de carro foram despachadas! Código de Rastreio: " + codigoRastreio
            );
            restTemplate.postForEntity(BASE_URL + "/notificacao/enviar-email", emailEntrega, Map.class);

            System.out.println("✅ ORQUESTRAÇÃO FINALIZADA COM SUCESSO!\n");

            // Retorno final do orquestrador
            return new CheckoutResponseDTO(
                pedidoId,
                "CONCLUIDO",
                notaFiscalId,
                codigoRastreio,
                "Compra processada e entregas agendadas com sucesso!"
            );

        } catch (Exception e) {
            System.err.println("❌ FALHA NA ORQUESTRAÇÃO: " + e.getMessage());
            return new CheckoutResponseDTO(
                pedidoId,
                "ERRO",
                null,
                null,
                "Falha no processamento: " + e.getMessage()
            );
        }
    }
}