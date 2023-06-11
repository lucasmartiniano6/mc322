import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

public abstract class Seguro {
    private final int id;
    private Date dataInicio;
    private Date dataFim;
    private Seguradora seguradora;
    private ArrayList<Sinistro> listaSinistros;
    private ArrayList<Condutor> listacondutores;
    private double valorMensal;

    public Seguro(Date dataInicio, Date dataFim, Seguradora seguradora, ArrayList<Sinistro> listaSinistros,
            ArrayList<Condutor> listacondutores) {
        this.id = gerarIdUnico();
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.seguradora = seguradora;
        this.listaSinistros = listaSinistros;
        this.listacondutores = listacondutores;
    }

    public boolean desautorizarCondutor(Condutor condutor){
        for(Condutor c: listacondutores){
            if(c.getCpf().equals(condutor.getCpf())){
                listacondutores.remove(c);
                return true;
            }
        }
        return false;
    }

    public boolean autorizarCondutor(Condutor condutor){
        for(Condutor c: listacondutores){
            if(c.getCpf().equals(condutor.getCpf())){
                // Condutor já autorizado
                return false;
            }
        }
        listacondutores.add(condutor);
        return true;
    }

    public boolean gerarSinistro(Date data, String endereco, Condutor condutor, Seguro seguro){
        Sinistro sinistro = new Sinistro(data, endereco, condutor, seguro);
        listaSinistros.add(sinistro);
        condutor.adicionarSinistro(sinistro);
        return true;
    }

    public abstract double calcularValor();

    private int gerarIdUnico(){
        Random random = new Random();
        return random.nextInt(10000000);
    }    

    public int getId() {
        return id;
    }
    public Date getDataInicio() {
        return dataInicio;
    }
    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }
    public Date getDataFim() {
        return dataFim;
    }
    public void setDataFim(Date dataFim) {
        this.dataFim = dataFim;
    }
    public Seguradora getSeguradora() {
        return seguradora;
    }
    public void setSeguradora(Seguradora seguradora) {
        this.seguradora = seguradora;
    }
    public ArrayList<Sinistro> getListaSinistros() {
        return listaSinistros;
    }
    public void setListaSinistros(ArrayList<Sinistro> listaSinistros) {
        this.listaSinistros = listaSinistros;
    }
    public ArrayList<Condutor> getListacondutores() {
        return listacondutores;
    }
    public void setListacondutores(ArrayList<Condutor> listacondutores) {
        this.listacondutores = listacondutores;
    }
    public double getValorMensal() {
        return valorMensal;
    }
    public void setValorMensal(double valorMensal) {
        this.valorMensal = valorMensal;
    }
    @Override
    public String toString() {
        return "Seguro [id=" + id + ", dataInicio=" + dataInicio + ", dataFim=" + dataFim + ", seguradora=" + seguradora
                + ", listaSinistros=" + listaSinistros + ", listacondutores=" + listacondutores + ", valorMensal="
                + valorMensal + "]";
    }
}
