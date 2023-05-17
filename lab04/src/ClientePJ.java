import java.util.ArrayList;
import java.util.Date;

public class ClientePJ extends Cliente{
    private final String CNPJ;
    private Date dataFundacao;
    
    public ClientePJ(String nome, String endereco, ArrayList<Veiculo> listaVeiculos, String cNPJ, Date dataFundacao) {
        super(nome, endereco, listaVeiculos);
        CNPJ = cNPJ;
        this.dataFundacao = dataFundacao;
    }

    public double calculaScore(){
        return 0;
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
    
    @Override
    public String toString() {
        return super.toString() + "\n"+ "ClientePJ [CNPJ=" + CNPJ + ", dataFundacao=" + dataFundacao + "]";
    }    

}
