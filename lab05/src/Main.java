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
		if(op == MenuOpcoes.SAIR)
			return;

		Scanner scanner = new Scanner(System.in);
		Seguradora seguradora = null;
		String cnpjSeguradora;
		do{
			System.out.println("Digite o CNPJ da seguradora: ");
			cnpjSeguradora = scanner.nextLine();
			for(Seguradora s: listaSeguradoras)
				if((s.getCnpj()).equals(cnpjSeguradora)){
					seguradora = s;
					break;
				}
			if(seguradora == null)
				System.out.println("Seguradora [" + cnpjSeguradora + "] não encontrada");
		}while(seguradora == null);

		switch(op) {
			case CADASTROS:
			case LISTAR:
			case EXCLUIR:
				executarSubmenu(op, seguradora);
				break;
			case GERAR_SINISTRO:
				System.out.println("Data do sinistro: ");
			 	String dataSinistro = scanner.nextLine();
				System.out.println("Endereco do sinistro: ");
				String enderecoSinistro = scanner.nextLine();
				System.out.println("Placa do veiculo: ");
				String placaVeiculo = scanner.nextLine();
				System.out.println("CPF do condutor: ");
				String cpf = scanner.nextLine();
				Seguro seguro = null;
				for(Seguro s: seguradora.getListaSeguros()){
					if(s instanceof SeguroPF){
						if(((SeguroPF)s).getVeiculo().getPlaca().equals(placaVeiculo)){
							seguro = (SeguroPF)s;
						}
				}
					if(s instanceof SeguroPJ){
						for(Veiculo v: ((SeguroPJ)s).getFrota().getListaVeiculos()){
							if(v.getPlaca().equals(placaVeiculo))
								seguro = (SeguroPJ)s;
						}
					}
				}
				if(seguro == null){
					System.out.println("Veiculo não encontrado");
					break;
				}
				for(Condutor c: seguro.getListacondutores()){
					if(c.getCpf().equals(cpf)){
						try{
							SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
							Date data = sdf.parse(dataSinistro);
							seguro.gerarSinistro(data, enderecoSinistro, c, seguro);
							System.out.println("Sinistro gerado com sucesso");
						}catch(ParseException e){
							System.out.println("Data inválida");
						}
					}
				}
				break;
			case CALCULAR_RECEITA:
				System.out.println("Receita da seguradora " + cnpjSeguradora + ": " + seguradora.calcularReceita());
				break;
			case SAIR:
		}
	}
	
	public static void executarOpcaoSubMenu(SubmenuOpcoes opSubmenu, Seguradora seguradora) {
		Scanner scanner = new Scanner(System.in);
		switch(opSubmenu) {
		case CADASTRAR_CLIENTE:
			System.out.println("Chamar metodo cadastrar cliente");
			String nome = scanner.nextLine();
			System.out.println(nome);
			break;
		case CADASTRAR_VEICULO:
			System.out.println("Chamar metodo cadastrar veiculo");
			break;
		case CADASTRAR_SEGURADORA:
			System.out.println("Chamar metodo cadastrar seguradora");
			break;
		case LISTAR_CLIENTES:
			break;
		case LISTAR_SINISTROS:
			break;
		case LISTAR_VEICULOS:
			break;
		case EXCLUIR_CLIENTE:
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
	private static void executarSubmenu(MenuOpcoes op, Seguradora seguradora) {
		SubmenuOpcoes opSubmenu;
		do {
			exibirSubmenu(op);
			opSubmenu = lerOpcaoSubmenu(op);
			executarOpcaoSubMenu(opSubmenu, seguradora);
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
		// Instanciar Seguradoras
		ArrayList<Seguradora> listaSeguradoras = new ArrayList<Seguradora>();
		ArrayList<Cliente> listaClientes = new ArrayList<Cliente>();
		ArrayList<Seguro> listaSeguros = new ArrayList<Seguro>();
		Seguradora seguradora1 = new Seguradora("24.185.819/0001-39", "Seguradora1", "3325-0221", "Av. Maxwell", "@gmail.com", listaClientes, listaSeguros);
		listaSeguradoras.add(seguradora1);
		// Instanciar 2 veículos
		Veiculo veiculo1 = new Veiculo("ABC-1234", "Fiat", "Uno", 2010);
		Veiculo veiculo2 = new Veiculo("ABC-4321", "Ford", "Ka", 2012);
		// Instanciar ClientePF
		ArrayList<Veiculo> listaVeiculos1 = new ArrayList<Veiculo>();
		listaVeiculos1.add(veiculo1);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date dataNascimento = sdf.parse("15/06/2004");
		ClientePF clientePF1 = new ClientePF("Joao", "98821628", "Av. 1", "074.581.234-19", "M", "Superior", dataNascimento, listaVeiculos1);
		// Instanciar Seguro PF
		Date dataInicio = sdf.parse("15/06/2019");
		Date dataFim = sdf.parse("15/06/2023");
		ArrayList<Sinistro> listaSinistros1 = new ArrayList<Sinistro>();
		ArrayList<Condutor> listaCondutores1 = new ArrayList<Condutor>();
		seguradora1.gerarSeguro(dataInicio, dataFim, seguradora1, listaSinistros1, listaCondutores1, veiculo1, clientePF1);
		Seguro seguroPF1 = seguradora1.getListaSeguros().get(0);
		// Instanciar Frota
		ArrayList<Veiculo> listaVeiculos2 = new ArrayList<Veiculo>();
		listaVeiculos2.add(veiculo2);
		Frota frota = new Frota(listaVeiculos2);
		// Instanciar ClientePJ
		ArrayList<Frota> listaFrota = new ArrayList<Frota>();
		listaFrota.add(frota);
		Date dataFundacao = sdf.parse("01/01/2000");
		ClientePJ clientePJ = new ClientePJ("Empresa", "82988216219", "Av.2", "81.788.124/0001-85", dataFundacao, 10, listaFrota);
		// Instanciar Seguro PJ
		ArrayList<Sinistro> listaSinistros2 = new ArrayList<Sinistro>();
		ArrayList<Condutor> listaCondutores2 = new ArrayList<Condutor>();
		seguradora1.gerarSeguro(dataInicio, dataFim, seguradora1, listaSinistros2, listaCondutores2, frota, clientePJ);
		Seguro seguroPJ = seguradora1.getListaSeguros().get(1);
		// Instanciar Condutores
		Condutor condutor1 = new Condutor("401.868.750-88", "Lucas", "1212-1212", "Av. Br", "@email.com", dataNascimento);
		Condutor condutor2 = new Condutor("807.750.790-57", "Davi", "3434-1212", "Av. Br", "@email.com", dataNascimento);
		// Gerar Sinistros
		seguroPF1.autorizarCondutor(condutor1);
		seguroPF1.gerarSinistro(sdf.parse("15/06/2020"), "Rua 1", condutor1, seguroPF1);

		seguroPJ.autorizarCondutor(condutor2);
		seguroPJ.gerarSinistro(sdf.parse("22/02/2021"), "Av. 7", condutor2, seguroPJ);

		// Apresentando detalhes dos objetos
		System.out.println(seguradora1);
		System.out.println(clientePF1);
		System.out.println(clientePJ);
		System.out.println(seguroPF1);
		System.out.println(seguroPJ);
		System.out.println(condutor1);
		System.out.println(veiculo1);
		System.out.println(seguroPF1.getListaSinistros().get(0));
		System.out.println(frota);

		// Chamar metodos do Sistema de Seguradora

		// Cadastrar e remover clientes
		seguradora1.removerCliente(clientePF1);
		seguradora1.cadastrarCliente(clientePF1);
		// Mostrar Seguros por cliente e sinistros por cliente
		System.out.println(seguradora1.getSegurosPorCliente(clientePJ));
		System.out.println(seguradora1.getSinistrosPorCliente(clientePJ));
		// Cadastrar e cancelar seguro
		seguradora1.cancelarSeguro(seguroPJ);
		seguradora1.cadastrarSeguro(seguroPJ);
		// Calcular receita de cada seguradora
		for(Seguradora seg: listaSeguradoras){
			System.out.println(seg.getNome() + " tem como receita: " + seg.calcularReceita());
		}
		// Cadastrar veiculo na frota
		clientePJ.atualizarFrota(frota, veiculo1);
		System.out.println(clientePJ.getVeiculosPorFrota(frota));
		// Remover veiculo na frota 
		clientePJ.atualizarFrota(frota, veiculo2);
		// Remover frota
		clientePJ.atualizarFrota(frota);
		// Cadastrar frota
		clientePJ.cadastrarFrota(listaVeiculos2);
		// Cadastrar e remover veiculo
		clientePF1.cadastrarVeiculo(veiculo2);
		clientePF1.removerVeiculo(veiculo2);
		// Manipular condutores
		seguroPF1.autorizarCondutor(condutor1);
		seguroPF1.desautorizarCondutor(condutor1);
		seguroPF1.calcularValor();

		// Iniciar menu de operacoes
		iniciarMenu(listaSeguradoras);
	}

}
