/* enum para menu externo
Cada constante é vista como uma descrição e uma lista de outras constantes (que são as constantes do submenu)

new SubmenuOpcoes[]{} cria uma lista de constantes do submenu.
*/
public enum MenuOpcoes {
	CADASTROS("Cadastros", new SubmenuOpcoes[] {
		  	SubmenuOpcoes.CADASTRAR_SEGURADORA,
			SubmenuOpcoes.CADASTRAR_CLIENTE,
			SubmenuOpcoes.CADASTRAR_VEICULO,
			SubmenuOpcoes.CADASTRAR_FROTA,
			SubmenuOpcoes.CADASTRAR_SEGURO,
			SubmenuOpcoes.VOLTAR
	}),
	LISTAR("Listar", new SubmenuOpcoes[] {
			SubmenuOpcoes.LISTAR_SEGUROS,
			SubmenuOpcoes.LISTAR_CLIENTES,
			SubmenuOpcoes.LISTAR_SINISTROS,
			SubmenuOpcoes.VOLTAR
	}),
	EXCLUIR("Excluir", new SubmenuOpcoes[] {
			SubmenuOpcoes.EXCLUIR_SEGURO,
			SubmenuOpcoes.EXCLUIR_CLIENTE,
			SubmenuOpcoes.EXCLUIR_SINISTRO,
			SubmenuOpcoes.VOLTAR}),
	GERAR_SINISTRO("Gerar Sinistro", new SubmenuOpcoes[] {SubmenuOpcoes.VOLTAR}),
	AUTORIZAR_CONDUTOR("Autorizar condutor", new SubmenuOpcoes[] {SubmenuOpcoes.VOLTAR}),
	DESAUTORIZAR_CONDUTOR("Desautorizar condutor", new SubmenuOpcoes[] {SubmenuOpcoes.VOLTAR}),
	CALCULAR_RECEITA("Calcular Receita", new SubmenuOpcoes[] {SubmenuOpcoes.VOLTAR}),
	SAIR("Sair", new SubmenuOpcoes[] {});
	
	//atributos
	private final String descricao;
	private final SubmenuOpcoes[] submenu;
	
	//Construtor
	MenuOpcoes(String descricao, SubmenuOpcoes[] submenu){
		this.descricao = descricao;
		this.submenu = submenu;
	}
	
	//getters
	public String getDescricao() {
		return descricao;
	}
	
	public SubmenuOpcoes[] getSubmenu() {
		return submenu;
	}
}
