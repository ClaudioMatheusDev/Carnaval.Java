# 📌 Gerenciador de Escolas de Samba - Carnaval 🎭

Este projeto é um sistema simples em Java para gerenciar escolas de samba, permitindo o cadastro de escolas, adição de notas e exibição da escola campeã com maior média de notas.

---

## 📜 Funcionalidades

✅ Cadastro de novas escolas de samba, incluindo:
   - Nome da escola
   - Samba-enredo
   - Tema do desfile
   - Características da escola
   - Famosos que participam do desfile

✅ Adição de notas para cada escola cadastrada

✅ Cálculo da média de notas para determinar a escola campeã

✅ Interface baseada em terminal com um menu interativo

---

## 🛠 Tecnologias Utilizadas

- **Linguagem:** Java
- **Paradigma:** Programação Orientada a Objetos (POO)
- **Coleções:** `ArrayList` e `HashMap`
- **Entrada de Dados:** `Scanner`

---

## 📂 Estrutura do Código

### `EscolaDeSamba`
Classe responsável por armazenar os dados de cada escola de samba.

### `GerenciadorNotas`
Classe que gerencia as notas das escolas, armazenando-as e calculando a média para determinar a campeã.

### `Carnaval`
Classe principal que contém o menu interativo para interação do usuário.

---

## 🚀 Como Executar o Projeto

1. **Clone o repositório:**
```sh
 git clone https://github.com/ClaudioMatheusDev/Carnaval.Java
```

2. **Compile o código:**
```sh
javac fai/escolacarnaval/Carnaval.java
```

3. **Execute o programa:**
```sh
java fai.escolacarnaval.Carnaval
```

---

## 🔧 Melhorias Implementadas

- Adicionado tratamento de entrada para evitar valores inválidos
- Otimizado o cálculo da média de notas
- Implementado validação para evitar cadastro duplicado de escolas
- Aprimorada a exibição do menu e mensagens para o usuário

---
