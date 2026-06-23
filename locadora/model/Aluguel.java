package locadora.model;

public class Aluguel {
    private Cliente cliente;
    private Veiculo veiculo;
    private int quantidadeDias;

    public Aluguel(Cliente cliente, Veiculo veiculo, int quantidadeDias) {
        this.cliente = cliente;
        this.veiculo = veiculo;
        this.quantidadeDias = quantidadeDias;
    }

    public double obterValorFinal() {
        if (veiculo != null) {
            return veiculo.calcularAluguel(quantidadeDias);
        }
        return 0.0;
    }

    public Cliente getCliente() { return cliente; }
    public void setCliente(Cliente cliente) { this.cliente = cliente; }

    public Veiculo getVeiculo() { return veiculo; }
    public void setVeiculo(Veiculo veiculo) { this.veiculo = veiculo; }

    public int getQuantidadeDias() { return quantidadeDias; }
    public void setQuantidadeDias(int quantidadeDias) { this.quantidadeDias = quantidadeDias; }
}
