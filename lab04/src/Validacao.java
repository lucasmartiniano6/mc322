public class Validacao {
    public static boolean validaNome(String nome_){
        String nome = nome_.replaceAll("[^A-Z, ^a-z, ^\b]", "");
        if(nome == nome_) return true;
        else return false;
    }

    public static boolean validarCPF(String cpf) {
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


    public static boolean validarCNPJ(String cnpj){
        cnpj = cnpj.replaceAll("[^0-9]", "");
        
        if(cnpj.length() != 14) return false;

        boolean differ = false;
        for(int i=0;i<cnpj.length()-1;i++){
            if( cnpj.charAt(i) != cnpj.charAt(i+1) ) differ = true;
        }
        if(!differ) return false;

        int soma = 0;
        int mult[] = {5,4,3,2,9,8,7,6,5,4,3,2};
        for(int i=0;i<mult.length;i++){
            soma += ((int)cnpj.charAt(i)-48) * mult[i];
        }

        int primeiro;
        if(soma % 11 < 2) primeiro = 0;
        else primeiro = 11 - (soma%11);

        soma = 0;
        mult = new int[]{6,5,4,3,2,9,8,7,6,5,4,3,2};
        for(int i=0; i<mult.length; i++){
            soma += ((int)cnpj.charAt(i)-48) * mult[i];
        }

        int segundo;
        if(soma % 11 < 2) segundo = 0;
        else segundo = 11- (soma % 11);

        if((int)cnpj.charAt(12)-48 == primeiro && (int)cnpj.charAt(13)-48== segundo) return true;
        else return false;
    }
}
