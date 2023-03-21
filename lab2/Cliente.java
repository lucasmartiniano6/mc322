public class Cliente {
    private String nome;
    private String cpf;
    private String dataNascimento;
    private int idade;
    private String endereco;

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getCpf() {
        return cpf;
    }
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
    public String getDataNascimento() {
        return dataNascimento;
    }
    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }
    public int getIdade() {
        return idade;
    }
    public void setIdade(int idade) {
        this.idade = idade;
    }
    public String getEndereco() {
        return endereco;
    }
    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }
    
    public Cliente(String nome, String cpf, String dataNascimento, int idade, String endereco) {
        this.nome = nome;
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
        this.idade = idade;
        this.endereco = endereco;
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

    public String toString(){
        return "Nome: " + getNome() + "\nCpf: " + getCpf()
                + "\nNascimento: " + getDataNascimento()
                + "\nIdade: " + getIdade()
                + "\nEndereco: " + getEndereco();
    }

}
