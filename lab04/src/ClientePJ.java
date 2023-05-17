import java.util.ArrayList;
import java.util.Date;

public class ClientePJ extends Cliente{
    private final String CNPJ;
    private Date dataFundacao;
    private int quantidadeFunc;
    
    public ClientePJ(String nome, String endereco, ArrayList<Veiculo> listaVeiculos, String cNPJ, Date dataFundacao, int quantidadeFunc) {
        super(nome, endereco, listaVeiculos);
        CNPJ = cNPJ;
        this.dataFundacao = dataFundacao;
        this.quantidadeFunc = quantidadeFunc;
    }

    public double calculaScore(){
        return CalcSeguro.VALOR_BASE.num * getListaVeiculos().size() * (1 + ( quantidadeFunc ) /100);
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
     
    @Override
    public String toString() {
        return super.toString() + "\n"+ "ClientePJ [CNPJ=" + CNPJ + ", dataFundacao=" + dataFundacao + "]";
    }    

}
