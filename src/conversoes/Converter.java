package conversoes;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Converter {
	
	public static SimpleDateFormat FORMATADORDATA = 
			new SimpleDateFormat("yyyy-MM-dd");
	
	public static Date formataEstaData(String data) throws Exception {   
        if (data == null || data.equals(""))  
            return null;  
          
        Date date = null;  
        try {  
        	SimpleDateFormat FORMATADORDATA = new SimpleDateFormat("dd/MM/yyyy");
            date = FORMATADORDATA.parse(data);  
        } catch (ParseException e) {              
            throw e;  
        }  
        return date;  
    }  

	public static Integer StringPARAint(String s) {
		return Integer.parseInt(s);
	}

 
	public static Double StringPARAdouble(String s) {
		return Double.parseDouble(s);

	}
	
	
	
	
}
