import java.util.ArrayList;
import java.util.Date;

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
    
    public void listarClientes(){
        for (Cliente cliente : listaClientes) {
            System.out.println(cliente);
        }
    }
    
    public boolean gerarSeguro(Date dataInicio, Date dataFim, Seguradora seguradora, ArrayList<Sinistro> listaSinistros,
            ArrayList<Condutor> listacondutores, Veiculo veiculo, ClientePF cliente){
        // Gerar seguro de Cliente PF
        Seguro seguro = new SeguroPF(dataInicio, dataFim, seguradora, listaSinistros, listacondutores, veiculo, cliente);
        // Adicionar seguro na lista de seguros
        for(Seguro s: listaSeguros){
            if(s.equals(seguro)){
                // Seguro já existe
                return false;
            }
        }
        seguro.calcularValor();
        listaSeguros.add(seguro);
        return true;
    }
    
    public boolean gerarSeguro(Date dataInicio, Date dataFim, Seguradora seguradora, ArrayList<Sinistro> listaSinistros,
            ArrayList<Condutor> listacondutores, Frota frota, ClientePJ cliente){
        // Gerar seguro de Cliente PJ
        Seguro seguro = new SeguroPJ(dataInicio, dataFim, seguradora, listaSinistros, listacondutores, frota, cliente);
        // Adicionar seguro na lista de seguros
        for(Seguro s: listaSeguros){
            if(s.equals(seguro)){
                // Seguro já existe
                return false;
            }
        }
        seguro.calcularValor();
        listaSeguros.add(seguro);
        return true;
    }

    public boolean cancelarSeguro(Seguro seguro){
        for(Seguro s: listaSeguros){
            if(s.getId() == seguro.getId()){
                listaSeguros.remove(s);
                return true;
            }
        }
        return false;
    }

    public boolean cadastrarCliente(Cliente cliente){
        for(Cliente c: listaClientes){
            if(c.equals(cliente)){
                // Cliente já existe
                return false;
            }
        }
        listaClientes.add(cliente);
        return true;
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

    public ArrayList<Seguro> getSegurosPorCliente(Cliente cliente){
        ArrayList<Seguro> seguros = new ArrayList<Seguro>();
        for(Seguro s: listaSeguros){
            if(s instanceof SeguroPF){
                if(((SeguroPF) s).getCliente().equals(cliente)){
                    seguros.add(s);
                }
            }else if(s instanceof SeguroPJ){
                if(((SeguroPJ) s).getCliente().equals(cliente)){
                    seguros.add(s);
                }
            }
        }
        return seguros;
    }

    public ArrayList<Sinistro> getSinistrosPorCliente(Cliente cliente){
        ArrayList<Sinistro> sinistros = new ArrayList<Sinistro>();
        for(Seguro s: listaSeguros){
            if(s instanceof SeguroPF){
                if(((SeguroPF) s).getCliente().equals(cliente)){
                    sinistros.addAll(s.getListaSinistros());
                }
            }else if(s instanceof SeguroPJ){
                if(((SeguroPJ) s).getCliente().equals(cliente)){
                    sinistros.addAll(s.getListaSinistros());
                }
            }
        }
        return sinistros;
    }

    public double calcularReceita(){
        double receita = 0;
        for(Seguro s: listaSeguros){
            receita += s.calcularValor();
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