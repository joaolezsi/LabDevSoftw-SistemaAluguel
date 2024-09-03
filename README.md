# LabDevSoftw-SistemaAluguel

# Histórias do Usuário para o Sistema de Gestão de Aluguéis de Automóveis

## 1. Cadastrar Usuário
**Como** um usuário individual  
**Eu quero** me cadastrar no sistema  
**Para que** eu possa utilizar o sistema para alugar automóveis

### Critérios de Aceitação:
- O usuário deve fornecer RG, CPF, nome, endereço, profissão, entidades empregadoras e rendimentos auferidos.
- O sistema deve verificar se o CPF já está cadastrado.

## 2. Introduzir Pedido de Aluguel
**Como** um usuário individual  
**Eu quero** criar um pedido de aluguel  
**Para que** eu possa alugar um automóvel

### Critérios de Aceitação:
- O pedido deve incluir a matrícula, ano, marca, modelo e placa do automóvel desejado.
- O pedido deve ser associado ao contrato de aluguel.

## 3. Modificar Pedido de Aluguel
**Como** um usuário individual  
**Eu quero** modificar um pedido de aluguel existente  
**Para que** eu possa ajustar as condições do meu pedido

### Critérios de Aceitação:
- O usuário deve ser capaz de alterar os detalhes do automóvel ou condições do aluguel.
- O sistema deve verificar se a alteração é permitida com base no estado do pedido.

## 4. Consultar Pedido de Aluguel
**Como** um usuário individual  
**Eu quero** consultar o status do meu pedido de aluguel  
**Para que** eu possa acompanhar o andamento do meu pedido

### Critérios de Aceitação:
- O sistema deve mostrar o status atual do pedido.
- O sistema deve permitir a filtragem por data, status, ou automóvel.

## 5. Cancelar Pedido de Aluguel
**Como** um usuário individual  
**Eu quero** cancelar um pedido de aluguel  
**Para que** eu possa desistir do aluguel de um automóvel

### Critérios de Aceitação:
- O sistema deve permitir o cancelamento de pedidos em estado "pendente" ou "em análise".
- O sistema deve registrar a data e o motivo do cancelamento.

## 6. Avaliar Pedido de Aluguel
**Como** um agente  
**Eu quero** avaliar um pedido de aluguel  
**Para que** eu possa verificar se o pedido está de acordo com as políticas financeiras

### Critérios de Aceitação:
- O agente deve poder aprovar ou rejeitar pedidos baseados em critérios financeiros.
- O sistema deve registrar o parecer do agente.

## 7. Modificar Pedido de Aluguel (Agente)
**Como** um agente  
**Eu quero** modificar um pedido de aluguel existente  
**Para que** eu possa ajustá-lo conforme necessário

### Critérios de Aceitação:
- O agente deve ser capaz de alterar os detalhes do automóvel ou condições do aluguel.
- O sistema deve registrar todas as alterações feitas pelo agente.

## 8. Executar Contrato
**Como** um agente  
**Eu quero** executar um contrato de aluguel  
**Para que** finalize o processo de aluguel após a aprovação do pedido

### Critérios de Aceitação:
- O sistema deve gerar um contrato de aluguel após a aprovação.
- O contrato deve incluir todos os detalhes do aluguel e ser enviado ao cliente.
