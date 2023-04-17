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

    public boolean validarCNPJ(String cnpj){
        cnpj = cnpj.replaceAll("[^0-9]", "");
        
        if(cnpj.length() != 14) return false;

        boolean differ = false;
        for(int i=0;i<cnpj.length()-1;i++){
            if( cnpj.charAt(i) != cnpj.charAt(i+1) ) differ = true;
        }
        if(!differ) return false;

        int soma = 0;
        int mult[] = {5,4,3,2,9,8,7,6,5,4,3,2};
        for(int i=0;i<mult.length;i++){
            soma += ((int)cnpj.charAt(i)-48) * mult[i];
        }

        int primeiro;
        if(soma % 11 < 2) primeiro = 0;
        else primeiro = 11 - (soma%11);

        soma = 0;
        mult = new int[]{6,5,4,3,2,9,8,7,6,5,4,3,2};
        for(int i=0; i<mult.length; i++){
            soma += ((int)cnpj.charAt(i)-48) * mult[i];
        }

        int segundo;
        if(soma % 11 < 2) segundo = 0;
        else segundo = 11- (soma % 11);

        if((int)cnpj.charAt(12)-48 == primeiro && (int)cnpj.charAt(13)-48== segundo) return true;
        else return false;
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
