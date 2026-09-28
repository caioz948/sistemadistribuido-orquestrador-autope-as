# sistemadistribuido-orquestrador-autope-as
# 🏎️ E-Commerce de Autopeças - Solução Didática de Orquestração de Serviços

Projeto desenvolvido em **Java 17** e **Spring Boot 3** simulando uma arquitetura de e-commerce de autopeças integrada via **Orquestração de Microserviços**. 

A aplicação centraliza o fluxo de checkout em um serviço maestro (`OrquestradorService`), que coordena chamadas síncronas para múltiplos serviços/APIs independentes (Pagamento, Estoque/NF, Logística e Notificação).

---

## 🛠️ Tecnologias Utilizadas

* **Java 17**
* **Spring Boot 3.x**
* **Spring Web (REST APIs & RestTemplate)**
* **Java Records (DTOs)**: Modelagem de dados imutável sem necessidade de bibliotecas externas (Lombok).
* **Maven**: Gerenciamento de dependências e build.

---

## 🏛️ Arquitetura do Sistema

A aplicação é dividida em duas camadas principais:

1. **Web APIs Simuladas (`Controllers`):** Módulos que expõem endpoints HTTP REST para simular os domínios do negócio:
   * `/api/catalogo`: Consulta de peças de veículos.
   * `/api/cep`: Validação e busca de endereços.
   * `/api/pagamento`: Processamento e autorização de cartão de crédito.
   * `/api/estoque`: Baixa de itens e emissão de Nota Fiscal.
   * `/api/logistica`: Agendamento de frete e geração de rastreio.
   * `/api/notificacao`: Disparo de e-mails de confirmação.

2. **Orquestrador Central (`OrquestradorService`):** Recebe a requisição do cliente (`POST /api/checkout`) e executa a sequência de chamadas síncronas via `RestTemplate`, garantindo que cada etapa dependa do sucesso da anterior.

---

## 🔄 Fluxo do Checkout Orquestrado

Ao enviar o pedido, o orquestrador executa sintonizadamente 12 passos de negócio:
[Cliente / Postman] ──(POST /api/checkout)──► [OrquestradorService]
│
┌────────────────────────────────────────────┼────────────────────────────────────────────┐
│                                            │                                            │
▼                                            ▼                                            ▼

E-mail Recebido                      2. Processar Pagamento                     3. E-mail Pagamento
(/notificacao)                          (/pagamento)                                (/notificacao)
│                                            │                                            │
▼                                            ▼                                            ▼

Baixa Estoque e NF                   5. E-mail Nota Fiscal                      6. Agendar Logística
(/estoque)                              (/notificacao)                              (/logistica)
│                                                                                         │
└────────────────────────────────────────────┬────────────────────────────────────────────┘
▼
7. E-mail Rastreio
(/notificacao)
│
[Cliente] ◄──(JSON Resposta 200 OK)──────────────────┘


---

## 🚀 Como Executar o Projeto

### Pré-requisitos
* Java 17 instalado
* Maven instalado (ou utilizar o wrapper `./mvnw`)

### Passos para execução
1. Clone o repositório:
   ```bash
   git clone [https://github.com/seu-usuario/orquestrador-autopecas.git](https://github.com/seu-usuario/orquestrador-autopecas.git)

Acesse a pasta do projeto:

Bash
cd orquestrador-autopecas
Execute a aplicação via Maven:

Bash
./mvnw spring-boot:run
A aplicação estará disponível em http://localhost:8080.

🧪 Como Testar no Postman / cURL
Envie uma requisição POST para http://localhost:8080/api/checkout com o seguinte corpo em JSON:

Requisição (POST /api/checkout):
JSON
{
  "nomeCliente": "Caio Oliveira Zani",
  "emailCliente": "caio@email.com",
  "enderecoEntrega": {
    "rua": "Rua das Oficinas, 123",
    "cidade": "Toledo",
    "estado": "PR",
    "cep": "85900-000"
  },
  "itens": [
    {
      "pecaId": "AMORT-01",
      "nome": "Amortecedor Dianteiro",
      "quantidade": 2,
      "precoUnitario": 350.00
    }
  ],
  "dadosPagamento": {
    "titular": "Caio O Zani",
    "numeroCartao": "5500000000001234",
    "validade": "12/28",
    "cvv": "123",
    "valorTotal": 700.00
  }
}
Resposta Esperada (HTTP 200 OK):
JSON
{
  "pedidoId": "PED-881203",
  "status": "CONCLUIDO",
  "notaFiscal": "NF-2026-4412",
  "codigoRastreio": "BR882931AUTO",
  "mensagem": "Compra processada e entregas agendadas com sucesso!"
}
✒️ Autor
Desenvolvido como projeto didático de integração de sistemas e arquitetura orientada a serviços.

Desenvolvedor: Caio Oliveira Zani


<FollowUp label="Quer uma revisão final dos comandos do terminal para garantir que a subida no Git não dê erros?
