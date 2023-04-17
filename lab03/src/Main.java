import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Main {
    public static void main(String[] args) {        
        String pattern = "dd/MM/yyyy";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        try {
            Date date = simpleDateFormat.parse("17/06/2018");
            ClientePJ c = new ClientePJ(pattern, pattern, null, "54.623.001/0001-11", date);
            System.out.println(c);
            System.out.println(c.validarCNPJ(c.getCNPJ()));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Sinistro s = new Sinistro(pattern, pattern, null, null, null);
        System.out.println(s);
    }
}
