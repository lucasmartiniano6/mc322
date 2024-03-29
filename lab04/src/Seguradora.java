import java.util.ArrayList;

public class Seguradora {
    private String nome;
    private String telefone;
    private String email;
    private String endereco;
    private ArrayList<Sinistro> listaSinistros;
    private ArrayList<Cliente> listaClientes;

    public Seguradora(String nome, String telefone, String email, String endereco, ArrayList<Sinistro> listaSinistros,
            ArrayList<Cliente> listaClientes) {
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
        this.endereco = endereco;
        this.listaSinistros = listaSinistros;
        this.listaClientes = listaClientes;
    }

    public boolean cadastrarCliente(Cliente cliente){
        // Adiciona Cliente na lista de clientes da seguradora
        for(Cliente c: listaClientes){
            if(c.equals(cliente))
                return false;
        }
        listaClientes.add(cliente);
        return true;
    }
    
    public boolean removerCliente(String cliente_str){
        // Remover Cliente da lista de clientes da seguradora
        // cliente_str pode ser CPF ou CNPJ
        for(Cliente c: listaClientes){
            if(c instanceof ClientePF){
                if(((ClientePF) c).getCPF().equals(cliente_str)){
                    listaClientes.remove(c);
                    removerSinistro(c);
                    return true;
                }
            }
            else if(c instanceof ClientePJ){
                if(((ClientePJ) c).getCNPJ().equals(cliente_str)){
                    listaClientes.remove(c);
                    removerSinistro(c);
                    return true;
                }
            }
        }
        return false;
    }
    
    public boolean removerCliente(Cliente cliente){
        for(Cliente c: listaClientes){
            if(c.equals(cliente)){
                listaClientes.remove(c);
                removerSinistro(c);
                return true;
            }
        }
        return false;
    }
    
    public boolean removerSinistro(Cliente cliente){
        // Remover Sinistro da lista de sinistros da seguradora
        for(Sinistro s: listaSinistros){
            if(s.getCliente().equals(cliente)){
                listaSinistros.remove(s);
                return true;
            }
        }
        return false;
    }

    public void listarClientes(String tipoCliente){
        // tipoCliente pode ser "PF", "PJ" ou todos
        if(tipoCliente.equals("PF")){
            for(Cliente c: listaClientes){
                if(c instanceof ClientePF)
                    System.out.println(c);
            }
        }
        else if(tipoCliente.equals("PJ")){
            for(Cliente c: listaClientes){
                if(c instanceof ClientePJ)
                    System.out.println(c);
            }
        }
        else{
            for(Cliente c: listaClientes){
                System.out.println(c);
            }
        }
    }

    public boolean gerarSinistro(String data, String endereco, Seguradora seguradora, Veiculo veiculo, Cliente cliente){
        // criar objeto sinistro
        Sinistro s = new Sinistro(data, endereco, seguradora, veiculo, cliente);
        // cadastrar sinistro
        listaSinistros.add(s);
        return true;
    }

    public boolean vizualizarSinistro(String cliente_str){
        // cliente_str pode ser CPF ou CNPJ
        for(Sinistro s: listaSinistros){
            if(s.getCliente() instanceof ClientePF){
                if(((ClientePF) s.getCliente()).getCPF().equals(cliente_str)){
                    System.out.println(s);
                    return true;
                }
            }
            else if(s.getCliente() instanceof ClientePJ){
                if(((ClientePJ) s.getCliente()).getCNPJ().equals(cliente_str)){
                    System.out.println(s);
                    return true;
                }
            }
        }
        return false;
    }

    public void listarSinistros(){
        for(Sinistro s: listaSinistros){
            System.out.println(s);
        }
    }

    public void listarVeiculos(){
        for(Cliente c: listaClientes){
            System.out.println("Cliente " + c.getNome() + ": ");
            for(Veiculo v: c.getListaVeiculos()){
                System.out.println(v);
            }
        }
    }
    
    public void calcularPrecoSeguroCliente(Cliente cliente){
        int quantidade_de_sinistros = 0;
        for(Sinistro s: listaSinistros){
            if(s.getCliente().equals(cliente)){
                quantidade_de_sinistros++;
            }
        }
        double valor_seguro = cliente.calculaScore() * (1 + quantidade_de_sinistros );
        cliente.setValorSeguro(valor_seguro);
    }
    
    public double calcularReceita(){
        double receita = 0;
        for(Cliente c: listaClientes){
            calcularPrecoSeguroCliente(c);
            receita += c.getValorSeguro();
        }
        return receita;
    }
    
    public boolean transferirSeguro(String nomeOrigem, String nomeDestino){
        Cliente origem = null;
        Cliente destino = null;
        for(Cliente c: listaClientes){
            if(c.getNome().equals(nomeOrigem)) origem = c;
            else if(c.getNome().equals(nomeDestino)) destino = c;
        }
        destino.setListaVeiculos(origem.getListaVeiculos());
        calcularPrecoSeguroCliente(destino);
        removerCliente(origem);
        return true;
    }
    
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getTelefone() {
        return telefone;
    }
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getEndereco() {
        return endereco;
    }
    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }
    public ArrayList<Sinistro> getListaSinistros() {
        return listaSinistros;
    }
    public void setListaSinistros(ArrayList<Sinistro> listaSinistros) {
        this.listaSinistros = listaSinistros;
    }
    public ArrayList<Cliente> getListaClientes() {
        return listaClientes;
    }
    public void setListaClientes(ArrayList<Cliente> listaClientes) {
        this.listaClientes = listaClientes;
    }

    @Override
    public String toString() {
        return "Seguradora [nome=" + nome + ", telefone=" + telefone + ", email=" + email + ", endereco=" + endereco
                + ", listaSinistros=" + listaSinistros + ", listaClientes=" + listaClientes + "]";
    }
}