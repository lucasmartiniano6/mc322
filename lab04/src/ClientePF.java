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

    public double calculaScore(){
        // calculate age based on date of birth
        int age = (int) ((new Date().getTime() - dataNascimento.getTime()) / 86400000 / 365.25);
        double fator_idade = 1;
        if(age >= 18 && age < 30)
            fator_idade = CalcSeguro.FATOR_18_30.num; 
        else if(age >= 30 && age < 60)
            fator_idade = CalcSeguro.FATOR_30_60.num;
        else if(age >= 60 && age < 90)
            fator_idade = CalcSeguro.FATOR_60_90.num;

        return CalcSeguro.VALOR_BASE.num * getListaVeiculos().size() * fator_idade;
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
