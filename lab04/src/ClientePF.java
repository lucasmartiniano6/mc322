import java.util.ArrayList;
import java.util.Date;

import javax.swing.event.ListDataEvent;

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
        return CalcSeguro.VALOR_BASE.num * getListaVeiculos().size();
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
