import java.util.ArrayList;
import java.util.Random;

public class Frota {
    private final String code;
    private ArrayList<Veiculo> listaVeiculos;

    public Frota(ArrayList<Veiculo> listaVeiculos) {
        this.listaVeiculos = listaVeiculos;
        Random random = new Random();
        this.code = Integer.toString(random.nextInt(10000));
    }

    public boolean addVeiculo(Veiculo v){
        for(Veiculo veiculo : listaVeiculos){
            if(veiculo.getPlaca().equals(v.getPlaca())){
                return false;
            }
        }
        listaVeiculos.add(v);
        return true;
    }
    
    public boolean removeVeiculo(Veiculo v){
        for(Veiculo veiculo : listaVeiculos){
            if(veiculo.getPlaca().equals(v.getPlaca())){
                listaVeiculos.remove(veiculo);
                return true;
            }
        }
        return false;
    }

    public String getCode() {
        return code;
    }
    public ArrayList<Veiculo> getListaVeiculos() {
        return listaVeiculos;
    }
    public void setListaVeiculos(ArrayList<Veiculo> listaVeiculos) {
        this.listaVeiculos = listaVeiculos;
    }
    @Override
    public String toString() {
        return "Frota [code=" + code + ", listaVeiculos=" + listaVeiculos + "]";
    }
}
