import java.util.ArrayList;
import java.util.Date;

public class ClientePF extends Cliente{
    private String CPF;
    private Date dataNascimento;    

    public ClientePF(String nome, String endereco, Date dataLicenca, String educacao, String genero,
            String classeEconomica, ArrayList<Veiculo> listaVeiculos, String CPF, Date dataNascimento) {
        super(nome, endereco, dataLicenca, educacao, genero, classeEconomica, listaVeiculos);
        this.CPF = CPF;
        this.dataNascimento = dataNascimento;
    }

    public boolean validarCPF(String cpf) {
        cpf = cpf.replaceAll("[^0-9]", "");
        
        if(cpf.length() != 11) return false;

        boolean differ = false;
        for(int i=0;i<cpf.length()-1;i++){
            if( cpf.charAt(i) != cpf.charAt(i+1) ) differ = true;
        }
        if(!differ) return false;

        int mult = 10, soma = 0;
        for(int i=0;i<9;i++){
            soma += ((int)cpf.charAt(i)-48) * mult;
            mult--;
        }

        int primeiro;
        if(soma % 11 < 2) primeiro = 0;
        else primeiro = 11 - (soma%11);

        mult = 11;
        soma = 0;
        for(int i=0; i<10; i++){
            soma += ((int)cpf.charAt(i)-48) * mult;
            mult--;
        }

        int segundo;
        if(soma % 11 < 2) segundo = 0;
        else segundo = 11- (soma % 11);

        if((int)cpf.charAt(9)-48 == primeiro && (int)cpf.charAt(10)-48== segundo) return true;
        else return false;
    }

    public String getCPF() {
        return CPF;
    }

    public void setCPF(String CPF) {
        this.CPF = CPF;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    @Override
    public String toString() {
        return super.toString() + "\n" + "ClientePF [CPF=" + CPF + ", dataNascimento=" + dataNascimento + "]";
    }

}
