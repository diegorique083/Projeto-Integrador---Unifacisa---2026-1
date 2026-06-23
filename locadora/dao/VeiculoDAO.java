package locadora.dao;

import locadora.database.ConnectionFactory;
import locadora.model.Veiculo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VeiculoDAO {

    public boolean salvar(Veiculo veiculo) {
        String sql = "INSERT INTO veiculo (placa, marca, modelo, valor_diaria, disponivel) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, veiculo.getPlaca());
            stmt.setString(2, veiculo.getMarca());
            stmt.setString(3, veiculo.getModelo());
            stmt.setDouble(4, veiculo.getValorDiaria());
            stmt.setInt(5, 1);
            stmt.executeUpdate();

            System.out.println("Veículo salvo com sucesso!");
            return true;

        } catch (SQLException e) {
            System.out.println("Erro ao salvar veículo: " + e.getMessage());
            return false;
        }
    }

    public List<Veiculo> listar() {
        List<Veiculo> veiculos = new ArrayList<>();
        String sql = "SELECT * FROM veiculo ORDER BY id";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Veiculo veiculo = new Veiculo(
                        rs.getInt("id"),
                        rs.getString("placa"),
                        rs.getString("marca"),
                        rs.getString("modelo"),
                        rs.getDouble("valor_diaria")
                );
                veiculos.add(veiculo);
            }

        } catch (SQLException e) {
            System.out.println("Erro ao listar veículos: " + e.getMessage());
        }

        return veiculos;
    }

    public List<Veiculo> listarDisponiveis() {
        List<Veiculo> veiculos = new ArrayList<>();
        String sql = "SELECT * FROM veiculo WHERE disponivel = 1 ORDER BY id";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Veiculo veiculo = new Veiculo(
                        rs.getInt("id"),
                        rs.getString("placa"),
                        rs.getString("marca"),
                        rs.getString("modelo"),
                        rs.getDouble("valor_diaria")
                );
                veiculos.add(veiculo);
            }

        } catch (SQLException e) {
            System.out.println("Erro ao listar veículos disponíveis: " + e.getMessage());
        }

        return veiculos;
    }

    public Veiculo buscarPorId(int id) {
        String sql = "SELECT * FROM veiculo WHERE id = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Veiculo(
                        rs.getInt("id"),
                        rs.getString("placa"),
                        rs.getString("marca"),
                        rs.getString("modelo"),
                        rs.getDouble("valor_diaria")
                );
            }

        } catch (SQLException e) {
            System.out.println("Erro ao buscar veículo: " + e.getMessage());
        }

        return null;
    }

    public boolean atualizarDisponibilidade(int id, boolean disponivel) {
        String sql = "UPDATE veiculo SET disponivel = ? WHERE id = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, disponivel ? 1 : 0);
            stmt.setInt(2, id);
            int linhas = stmt.executeUpdate();

            return linhas > 0;

        } catch (SQLException e) {
            System.out.println("Erro ao atualizar disponibilidade: " + e.getMessage());
            return false;
        }
    }

    public boolean remover(int id) {
        String sql = "DELETE FROM veiculo WHERE id = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            int linhas = stmt.executeUpdate();

            return linhas > 0;

        } catch (SQLException e) {
            System.out.println("Erro ao remover veículo: " + e.getMessage());
            return false;
        }
    }
}
