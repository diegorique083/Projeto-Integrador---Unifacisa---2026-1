package locadora.view;

import javax.swing.JOptionPane;
import locadora.dao.AluguelDAO;
import locadora.dao.ClienteDAO;
import locadora.dao.VeiculoDAO;
import locadora.database.CriadorTabelas;
import locadora.model.Cliente;
import locadora.model.Veiculo;
import locadora.model.Aluguel;
import locadora.util.Formatador;
import java.util.List;

public class MenuPrincipal {

    private static ClienteDAO clienteDAO = new ClienteDAO();
    private static VeiculoDAO veiculoDAO = new VeiculoDAO();
    private static AluguelDAO aluguelDAO = new AluguelDAO();

    public static void main(String[] args) {
        CriadorTabelas.criarTabelas();
        int opcao = -1;

        do {
            String menu = "=== SISTEMA LOCADORA DE VEICULOS ===\n\n"
                    + "1. Cadastrar Cliente\n"
                    + "2. Listar Clientes\n"
                    + "3. Cadastrar Veiculo\n"
                    + "4. Listar Veiculos\n"
                    + "5. Registrar Aluguel\n"
                    + "0. Sair\n\n"
                    + "Escolha uma opcao:";

            String entrada = JOptionPane.showInputDialog(null, menu, "Menu Principal", JOptionPane.QUESTION_MESSAGE);

            if (entrada == null) {
                break;
            }

            try {
                opcao = Integer.parseInt(entrada);

                switch (opcao) {
                    case 1:
                        cadastrarCliente();
                        break;
                    case 2:
                        listarClientes();
                        break;
                    case 3:
                        cadastrarVeiculo();
                        break;
                    case 4:
                        listarVeiculos();
                        break;
                    case 5:
                        registrarAluguel();
                        break;
                    case 0:
                        JOptionPane.showMessageDialog(null, "Saindo do sistema... Até logo!");
                        break;
                    default:
                        JOptionPane.showMessageDialog(null, "Opção inválida! Tente novamente.");
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Por favor, digite um número válido.");
            }

        } while (opcao != 0);
    }

    private static void cadastrarCliente() {
        String nome = JOptionPane.showInputDialog("Nome do Cliente:");
        if (nome == null) return;

        String cpf = JOptionPane.showInputDialog("CPF do Cliente (somente números):");
        if (cpf == null) return;

        if (!Formatador.cpfValido(cpf)) {
            JOptionPane.showMessageDialog(null, "CPF inválido! Digite 11 números, exemplo: 12345678901");
            return;
        }

        String telefone = JOptionPane.showInputDialog("Telefone (somente números, com DDD):");
        if (telefone == null) return;

        if (!Formatador.telefoneValido(telefone)) {
            JOptionPane.showMessageDialog(null, "Telefone inválido! Digite DDD + número, exemplo: 11987654321");
            return;
        }

        String cnh = JOptionPane.showInputDialog("CNH do Cliente (somente números):");
        if (cnh == null) return;

        if (!Formatador.cnhValida(cnh)) {
            JOptionPane.showMessageDialog(null, "CNH inválida! Digite 11 números.");
            return;
        }

        Cliente novoCliente = new Cliente(0, nome, Formatador.formatarCpf(cpf), Formatador.formatarTelefone(telefone), Formatador.formatarCnh(cnh));
        boolean sucesso = clienteDAO.salvar(novoCliente);

        if (sucesso) {
            JOptionPane.showMessageDialog(null, "Cliente cadastrado com sucesso!");
        } else {
            JOptionPane.showMessageDialog(null, "Não foi possível cadastrar o cliente. Verifique a conexão com o banco.");
        }
    }

    private static void listarClientes() {
        List<Cliente> clientes = clienteDAO.listar();
        if (clientes.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Nenhum cliente cadastrado.");
            return;
        }

        StringBuilder lista = new StringBuilder("=== LISTA DE CLIENTES ===\n\n");
        for (Cliente c : clientes) {
            lista.append("ID: ").append(c.getId())
                 .append(" | Nome: ").append(c.getNome())
                 .append(" | CPF: ").append(c.getCpf())
                 .append(" | CNH: ").append(c.getCnh())
                 .append(" | Telefone: ").append(c.getTelefone())
                 .append("\n");
        }
        JOptionPane.showMessageDialog(null, lista.toString());
    }

    private static void cadastrarVeiculo() {
        String marca = JOptionPane.showInputDialog("Marca do Veículo:");
        if (marca == null) return;

        String modelo = JOptionPane.showInputDialog("Modelo do Veículo:");
        if (modelo == null) return;

        String placa = JOptionPane.showInputDialog("Placa:");
        if (placa == null) return;

        String valorTexto = JOptionPane.showInputDialog("Valor da Diária (R$):");
        if (valorTexto == null) return;

        try {
            double valorDiaria = Double.parseDouble(valorTexto.replace(",", "."));
            Veiculo novoVeiculo = new Veiculo(0, placa, marca, modelo, valorDiaria);
            boolean sucesso = veiculoDAO.salvar(novoVeiculo);

            if (sucesso) {
                JOptionPane.showMessageDialog(null, "Veículo cadastrado com sucesso!");
            } else {
                JOptionPane.showMessageDialog(null, "Não foi possível cadastrar o veículo. Verifique a conexão com o banco.");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Valor da diária inválido!");
        }
    }

    private static void listarVeiculos() {
        List<Veiculo> veiculos = veiculoDAO.listar();
        if (veiculos.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Nenhum veículo cadastrado.");
            return;
        }

        StringBuilder lista = new StringBuilder("=== LISTA DE VEÍCULOS ===\n\n");
        for (Veiculo v : veiculos) {
            lista.append("ID: ").append(v.getId())
                 .append(" | Modelo: ").append(v.getModelo())
                 .append(" | Marca: ").append(v.getMarca())
                 .append(" | Placa: ").append(v.getPlaca())
                 .append(" | Diária: R$ ").append(v.getValorDiaria())
                 .append("\n");
        }
        JOptionPane.showMessageDialog(null, lista.toString());
    }

    private static void registrarAluguel() {
        List<Cliente> clientes = clienteDAO.listar();
        List<Veiculo> veiculos = veiculoDAO.listarDisponiveis();

        if (clientes.isEmpty() || veiculos.isEmpty()) {
            JOptionPane.showMessageDialog(null, "É necessário ter pelo menos 1 cliente e 1 veículo disponível para realizar um aluguel.");
            return;
        }

        StringBuilder listaClientes = new StringBuilder("Clientes disponíveis:\n\n");
        for (Cliente c : clientes) {
            listaClientes.append("ID: ").append(c.getId()).append(" - ").append(c.getNome()).append("\n");
        }
        String idClienteTexto = JOptionPane.showInputDialog(listaClientes + "\nDigite o ID do cliente:");
        if (idClienteTexto == null) return;

        StringBuilder listaVeiculos = new StringBuilder("Veículos disponíveis:\n\n");
        for (Veiculo v : veiculos) {
            listaVeiculos.append("ID: ").append(v.getId()).append(" - ").append(v.getModelo())
                    .append(" (").append(v.getMarca()).append(") - R$ ").append(v.getValorDiaria()).append("/dia\n");
        }
        String idVeiculoTexto = JOptionPane.showInputDialog(listaVeiculos + "\nDigite o ID do veículo:");
        if (idVeiculoTexto == null) return;

        String diasTexto = JOptionPane.showInputDialog("Quantidade de dias do aluguel:");
        if (diasTexto == null) return;

        try {
            int idCliente = Integer.parseInt(idClienteTexto);
            int idVeiculo = Integer.parseInt(idVeiculoTexto);
            int dias = Integer.parseInt(diasTexto);

            Cliente cliente = clienteDAO.buscarPorId(idCliente);
            Veiculo veiculo = veiculoDAO.buscarPorId(idVeiculo);

            if (cliente == null || veiculo == null) {
                JOptionPane.showMessageDialog(null, "Cliente ou veículo não encontrado.");
                return;
            }

            Aluguel novoAluguel = new Aluguel(cliente, veiculo, dias);
            double valorTotal = novoAluguel.obterValorFinal();

            boolean salvo = aluguelDAO.salvar(novoAluguel);
            if (salvo) {
                veiculoDAO.atualizarDisponibilidade(veiculo.getId(), false);
            }

            String recibo = "=== COMPROVANTE DE ALUGUEL ===\n\n"
                    + "Cliente: " + cliente.getNome() + "\n"
                    + "Veículo: " + veiculo.getModelo() + " (" + veiculo.getMarca() + ")\n"
                    + "Dias contratados: " + dias + "\n"
                    + "Valor total a pagar: R$ " + valorTotal + "\n\n"
                    + (salvo ? "Aluguel registrado no banco de dados!" : "Houve um problema ao salvar o aluguel no banco.");

            JOptionPane.showMessageDialog(null, recibo);

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Dados inválidos. Use apenas números.");
        }
    }
}
