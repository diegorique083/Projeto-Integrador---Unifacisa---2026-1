# Integrantes:

Ademir Costa Santos Filho 
2525050040
André Luís Sousa Araújo Júnior
2525050011
Diego Henrique Brito de Almeida
2525050038
Matheus Rodrigues de Sousa 
2525050025
Evaldo Maciel de Souza Júnior  
2525050003
Nicholas Nunes da Silva
2525050035


# Sistema Locadora de Veículos

Sistema de gerenciamento de locadora de veículos desenvolvido em Java, com persistência de dados em SQLite.

## Estrutura do projeto

```
projeto_final/
├── bin/              <- classes compiladas
├── lib/              <- driver SQLite (sqlite-jdbc-3.53.2.0.jar)
├── locadora/         <- código fonte .java
│   ├── model/
│   ├── dao/
│   ├── database/
│   ├── util/
│   └── view/
└── database.sql      <- schema do banco (referência)
```

O banco de dados (`locadora.db`) é criado automaticamente na primeira execução, dentro da pasta `projeto_final`.

## Pré-requisito

Ter o **Java (JDK 17 ou superior)** instalado. Para verificar, abra o terminal e rode:

```
java -version
```

Se não tiver instalado, baixe em: https://adoptium.net/

---

## Como rodar

### 1. Abra o terminal na pasta do projeto

No VS Code, abra a pasta `projeto_final` e abra o terminal integrado (`Ctrl + '`).

Confirme que está na pasta correta rodando:

```
pwd
```

O resultado deve terminar com `projeto_final`.

### 2. Se for a primeira vez (ou se alterar algum .java), compile:

```
javac -cp "lib\sqlite-jdbc-3.53.2.0.jar" -d bin (Get-ChildItem -Recurse -Path locadora -Filter "*.java" | Select-Object -ExpandProperty FullName)
```

Se não houver nenhuma mensagem de erro, a compilação foi bem-sucedida.

### 3. Execute o sistema:

```
cd projeto_final
java -cp "bin;lib\sqlite-jdbc-3.53.2.0.jar" locadora.view.MenuPrincipal

```

O menu do sistema será exibido no terminal e o arquivo `locadora.db` será criado automaticamente.

---

## Observações

- O comando de compilação só precisa ser rodado uma vez, ou sempre que algum arquivo `.java` for alterado.
- O banco de dados SQLite não precisa de instalação de servidor. O arquivo `locadora.db` gerado na pasta do projeto já é o banco.
- O arquivo `database.sql` é apenas uma referência do schema. Não precisa ser executado manualmente.
