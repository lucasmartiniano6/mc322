import java.util.ArrayList;

public class Seguradora {
    private final String cnpj;
    private String nome;
    private String telefone;
    private String endereco;
    private String email;
    private ArrayList<Cliente> listaClientes;
    private ArrayList<Seguro> listaSeguros;

    public Seguradora(String cnpj, String nome, String telefone, String endereco, String email,
            ArrayList<Cliente> listaClientes, ArrayList<Seguro> listaSeguros) {
        this.cnpj = cnpj;
        this.nome = nome;
        this.telefone = telefone;
        this.endereco = endereco;
        this.email = email;
        this.listaClientes = listaClientes;
        this.listaSeguros = listaSeguros;
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
                    return true;
                }
            }
            else if(c instanceof ClientePJ){
                if(((ClientePJ) c).getCNPJ().equals(cliente_str)){
                    listaClientes.remove(c);
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

    public double calcularReceita(){
        double receita = 0;
        for(Seguro s: listaSeguros){
            s.calcularValor();
            receita += s.getValorMensal();
        }
        return receita;
    }
    
    public String getCnpj() {
        return cnpj;
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

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public ArrayList<Cliente> getListaClientes() {
        return listaClientes;
    }

    public void setListaClientes(ArrayList<Cliente> listaClientes) {
        this.listaClientes = listaClientes;
    }

    public ArrayList<Seguro> getListaSeguros() {
        return listaSeguros;
    }

    public void setListaSeguros(ArrayList<Seguro> listaSeguros) {
        this.listaSeguros = listaSeguros;
    }

    @Override
    public String toString() {
        return "Seguradora [cnpj=" + cnpj + ", nome=" + nome + ", telefone=" + telefone + ", endereco=" + endereco
                + ", email=" + email + ", listaClientes=" + listaClientes + ", listaSeguros=" + listaSeguros + "]";
    }
} 