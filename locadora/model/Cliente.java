package locadora.model;

public class Cliente extends Pessoa {
    private String cnh;

    public Cliente() {
        super();
    }

    public Cliente(int id, String nome, String cpf, String telefone, String cnh) {
        super(id, nome, cpf, telefone);
        this.cnh = cnh;
    }

    public String getCnh() { return cnh; }
    public void setCnh(String cnh) { this.cnh = cnh; }
}