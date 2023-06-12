import java.util.ArrayList;
import java.util.Date;

public class ClientePJ extends Cliente{
    private final String CNPJ;
    private Date dataFundacao;
    private int quantidadeFunc;
    private ArrayList<Frota> listaFrota;

    public ClientePJ(String nome, String telefone, String endereco, String cNPJ, Date dataFundacao, int quantidadeFunc,
            ArrayList<Frota> listaFrota) {
        super(nome, telefone, endereco);
        CNPJ = cNPJ;
        this.dataFundacao = dataFundacao;
        this.quantidadeFunc = quantidadeFunc;
        this.listaFrota = listaFrota;
    }

    public boolean cadastrarFrota(ArrayList<Veiculo> listaVeiculos){
        Frota frota = new Frota(listaVeiculos);
        listaFrota.add(frota);
        return true;
    }

    public boolean atualizarFrota(Frota frota, Veiculo veiculo){
        // Adicionar (se não existir) ou remover (caso exista) veiculo em uma frota
        for(Frota f : listaFrota){
            if(f.getCode().equals(frota.getCode())){
                // Frota encontrada
                for(Veiculo v : f.getListaVeiculos()){
                    if(v.getPlaca().equals(veiculo.getPlaca())){
                        // Veiculo encontrado
                        return f.removeVeiculo(v);
                    }
                }
                // Veiculo não encontrado
                return f.addVeiculo(veiculo);
            }
        }
        return false;
    }

    public boolean atualizarFrota(Frota frota){
        // Remover a frota por completo
        for(Frota f : listaFrota){
            if(f.getCode().equals(frota.getCode())){
                listaFrota.remove(f);
                return true;
            }
        }
        return false;
    }

    public ArrayList<Veiculo> getVeiculosPorFrota(Frota frota){
        ArrayList<Veiculo> listaVeiculos = new ArrayList<Veiculo>();
        for(Frota f : listaFrota){
            if(f.getCode().equals(frota.getCode())){
                return listaVeiculos = f.getListaVeiculos();
            }
        }
        return listaVeiculos;
    }

    public String getCNPJ() {
        return CNPJ;
    }
    public Date getDataFundacao() {
        return dataFundacao;
    }
    public void setDataFundacao(Date dataFundacao) {
        this.dataFundacao = dataFundacao;
    }
    public int getQuantidadeFunc() {
        return quantidadeFunc;
    }
    public void setQuantidadeFunc(int quantidadeFunc) {
        this.quantidadeFunc = quantidadeFunc;
    }
    public ArrayList<Frota> getListaFrota() {
        return listaFrota;
    }
    public void setListaFrota(ArrayList<Frota> listaFrota) {
        this.listaFrota = listaFrota;
    }
    @Override
    public String toString() {
        return "ClientePJ [CNPJ=" + CNPJ + ", dataFundacao=" + dataFundacao + ", quantidadeFunc=" + quantidadeFunc
                + ", listaFrota=" + listaFrota + "]";
    }
    

}
