public class Main {
    public static void main(String[] args) {
        Sinistro sinistro = new Sinistro("01/03/1994", "Chicago, IL");
        System.out.println(sinistro);

        Veiculo veiculo = new Veiculo("ABC-123", "Ford", "Fiesta");
        veiculo.setPlaca("MUDOU-PLACA");
        System.out.println(veiculo);
        
        Seguradora segura = new Seguradora("Davi", "1-003-005", "@uol.com", "Fortaleza, BA");
        System.out.println(segura);

        Cliente cliente = new Cliente("Lucas", "001.983.540-02", "01/01/2025", 0, "PB04");
        if(cliente.validarCPF(cliente.getCpf())){
            System.out.println(cliente);
        }
        else{
            System.out.println("CPF Invalido!");
        }
    }
}
