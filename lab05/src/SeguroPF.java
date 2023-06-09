import java.util.ArrayList;
import java.util.Date;

public class SeguroPF extends Seguro{
    private Veiculo veiculo;
    private ClientePF cliente;

    public SeguroPF(Date dataInicio, Date dataFim, Seguradora seguradora, ArrayList<Sinistro> listaSinistros,
            ArrayList<Condutor> listacondutores, Veiculo veiculo, ClientePF cliente) {
        super(dataInicio, dataFim, seguradora, listaSinistros, listacondutores);
        this.veiculo = veiculo;
        this.cliente = cliente;
    }

   
    public double calcularValor(){
        int age = (int) ((new Date().getTime() - cliente.getDataNascimento().getTime()) / 86400000 / 365.25);
        double fator_idade = 1;
        if(age >= 18 && age < 30)
            fator_idade = CalcSeguro.FATOR_18_30.num; 
        else if(age >= 30 && age < 60)
            fator_idade = CalcSeguro.FATOR_30_60.num;
        else if(age >= 60 && age < 90)
            fator_idade = CalcSeguro.FATOR_60_90.num;

        double qtdeSinistrosCondutor = 0;
        for(Condutor c: getListacondutores()){
            qtdeSinistrosCondutor += c.getListaSinistros().size();
        }
        double qtdeSinistrosCliente = getListaSinistros().size();

        setValorMensal( 
            CalcSeguro.VALOR_BASE.num * fator_idade * (1.0 + 1.0/(cliente.getListaVeiculos().size()+2.0))
            * (2.0 + qtdeSinistrosCliente/10.0) * (5.0 + qtdeSinistrosCondutor/10.0)
        );
        return getValorMensal();
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }
    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }
    public ClientePF getCliente() {
        return cliente;
    }
    public void setCliente(ClientePF cliente) {
        this.cliente = cliente;
    }
    @Override
    public String toString() {
        return "SeguroPF [veiculo=" + veiculo + ", cliente=" + cliente + "]";
    }

}
