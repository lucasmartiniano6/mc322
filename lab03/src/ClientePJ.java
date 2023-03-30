import java.util.ArrayList;
import java.util.Date;;

public class ClientePJ extends Cliente{
    private String CNPJ;
    private Date dataFundacao;

    public ClientePJ(String nome, String endereco, Date dataLicenca, String educacao, String genero,
            String classeEconomica, ArrayList<Veiculo> listaVeiculos, String CNPJ, Date dataFundacao) {
        super(nome, endereco, dataLicenca, educacao, genero, classeEconomica, listaVeiculos);
        this.CNPJ = CNPJ;
        this.dataFundacao = dataFundacao;
    }

    public boolean validarCNPJ(String cnpj){

        return true;
    }

    public String getCNPJ() {
        return CNPJ;
    }
    public void setCNPJ(String CNPJ) {
        this.CNPJ = CNPJ;
    }
    public Date getDataFundacao() {
        return dataFundacao;
    }
    public void setDataFundacao(Date dataFundacao) {
        this.dataFundacao = dataFundacao;
    }
    
}

