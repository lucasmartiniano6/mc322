import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws ParseException{   
        // Cadastrar ClientePF
        Scanner sc = new Scanner(System.in);

        String pattern = "dd/MM/yyyy";
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);

        System.out.println("Digite o nome do cliente: ");
        String nome = sc.nextLine();
        System.out.println("Digite o endereço do cliente: ");
        String endereco = sc.nextLine();
        ArrayList<Veiculo> listaVeiculos = new ArrayList<Veiculo>();
        System.out.println("Digite o CPF do cliente: ");
        String cpf = sc.nextLine();
        System.out.println("Digite o gênero do cliente: ");
        String genero = sc.nextLine();
        System.out.println("Digite a data de licença do cliente: ");
        Date dataLicenca = sdf.parse(sc.nextLine());
        System.out.println("Digite a educação do cliente: ");
        String educacao = sc.nextLine();
        System.out.println("Digite a data de nascimento do cliente: ");
        Date dataNascimento = sdf.parse(sc.nextLine());
        System.out.println("Digite a classe econômica do cliente: ");
        String classeEconomica = sc.nextLine();

        ClientePF cliente_pf = new ClientePF(nome, endereco, listaVeiculos, cpf, genero, dataLicenca, educacao, dataNascimento, classeEconomica);
        if(!cliente_pf.validarCPF(cpf)){
            System.out.println("CPF inválido!");
            System.exit(0);
        }
        else{
            System.out.println("CPF válido!");
        }
        System.out.println("Cliente cadastrado com sucesso!");

        // Cadastrar Veículo
        System.out.println("Digite a placa do veículo: ");
        String placa = sc.nextLine();
        System.out.println("Digite a marca do veículo: ");
        String marca = sc.nextLine();
        System.out.println("Digite o modelo do veículo: ");
        String modelo = sc.nextLine();
        System.out.println("Digite o ano do veículo: ");
        Integer ano = Integer.parseInt(sc.nextLine());
        
        // Adiciona o veículo na lista de veículos do cliente 
        Veiculo veiculo = new Veiculo(placa, marca, modelo, ano);
        cliente_pf.getListaVeiculos().add(veiculo);
 
        // Cadastrar Seguradora
        System.out.println("Digite o nome da seguradora: ");
        String nomeSeguradora = sc.nextLine();
        System.out.println("Digite o telefone da seguradora: ");
        String telefoneSeguradora = sc.nextLine();
        System.out.println("Digite o email da seguradora: ");
        String emailSeguradora = sc.nextLine();
        System.out.println("Digite o endereço da seguradora: ");
        String enderecoSeguradora = sc.nextLine();

        ArrayList<Sinistro> listaSinistros = new ArrayList<Sinistro>();
        ArrayList<Cliente> listaClientes = new ArrayList<Cliente>();
 
        Seguradora seguradora = new Seguradora(nomeSeguradora, telefoneSeguradora, emailSeguradora, enderecoSeguradora, listaSinistros, listaClientes);
        System.out.println(seguradora);

        ClientePJ cliente_pj = new ClientePJ(nome, endereco, listaVeiculos, "82.262.831/0001-04", dataLicenca);
        if(!cliente_pj.validarCNPJ(cliente_pj.getCNPJ())){
            System.out.println("CNPJ inválido!");
            System.exit(0);
        }
        else{
            System.out.println("CNPJ válido!");
        }

        // Adiciona o cliente na lista de clientes da seguradora
        seguradora.cadastrarCliente(cliente_pf);
        seguradora.cadastrarCliente(cliente_pj);

        ClientePF cliente_pf2 = new ClientePF(nome, endereco, listaVeiculos, "350.081.550-24", genero, dataLicenca, educacao, dataNascimento, classeEconomica);
        seguradora.cadastrarCliente(cliente_pf2);
        seguradora.removerCliente(cliente_pf2.getCPF());

        // Gerar Sinistro
        if(seguradora.gerarSinistro("13/04/1994", "Avenida 1")){
            System.out.println("Sinistro gerado com sucesso!");
        }
        else{
            System.out.println("Sinistro não gerado!");
        }

        // Listar Clientes
        seguradora.listarClientes(cliente_pf.getCPF());
        
        // Vizualizar Sinistro
        seguradora.vizualizarSinistro(cliente_pf.getCPF());
        
        // Listar Sinistros
        seguradora.listarSinistros();

        // Chamar to-string
        System.out.println(veiculo);
        System.out.println(seguradora);
        
        sc.close();
    }
}
