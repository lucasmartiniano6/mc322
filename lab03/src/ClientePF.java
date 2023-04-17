import java.util.ArrayList;
import java.util.Date;

public class ClientePF extends Cliente{
    private final String CPF;
    private String genero;
    private Date dataLicenca;    
    private String educacao;
    private Date dataNascimento;    
    private String classeEconomica;

    public ClientePF(String nome, String endereco, ArrayList<Veiculo> listaVeiculos, String cpf, String genero,
            Date dataLicenca, String educacao, Date dataNascimento, String classeEconomica) {
        super(nome, endereco, listaVeiculos);
        this.CPF = cpf;
        this.genero = genero;
        this.dataLicenca = dataLicenca;
        this.educacao = educacao;
        this.dataNascimento = dataNascimento;
        this.classeEconomica = classeEconomica;
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

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public Date getDataLicenca() {
        return dataLicenca;
    }

    public void setDataLicenca(Date dataLicenca) {
        this.dataLicenca = dataLicenca;
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

    public String getClasseEconomica() {
        return classeEconomica;
    }

    public void setClasseEconomica(String classeEconomica) {
        this.classeEconomica = classeEconomica;
    }

    @Override
    public String toString() {
        return super.toString() +"\n"+ "ClientePF [CPF=" + CPF + ", genero=" + genero + ", dataLicenca=" + dataLicenca + ", educacao="
                + educacao + ", dataNascimento=" + dataNascimento + ", classeEconomica=" + classeEconomica + "]";
    }
}
