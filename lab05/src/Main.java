import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;


public class Main {
	private static Seguradora procurarSeguradora(ArrayList<Seguradora> listaSeguradoras){
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

		return seguradora;
	}

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
		switch(op) {
			case CADASTROS:
			case LISTAR:
			case EXCLUIR:
				executarSubmenu(op, listaSeguradoras);
				break;
			case GERAR_SINISTRO:
				Seguradora seguradora = procurarSeguradora(listaSeguradoras);
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
			case AUTORIZAR_CONDUTOR:
				Seguradora seg_condutor = procurarSeguradora(listaSeguradoras);
				System.out.println("Digite o ID do seguro: ");
				int id = Integer.parseInt(scanner.nextLine());
				Seguro seguro_condutor = null;
				for(Seguro s: seg_condutor.getListaSeguros()){
					if(s.getId() == id){
						seguro_condutor = s;
					}
				}
				if(seguro_condutor == null) break;

				System.out.println("Digite o cpf do condutor: ");
				String cpf_condutor = scanner.nextLine();
				System.out.println("Digite o nome do condutor: ");
				String nome_condutor = scanner.nextLine();
				System.out.println("Digite o telefone do condutor: ");
				String telefone_condutor = scanner.nextLine();
				System.out.println("Digite o endereco do condutor: ");
				String endereco_condutor = scanner.nextLine();
				System.out.println("Digite o email do condutor: ");
				String email_condutor = scanner.nextLine();
				System.out.println("Digite a data de nascimento do condutor: ");
				String dataNascimento_condutor = scanner.nextLine();
				try{
					SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
					Date dataNascimento = sdf.parse(dataNascimento_condutor);
					Condutor condutor = new Condutor(cpf_condutor, nome_condutor, telefone_condutor, endereco_condutor, email_condutor, dataNascimento);
					for(Condutor c: seguro_condutor.getListacondutores()){
						if(c.getCpf().equals(cpf_condutor)){
							System.out.println("Condutor já autorizado");
							return;
						}
					}
					seguro_condutor.getListacondutores().add(condutor);
					System.out.println("Condutor cadastrado com sucesso");
				}catch(ParseException e){
					System.out.println("Data inválida");
				}
				break;
			case DESAUTORIZAR_CONDUTOR:
				Seguradora seg_desautorizar = procurarSeguradora(listaSeguradoras);
				System.out.println("Digite o ID do seguro: ");
				int id_desautorizar = Integer.parseInt(scanner.nextLine());
				Seguro seguro_desautorizar = null;
				for(Seguro s: seg_desautorizar.getListaSeguros()){
					if(s.getId() == id_desautorizar){
						seguro_desautorizar = s;
					}
				}
				System.out.println("Digite o cpf do condutor: ");
				String cpf_des = scanner.nextLine();
				for(Condutor c: seguro_desautorizar.getListacondutores()){
					if(c.getCpf() == cpf_des){
						seguro_desautorizar.getListacondutores().remove(c);
						return;
					}
				}
				System.out.println("Condutor não encontrado");
				break;
			case CALCULAR_RECEITA:
				Seguradora seg = procurarSeguradora(listaSeguradoras);
				System.out.printf("Receita da seguradora [%s] : %.2f reais\n", seg.getCnpj(), seg.calcularReceita());
				break;
			case SAIR:
		}
	}
	
	public static void executarOpcaoSubMenu(SubmenuOpcoes opSubmenu, ArrayList<Seguradora> listaSeguradoras) {
		Scanner scanner = new Scanner(System.in);
		switch(opSubmenu) {
		case CADASTRAR_SEGURADORA:
			System.out.println("Digite o cnpj da seguradora: ");
			String cnpj = scanner.nextLine();
			System.out.println("Digite o nome da seguradora: ");
			String nome = scanner.nextLine();
			System.out.println("Digite o telefone da seguradora: ");
			String telefone = scanner.nextLine();
			System.out.println("Digite o endereco da seguradora: ");
			String endereco = scanner.nextLine();
			System.out.println("Digite o email da seguradora: ");
			String email = scanner.nextLine();
			Seguradora seguradora = new Seguradora(cnpj, nome, telefone, endereco, email, new ArrayList<Cliente>(), new ArrayList<Seguro>());
			for(Seguradora s: listaSeguradoras){
				if(s.equals(seguradora)){
					System.out.println("Seguradora já cadastrada");
					return;
				}
			}
			listaSeguradoras.add(seguradora);
			System.out.println("Seguradora cadastrada com sucesso");
			break;
		case CADASTRAR_CLIENTE:
			Seguradora seg_cliente = procurarSeguradora(listaSeguradoras);
			System.out.println("Digite o nome do cliente: ");
			String nome_cliente = scanner.nextLine();
			System.out.println("Digite o telefone do cliente: ");
			String telefone_cliente = scanner.nextLine();
			System.out.println("Digite o endereco do cliente: ");
			String endereco_cliente = scanner.nextLine();
			System.out.println("Digite o cpf do cliente: ");
			String cpf_cliente = scanner.nextLine();
			System.out.println("Digite o sexo do cliente: ");
			String sexo_cliente = scanner.nextLine();
			System.out.println("Digite a escolaridade do cliente: ");
			String escolaridade_cliente = scanner.nextLine();
			System.out.println("Digite a data de nascimento do cliente: ");
			String dataNascimento_cliente = scanner.nextLine();
			System.out.println("Digite o tipo de cliente: ");
			String tipoCliente = scanner.nextLine();
			if(tipoCliente.equals("PF")){
				try{
					SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
					Date dataNascimento = sdf.parse(dataNascimento_cliente);
					ClientePF clientePF = new ClientePF(nome_cliente, telefone_cliente, endereco_cliente, cpf_cliente, sexo_cliente, escolaridade_cliente, dataNascimento, new ArrayList<Veiculo>());
					if(seg_cliente.cadastrarCliente(clientePF))
						System.out.println("Cliente cadastrado com sucesso");
					else
						System.out.println("Cliente já cadastrado");
				}catch(ParseException e){
					System.out.println("Data inválida");
				}
			}
			else if(tipoCliente.equals("PJ")){
				System.out.println("Digite a data de fundação do cliente: ");
				String dataFundacao_cliente = scanner.nextLine();
				System.out.println("Digite o numero de funcionarios do cliente: ");
				int numeroFuncionarios_cliente = scanner.nextInt();
				try{
					SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
					Date dataFundacao = sdf.parse(dataFundacao_cliente);
					ClientePJ clientePJ = new ClientePJ(nome_cliente, telefone_cliente, endereco_cliente, cpf_cliente, dataFundacao, numeroFuncionarios_cliente, new ArrayList<Frota>());
					if(seg_cliente.cadastrarCliente(clientePJ))
						System.out.println("Cliente cadastrado com sucesso");
					else
						System.out.println("Cliente já cadastrado");
				}catch(ParseException e){
					System.out.println("Data inválida");
				}
			}
			break;
		case CADASTRAR_VEICULO:
			Seguradora seg_veiculo = procurarSeguradora(listaSeguradoras);
			System.out.println("Digite o CPF do cliente: ");
			String cpf = scanner.nextLine();
			System.out.println("Digite a placa do veículo: ");
			String placa = scanner.nextLine();
			System.out.println("Digite a marca do veículo: ");
			String marca = scanner.nextLine();
			System.out.println("Digite o modelo do veículo: ");
			String modelo = scanner.nextLine();
			System.out.println("Digite o ano do veículo: ");
			int ano = Integer.parseInt(scanner.nextLine());
			Veiculo veiculo = new Veiculo(placa, marca, modelo, ano);
			for(Cliente c: seg_veiculo.getListaClientes()){
				if(c instanceof ClientePF){
					if(((ClientePF)c).getCPF().equals(cpf))
						((ClientePF)c).cadastrarVeiculo(veiculo);
				}
			}
			break;
		case CADASTRAR_FROTA:
			Seguradora seg_frota = procurarSeguradora(listaSeguradoras);
			System.out.println("Digite o CNPJ do cliente: ");
			String cnpj_frota = scanner.nextLine();
			System.out.println("Digite o numero de veiculos da frota: ");
			int numeroVeiculos = Integer.parseInt(scanner.nextLine());
			ArrayList<Veiculo> listaVeiculos = new ArrayList<Veiculo>();
			for(int i=0; i<numeroVeiculos; i++){
				System.out.println("Digite a placa do veículo: ");
				String placa_frota = scanner.nextLine();
				System.out.println("Digite a marca do veículo: ");
				String marca_frota = scanner.nextLine();
				System.out.println("Digite o modelo do veículo: ");
				String modelo_frota= scanner.nextLine();
				System.out.println("Digite o ano do veículo: ");
				int ano_frota = Integer.parseInt(scanner.nextLine());
				Veiculo veiculo_frota = new Veiculo(placa_frota, marca_frota, modelo_frota, ano_frota);
				listaVeiculos.add(veiculo_frota);
			}
			for(Cliente c: seg_frota.getListaClientes()){
				if(c instanceof ClientePJ){
					if(((ClientePJ)c).getCNPJ().equals(cnpj_frota))
						((ClientePJ)c).cadastrarFrota(listaVeiculos);
				}
			}
			break;
		case CADASTRAR_SEGURO:
			Seguradora seg_seguro = procurarSeguradora(listaSeguradoras);
			System.out.println("Digite a data de início: ");
			String dataInicio = scanner.nextLine();
			System.out.println("Digite a data de fim: ");
			String dataFim = scanner.nextLine();
			System.out.println("Digite o tipo de seguro (PF/PJ): ");
			String tipoSeguro = scanner.nextLine();
			if(tipoSeguro == "PF"){
				try{
					SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
					Date dataInicioDate = sdf.parse(dataInicio);
					Date dataFimDate = sdf.parse(dataFim);
					System.out.println("Digite o CPF do cliente: ");
					String cpf_seguro = scanner.nextLine();
					ClientePF cliente_seg = null;
					for(Cliente c: seg_seguro.getListaClientes()){
						if(c instanceof ClientePF)
							if(((ClientePF)c).getCPF().equals(cpf_seguro))
								cliente_seg = (ClientePF)c;
					}
					System.out.println("Digite a placa do veículo: ");
					String placa_seg = scanner.nextLine();
					System.out.println("Digite a marca do veículo: ");
					String marca_seg = scanner.nextLine();
					System.out.println("Digite o modelo do veículo: ");
					String modelo_seg = scanner.nextLine();
					System.out.println("Digite o ano do veículo: ");
					int ano_seg = Integer.parseInt(scanner.nextLine());
					Veiculo veiculo_seg = new Veiculo(placa_seg, marca_seg, modelo_seg, ano_seg);
					SeguroPF seguroPF = new SeguroPF(dataInicioDate, dataFimDate, seg_seguro, new ArrayList<Sinistro>(), new ArrayList<Condutor>(), veiculo_seg, cliente_seg);
					if(seg_seguro.cadastrarSeguro(seguroPF))
						System.out.println("Seguro cadastrado com sucesso");
					else
						System.out.println("Seguro já cadastrado");
				}
				catch(ParseException e){
					System.out.println("Data inválida");
				}
			}
			else if(tipoSeguro == "PJ"){
				try{
					SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
					Date dataInicioDate = sdf.parse(dataInicio);
					Date dataFimDate = sdf.parse(dataFim);
					System.out.println("Digite o CNPJ do cliente: ");
					String cnpj_seg = scanner.nextLine();
					ClientePJ cliente_seg = null;
					for(Cliente c: seg_seguro.getListaClientes()){
						if(c instanceof ClientePJ)
							if(((ClientePJ)c).getCNPJ().equals(cnpj_seg))
								cliente_seg = (ClientePJ)c;
					}
					System.out.println("Digite o numero de veiculos da frota: ");
					int numeroVeiculos_seg = Integer.parseInt(scanner.nextLine());
					ArrayList<Veiculo> listaVeiculos_seg = new ArrayList<Veiculo>();
					for(int i=0; i<numeroVeiculos_seg; i++){
						System.out.println("Digite a placa do veículo: ");
						String placa_frota = scanner.nextLine();
						System.out.println("Digite a marca do veículo: ");
						String marca_frota = scanner.nextLine();
						System.out.println("Digite o modelo do veículo: ");
						String modelo_frota= scanner.nextLine();
						System.out.println("Digite o ano do veículo: ");
						int ano_frota = Integer.parseInt(scanner.nextLine());
						Veiculo veiculo_frota = new Veiculo(placa_frota, marca_frota, modelo_frota, ano_frota);
						listaVeiculos_seg.add(veiculo_frota);
					}
					SeguroPJ seguroPJ_seg = new SeguroPJ(dataInicioDate, dataFimDate, seg_seguro, new ArrayList<Sinistro>(), new ArrayList<Condutor>(), new Frota(listaVeiculos_seg), cliente_seg);
					if(seg_seguro.cadastrarSeguro(seguroPJ_seg))
						System.out.println("Seguro cadastrado com sucesso");
					else
						System.out.println("Seguro já cadastrado");
				}
				catch(ParseException e){
					System.out.println("Data inválida");
				}
			}
			break;
		case LISTAR_CLIENTES:
			Seguradora seg_listar = procurarSeguradora(listaSeguradoras);
			System.out.println(seg_listar.getListaClientes());
			break;
		case LISTAR_SEGUROS:
			Seguradora seg_listar_seguros = procurarSeguradora(listaSeguradoras);
			System.out.println("Digite o CPF/CPNJ do cliente: ");
			String cpf_cnpj = scanner.nextLine();
			for(Cliente c: seg_listar_seguros.getListaClientes()){
				if(c instanceof ClientePF){
					if(((ClientePF)c).getCPF().equals(cpf_cnpj))
						System.out.println(seg_listar_seguros.getSegurosPorCliente(c));
				}
				if(c instanceof ClientePJ){
					if(((ClientePJ)c).getCNPJ().equals(cpf_cnpj))
						System.out.println(seg_listar_seguros.getSegurosPorCliente(c));
				}
			}
			break;
		case LISTAR_SINISTROS:
			Seguradora seg_listar_sinistros = procurarSeguradora(listaSeguradoras);
			System.out.println("Digite o CPF/CPNJ do cliente: ");
			String cpf_cnpj_sinistros = scanner.nextLine();
			for(Cliente c: seg_listar_sinistros.getListaClientes()){
				if(c instanceof ClientePF){
					if(((ClientePF)c).getCPF().equals(cpf_cnpj_sinistros))
						System.out.println(seg_listar_sinistros.getSinistrosPorCliente(c));
				}
				if(c instanceof ClientePJ){
					if(((ClientePJ)c).getCNPJ().equals(cpf_cnpj_sinistros))
						System.out.println(seg_listar_sinistros.getSinistrosPorCliente(c));
				}
			}
			break;
		case EXCLUIR_CLIENTE:
			Seguradora seg_excluir_cliente = procurarSeguradora(listaSeguradoras);
			System.out.println("Digite o CPF/CPNJ do cliente: ");
			String cpf_cnpj_excluir = scanner.nextLine();
			for(Cliente c: seg_excluir_cliente.getListaClientes()){
				if(c instanceof ClientePF){
					if(((ClientePF)c).getCPF().equals(cpf_cnpj_excluir))
						seg_excluir_cliente.removerCliente(c);
				}
				if(c instanceof ClientePJ){
					if(((ClientePJ)c).getCNPJ().equals(cpf_cnpj_excluir))
						seg_excluir_cliente.removerCliente(c);
				}
			}
			break;
		case EXCLUIR_SEGURO:
			Seguradora seg_excluir_seguro = procurarSeguradora(listaSeguradoras);
			System.out.println("Digite o ID do seguro: ");
			int id_excluir = Integer.parseInt(scanner.nextLine());
			for(Seguro s: seg_excluir_seguro.getListaSeguros()){
				if(s.getId() == id_excluir)
					seg_excluir_seguro.cancelarSeguro(s);
			}
			break;
		case EXCLUIR_SINISTRO:
			Seguradora seg_excluir_sinistro = procurarSeguradora(listaSeguradoras);
			System.out.println("Digite o ID do seguro: ");
			int id_excluir_sinistro = Integer.parseInt(scanner.nextLine());
			Seguro seguro_excluir_sinistro = null;
			for(Seguro s: seg_excluir_sinistro.getListaSeguros()){
				if(s.getId() == id_excluir_sinistro)
					seguro_excluir_sinistro = s;
			}
			System.out.println("Digite o ID do sinistro: ");
			int id_sinistro = Integer.parseInt(scanner.nextLine());
			for(Sinistro s: seguro_excluir_sinistro.getListaSinistros()){
				if(s.getID() == id_sinistro)
					seguro_excluir_sinistro.getListaSinistros().remove(s);
			}
			for(Condutor cond : seguro_excluir_sinistro.getListacondutores()){
				for(Sinistro s: cond.getListaSinistros()){
					if(s.getID() == id_sinistro)
						cond.getListaSinistros().remove(s);
				}
			}
			break;
		case VOLTAR:
			break;
		}
	}
	
	//executa os submenus: exibição do menu, leitura da opção e execução dos métodos
	private static void executarSubmenu(MenuOpcoes op, ArrayList<Seguradora> listaSeguradoras) {
		SubmenuOpcoes opSubmenu;
		do {
			exibirSubmenu(op);
			opSubmenu = lerOpcaoSubmenu(op);
			executarOpcaoSubMenu(opSubmenu, listaSeguradoras);
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