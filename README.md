# 🛒 Projeto Carrinho de Compras com TDD

Este projeto foi desenvolvido com o objetivo de praticar e demonstrar a aplicação da metodologia **TDD (Test-Driven Development)** usando **Java** e **Maven**.

---

## 🧪 O que é TDD?

**TDD (Test-Driven Development)** é uma metodologia de desenvolvimento onde os testes são escritos **antes** da implementação do código. O ciclo de TDD é dividido em três etapas principais:

1. **Red**: Escreva um teste que falha, pois a funcionalidade ainda não existe.
2. **Green**: Implemente o código mínimo necessário para fazer o teste passar.
3. **Refactor**: Refatore o código, mantendo todos os testes passando.

Esse processo ajuda a garantir que o código seja mais confiável, modular e fácil de manter.

---

## 📚 Objetivo

Implementar, de forma incremental e guiada por testes, um sistema simples de **carrinho de compras**, simulando operações comuns de e-commerce como:

- Adicionar produto
- Calcular valor total
- Remover produto
- (Em breve) Aplicar desconto
- (Em breve) Controlar quantidade

---

## 🧪 Tecnologias Utilizadas

- Java 17+
- Maven
- JUnit 5 (Jupiter)
- AssertJ (para asserções mais fluídas)
- Mockito (opcional, para testes com mocks)

---

## 🚀 Como Executar os Testes

1. Clone o repositório:
   ```bash
   git clone git@github.com:DaniloTakeo/tdd_java.git
   cd carrinho-tdd
   ```
   
2. Rode os testes com Maven:

  ```bash
  mvn clean test
  ```

---

## 🧪 Filosofia TDD aplicada
Este projeto segue o ciclo TDD:

#### 1. Red – Escreva um teste que falha
#### 2. Green – Implemente o código mínimo necessário para passar o teste
#### 3. Refactor – Refatore o código mantendo todos os testes passando

---

## 👨‍💻 Autor
Danilo Takeo Kanizawa
