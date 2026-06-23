package locadora.util;

public class Formatador {

    public static boolean cpfValido(String cpf) {
        if (cpf == null) return false;
        String somenteNumeros = cpf.replaceAll("[^0-9]", "");
        return somenteNumeros.length() == 11;
    }

    public static boolean telefoneValido(String telefone) {
        if (telefone == null) return false;
        String somenteNumeros = telefone.replaceAll("[^0-9]", "");
        return somenteNumeros.length() == 10 || somenteNumeros.length() == 11;
    }

    public static boolean cnhValida(String cnh) {
        if (cnh == null) return false;
        String somenteNumeros = cnh.replaceAll("[^0-9]", "");
        return somenteNumeros.length() == 11;
    }

    public static String formatarCpf(String cpf) {
        String numeros = cpf.replaceAll("[^0-9]", "");
        if (numeros.length() != 11) return cpf;
        return numeros.substring(0, 3) + "." + numeros.substring(3, 6) + "." + numeros.substring(6, 9) + "-" + numeros.substring(9, 11);
    }

    public static String formatarTelefone(String telefone) {
        String numeros = telefone.replaceAll("[^0-9]", "");
        if (numeros.length() == 11) {
            return "(" + numeros.substring(0, 2) + ") " + numeros.substring(2, 7) + "-" + numeros.substring(7, 11);
        }
        if (numeros.length() == 10) {
            return "(" + numeros.substring(0, 2) + ") " + numeros.substring(2, 6) + "-" + numeros.substring(6, 10);
        }
        return telefone;
    }

    public static String formatarCnh(String cnh) {
        String numeros = cnh.replaceAll("[^0-9]", "");
        if (numeros.length() != 11) return cnh;
        return numeros.substring(0, 3) + " " + numeros.substring(3, 6) + " " + numeros.substring(6, 9) + " " + numeros.substring(9, 11);
    }
}
