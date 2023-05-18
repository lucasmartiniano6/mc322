import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Main {
	
	//exibir menu externo
	private static void exibirMenuExterno() {
		MenuOpcoes menuOpcoes[] = MenuOpcoes.values();
		System.out.println("Menu principal");
		for(MenuOpcoes op: menuOpcoes) {
			System.out.println(op.ordinal() + " - " + op.getDescricao());
		}
	}
	
	/* exibir submenus
	 * se a lista de constantes do submenu for percorrida da mesma forma que o meu externo, a opção Voltar
	 * é printada com a posição que está na lista do enum (9 - Voltar). Por isso, a lista é percorrida 
	 * de forma diferente, tendo i como o inteiro correspondente. Assim, para listar o submenu de cadastros,
	 * por exemplo, vai ser printado "3 - Voltar".
	 */
	private static void exibirSubmenu(MenuOpcoes op) {
		SubmenuOpcoes[] submenu = op.getSubmenu();
		System.out.println(op.getDescricao());
		for(int i=0; i<submenu.length; i++) {
			System.out.println(i +" - " + submenu[i].getDescricao());
		}
	}
	
	//ler opções do menu externo
	private static MenuOpcoes lerOpcaoMenuExterno() {
		Scanner scanner = new Scanner(System.in);
		int opUsuario;
		MenuOpcoes opUsuarioConst;
		do {
			System.out.println("Digite uma opcao: ");
			opUsuario = scanner.nextInt();
		}while(opUsuario < 0 || opUsuario > MenuOpcoes.values().length - 1);
		opUsuarioConst = MenuOpcoes.values()[opUsuario];
		return opUsuarioConst;
	}
	
	//ler opção dos submenus
	private static SubmenuOpcoes lerOpcaoSubmenu(MenuOpcoes op) {
		Scanner scanner = new Scanner(System.in);
		int opUsuario;
		SubmenuOpcoes opUsuarioConst;
		do {
			System.out.println("Digite uma opcao: ");
			opUsuario = scanner.nextInt();
		}while(opUsuario < 0 || opUsuario > op.getSubmenu().length - 1);
		opUsuarioConst = op.getSubmenu()[opUsuario];
		return opUsuarioConst;
	}
	
	//executar opções do menu externo
	private static void executarOpcaoMenuExterno(MenuOpcoes op, ArrayList<Seguradora> listaSeguradoras) {
		Scanner scanner = new Scanner(System.in);
		String nomeSeguradora;
		switch(op) {
			case CADASTROS:
			case LISTAR:
			case EXCLUIR:
				executarSubmenu(op);
				break;
			case GERAR_SINISTRO:
				System.out.println("Executar metodo gerar Sinistro");
				break;
			case TRANSFERIR_SEGURO:
				System.out.println("Executar metodo tranferir seguro");
				System.out.println("Digite o nome da seguradora: ");
				nomeSeguradora = scanner.nextLine();
				System.out.println("Nome do cliente de origem: ");
				String nomeClienteOrigem = scanner.nextLine();
				while(!Validacao.validaNome(nomeClienteOrigem)){
					System.out.println("Nome inválido, digite novamente: ");
					nomeClienteOrigem = scanner.nextLine();
				}
				System.out.println("Nome do cliente de destino: ");
				String nomeClienteDestino = scanner.nextLine();
				while(!Validacao.validaNome(nomeClienteDestino)){
					System.out.println("Nome inválido, digite novamente: ");
					nomeClienteDestino = scanner.nextLine();
				}
				for(Seguradora s: listaSeguradoras){
					System.out.println(s.getNome());
					if((s.getNome()).equals(nomeSeguradora)){
						s.transferirSeguro(nomeClienteOrigem, nomeClienteDestino);
						break;
					}
				}	
				System.out.println("Seguradora [" + nomeSeguradora + "] não encontrada");
				break;
			case CALCULAR_RECEITA:
				System.out.println("Executar metodo calcular receita");
				System.out.println("Digite o nome da seguradora: ");
				nomeSeguradora = scanner.nextLine();
				for(Seguradora s: listaSeguradoras){
					if(s.getNome().equals(nomeSeguradora)){
						System.out.println("Receita da seguradora " + nomeSeguradora + ": " + s.calcularReceita());
						break;
					}
				}
				// se não achou o nome
				System.out.println("Seguradora não encontrada");
				break;
			case SAIR:
		}
	}
	
	public static void executarOpcaoSubMenu(SubmenuOpcoes opSubmenu) {
		switch(opSubmenu) {
		case CADASTRAR_CLIENTE:
			System.out.println("Chamar metodo cadastrar cliente");
			break;
		case CADASTRAR_VEICULO:
			System.out.println("Chamar metodo cadastrar veiculo");
			break;
		case CADASTRAR_SEGURADORA:
			System.out.println("Chamar metodo cadastrar seguradora");
			break;
		case LISTAR_CLIENTES:
			System.out.println("Chamar metodo listar clientes");
			break;
		case LISTAR_SINISTROS:
			System.out.println("Chamar metodo listar sinistros");
			break;
		case LISTAR_VEICULOS:
			System.out.println("Chamar metodo listar veiculos");
			break;
		case EXCLUIR_CLIENTE:
			System.out.println("Chamar metodo excluir cliente");
			break;
		case EXCLUIR_VEICULO:
			System.out.println("Chamar metodo excluir veiculo");
			break;
		case EXCLUIR_SINISTRO:
			System.out.println("Chamar metodo excluir sinistro");
			break;
		case VOLTAR:
			break;
		}
	}
	
	//executa os submenus: exibição do menu, leitura da opção e execução dos métodos
	private static void executarSubmenu(MenuOpcoes op) {
		SubmenuOpcoes opSubmenu;
		do {
			exibirSubmenu(op);
			opSubmenu = lerOpcaoSubmenu(op);
			executarOpcaoSubMenu(opSubmenu);
		}while(opSubmenu != SubmenuOpcoes.VOLTAR);
	}
	
	//executa o menu externo: exibição do menu, leitura da opção e execução da opção
	public static void iniciarMenu(ArrayList<Seguradora> listaSeguradoras){
		MenuOpcoes op;
		do {
			exibirMenuExterno();
			op = lerOpcaoMenuExterno();
			executarOpcaoMenuExterno(op, listaSeguradoras);
		}while(op != MenuOpcoes.SAIR);
		System.out.println("Saiu do sistema");
	}

	public static void main(String[] args) throws ParseException{
		// Instanciar 2 veículos
		Veiculo veiculo1 = new Veiculo("ABC-1234", "Fiat", "Uno", 2010);
		Veiculo veiculo2 = new Veiculo("ABC-4321", "Ford", "Ka", 2012);
		// Instanciar ClientePF
		ArrayList<Veiculo> listaVeiculos1 = new ArrayList<Veiculo>();
		listaVeiculos1.add(veiculo1);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date dataLicenca = sdf.parse("01/01/2010");
		Date dataNascimento = sdf.parse("15/06/2004");
		ClientePF clientePF1 = new ClientePF("Joao", "Av 1", listaVeiculos1, "074.581.234-19", "M", dataLicenca, "Superior", dataNascimento, "A");
		// Instanciar ClientePJ
		ArrayList<Veiculo> listaVeiculos2 = new ArrayList<Veiculo>();
		listaVeiculos2.add(veiculo2);
		Date dataFundacao = sdf.parse("01/01/2000");
		ClientePJ clientePJ1 = new ClientePJ("Empresa", "Av 2", listaVeiculos2, "81.788.124/0001-85", dataFundacao, 15);
		// Instanciar Seguradoras
		ArrayList<Seguradora> listaSeguradoras = new ArrayList<Seguradora>();
		ArrayList<Sinistro> listaSinistros1 = new ArrayList<Sinistro>();
		ArrayList<Cliente> listaClientes1 = new ArrayList<Cliente>();
		Seguradora seguradora1 = new Seguradora("Seguradora 1", "1111-1111", "@seg1.com", "Av 3", listaSinistros1, listaClientes1);
		listaSeguradoras.add(seguradora1);
		// Cadastrar clientes na primeira seguradora
		if(listaSeguradoras.get(0).cadastrarCliente(clientePF1))
			System.out.println("Cliente cadastrado");
		else
			System.out.println("Cliente já cadastrado");

		if(listaSeguradoras.get(0).cadastrarCliente(clientePJ1))
			System.out.println("Cliente cadastrado");
		else
			System.out.println("Cliente já cadastrado");
		// Gerar sinistros
		listaSeguradoras.get(0).gerarSinistro("18/05/2023", "Av. Maxwell", listaSeguradoras.get(0), veiculo1, clientePF1);
		listaSeguradoras.get(0).gerarSinistro("15/05/2023", "Av. Tartaruga", listaSeguradoras.get(0), veiculo2, clientePJ1);
		// Chamar metodos da Seguradora
		listaSeguradoras.get(0).listarClientes("PF");
		listaSeguradoras.get(0).vizualizarSinistro("074.581.234-19");
		listaSeguradoras.get(0).listarSinistros();
		// Atualizar e calcular preco seguro
		for(Seguradora seg: listaSeguradoras){
			for(Cliente cliente: seg.getListaClientes()){
				seg.calcularPrecoSeguroCliente(cliente);
			}
			System.out.println(seg.getNome() + " tem como receita: " + seg.calcularReceita());
		}

		// Iniciar menu de operacoes
		iniciarMenu(listaSeguradoras);
	}

}
