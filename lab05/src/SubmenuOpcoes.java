/*
 * Define as constantes dos submenus
 */
public enum SubmenuOpcoes {
	CADASTRAR_SEGURADORA("Cadastrar seguradora"),
	CADASTRAR_CLIENTE("Cadastrar cliente"),
	CADASTRAR_VEICULO("Cadastrar veiculo"),
	CADASTRAR_FROTA("Cadastrar frota"),
	CADASTRAR_SEGURO("Cadastrar seguro"),
	LISTAR_CLIENTES("Listar clientes"),
	LISTAR_SEGUROS("Listar seguros"),
	LISTAR_SINISTROS("Listar sinistros"),
	EXCLUIR_CLIENTE("Excluir cliente"),
	EXCLUIR_SEGURO("Excluir seguro"),
	EXCLUIR_SINISTRO("Excluir sininstro"),
	VOLTAR("Voltar");
	
	//atributo
	private final String descricao;
	
	//Construtor
	SubmenuOpcoes(String descricao){
		this.descricao = descricao;
	}
	
	//getter
	public String getDescricao() {
		return descricao;
	}
}
