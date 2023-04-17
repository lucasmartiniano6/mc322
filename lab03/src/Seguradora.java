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
        for(Cliente c: listaClientes){
            if(c.equals(cliente))
                return false;
        }
        listaClientes.add(cliente);
        return true;
    }
    
    public boolean removerCliente(String cliente_str){
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
    
    public boolean removerSinistro(Cliente cliente){
        for(Sinistro s: listaSinistros){
            if(s.getCliente().equals(cliente)){
                listaSinistros.remove(s);
                return true;
            }
        }
        return false;
    }

    public void listarClientes(String tipoCliente){
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

    public boolean gerarSinistro(String data, String endereco){
        // criar objeto sinistro
        Sinistro s = new Sinistro(data, endereco, null, null, null);
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