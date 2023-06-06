import java.util.ArrayList;
import java.util.Date;

public class ClientePF extends Cliente{
    private final String CPF;
    private String genero;
    private String educacao;
    private Date dataNascimento;    
    private ArrayList<Veiculo> listaVeiculos;

    public ClientePF(String nome, String telefone, String endereco, String cPF, String genero, String educacao,
            Date dataNascimento, ArrayList<Veiculo> listaVeiculos) {
        super(nome, telefone, endereco);
        CPF = cPF;
        this.genero = genero;
        this.educacao = educacao;
        this.dataNascimento = dataNascimento;
        this.listaVeiculos = listaVeiculos;
    }
    
    public boolean cadastrarVeiculo(Veiculo v){
        for(Veiculo veiculo : listaVeiculos){
            if(veiculo.getPlaca().equals(v.getPlaca())){
                return false;
            }
        }
        listaVeiculos.add(v);
        return true;
    }

    public boolean removerVeiculo(Veiculo v){
        for(Veiculo veiculo : listaVeiculos){
            if(veiculo.getPlaca().equals(v.getPlaca())){
                listaVeiculos.remove(veiculo);
                return true;
            }
        }
        return false;
    }

    public String getCPF() {
        return CPF;
    }
    public String getGenero() {
        return genero;
    }
    public void setGenero(String genero) {
        this.genero = genero;
    }
    public String getEducacao() {
        return educacao;
    }
    public void setEducacao(String educacao) {
        this.educacao = educacao;
    }
    public Date getDataNascimento() {
        return dataNascimento;
    }
    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }
    public ArrayList<Veiculo> getListaVeiculos() {
        return listaVeiculos;
    }
    public void setListaVeiculos(ArrayList<Veiculo> listaVeiculos) {
        this.listaVeiculos = listaVeiculos;
    }
    @Override
    public String toString() {
        return "ClientePF [CPF=" + CPF + ", genero=" + genero + ", educacao=" + educacao + ", dataNascimento="
                + dataNascimento + ", listaVeiculos=" + listaVeiculos + "]";
    }

}