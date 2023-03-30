import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Main {
    public static void main(String[] args) {        
        String pattern = "MM-dd-yyyy";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        Date date;
        try {
            date = simpleDateFormat.parse("12-01-2018");
            ClientePF c = new ClientePF("lucas", "gaspar f", date, "escola", "genero", "baicxal", null, "074.581.234-19", date);
            System.out.println(c);
            System.out.println(c.validarCPF(c.getCPF()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
