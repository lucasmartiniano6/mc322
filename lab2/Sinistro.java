import java.util.Random;

public class Sinistro {
    private int id;
    private String data;
    private String endereco;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getData() {
        return data;
    }
    public void setData(String data) {
        this.data = data;
    }
    public String getEndereco() {
        return endereco;
    }
    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }
    
    public int gerarIdUnico(){
        Random random = new Random();
        return random.nextInt(10000000);
    }

    public Sinistro(String data, String endereco) {
        this.id = gerarIdUnico();
        this.data = data;
        this.endereco = endereco;
    }

    public String toString(){
        return "Id: " + getId() + "\nData: " + getData()
                + "\nEndereco: " + getEndereco() + "\n";
    }
}
