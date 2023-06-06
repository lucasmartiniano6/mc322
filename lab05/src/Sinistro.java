import java.util.Date;
import java.util.Random;

public class Sinistro {
    private final int ID;
    private Date data;
    private String endereco;
    private Condutor condutor;
    private Seguro seguro;

    public int gerarIdUnico(){
        Random random = new Random();
        return random.nextInt(10000000);
    }

    public Sinistro(Date data, String endereco, Condutor condutor, Seguro seguro) {
        this.ID = gerarIdUnico();
        this.data = data;
        this.endereco = endereco;
        this.condutor = condutor;
        this.seguro = seguro;
    }

    public int getID() {
        return ID;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public Condutor getCondutor() {
        return condutor;
    }

    public void setCondutor(Condutor condutor) {
        this.condutor = condutor;
    }

    public Seguro getSeguro() {
        return seguro;
    }

    public void setSeguro(Seguro seguro) {
        this.seguro = seguro;
    }

    @Override
    public String toString() {
        return "Sinistro [ID=" + ID + ", data=" + data + ", endereco=" + endereco + ", condutor=" + condutor
                + ", seguro=" + seguro + "]";
    }


}