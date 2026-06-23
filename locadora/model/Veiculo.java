package locadora.model;

public class Veiculo {
    private int id;
    private String placa;
    private String marca;
    private String modelo;
    private double valorDiaria;

    public Veiculo() {}

    public Veiculo(int id, String placa, String marca, String modelo, double valorDiaria) {
        this.id = id;
        this.placa = placa;
        this.marca = marca;
        this.modelo = modelo;
        this.valorDiaria = valorDiaria;
    }

    public double calcularAluguel(int dias) {
        return this.valorDiaria * dias;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getPlaca() { return placa; }
    public void setPlaca(String placa) { this.placa = placa; }

    public String getMarca() { return marca; }
    public void setMarca(String marca) { this.marca = marca; }

    public String getModelo() { return modelo; }
    public void setModelo(String modelo) { this.modelo = modelo; }

    public double getValorDiaria() { return valorDiaria; }
    public void setValorDiaria(double valorDiaria) { this.valorDiaria = valorDiaria; }
}
