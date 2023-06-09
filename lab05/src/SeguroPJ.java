import java.util.ArrayList;
import java.util.Date;

public class SeguroPJ extends Seguro {
    Frota frota;
    ClientePJ cliente;

    public SeguroPJ(Date dataInicio, Date dataFim, Seguradora seguradora, ArrayList<Sinistro> listaSinistros,
            ArrayList<Condutor> listacondutores, Frota frota, ClientePJ cliente) {
        super(dataInicio, dataFim, seguradora, listaSinistros, listacondutores);
        this.frota = frota;
        this.cliente = cliente;
    }
    
    public double calcularValor(){
        int age = (int) ((new Date().getTime() - cliente.getDataFundacao().getTime()) / 86400000 / 365.25);

        double qtdeSinistrosCondutor = 0;
        for(Condutor c: getListacondutores()){
            qtdeSinistrosCondutor += c.getListaSinistros().size();
        }
        double qtdeSinistrosCliente = getListaSinistros().size();

        setValorMensal( 
            CalcSeguro.VALOR_BASE.num * (10 + getCliente().getQuantidadeFunc()/10) * 
            (1 + 1/(getFrota().getListaVeiculos().size()+2)) * 
            (1 + 1/(age+2)) * 
            (2 + qtdeSinistrosCliente/10) * 
            (5 + qtdeSinistrosCondutor/10)
        );
        return getValorMensal();
    }

    public Frota getFrota() {
        return frota;
    }
    public void setFrota(Frota frota) {
        this.frota = frota;
    }
    public ClientePJ getCliente() {
        return cliente;
    }
    public void setCliente(ClientePJ cliente) {
        this.cliente = cliente;
    }
    @Override
    public String toString() {
        return "SeguroPJ [frota=" + frota + ", cliente=" + cliente + "]";
    }
}
