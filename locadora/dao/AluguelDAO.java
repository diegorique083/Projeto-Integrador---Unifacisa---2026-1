package locadora.dao;

import locadora.database.ConnectionFactory;
import locadora.model.Aluguel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AluguelDAO {

    public boolean salvar(Aluguel aluguel) {
        String sql = "INSERT INTO aluguel (id_cliente, id_veiculo, quantidade_dias, valor_total) VALUES (?, ?, ?, ?)";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, aluguel.getCliente().getId());
            stmt.setInt(2, aluguel.getVeiculo().getId());
            stmt.setInt(3, aluguel.getQuantidadeDias());
            stmt.setDouble(4, aluguel.obterValorFinal());
            stmt.executeUpdate();

            System.out.println("Aluguel registrado com sucesso no banco de dados!");
            return true;

        } catch (SQLException e) {
            System.out.println("Erro ao registrar aluguel: " + e.getMessage());
            return false;
        }
    }
}
