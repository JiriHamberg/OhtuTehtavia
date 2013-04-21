package ohtu;

import com.google.gson.Gson;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.io.IOUtils;
 
import java.util.Formatter;

public class Main {
 
    public static void main(String[] args) throws IOException {
        String studentNr = "13881339";
        if ( args.length>0) {
            studentNr = args[0];
        }
 
        String url = "http://ohtustats-2013.herokuapp.com/opiskelija/"+studentNr+".json";
 
        HttpClient client = new HttpClient();
        GetMethod method = new GetMethod(url);
        client.executeMethod(method);
 
        InputStream stream =  method.getResponseBodyAsStream();
 
        String bodyText = IOUtils.toString(stream);
 
        //System.out.println("json-muotoinen data:");
        //System.out.println( bodyText );
 
        Gson mapper = new Gson();
        List<Palautus> palautukset = mapper.fromJson(bodyText, Palautukset.class).getPalautukset();

        if(palautukset.isEmpty()){
            System.out.println("Ei palautuksia annetulla opiskelijanumerolla");
            return;
        }
        
        System.out.println("opiskelijanumero " + palautukset.iterator().next().getOpiskelijanumero() +"\n");
        Formatter f = new Formatter();
        
        int tehtavia = 0, tunteja = 0;
        for(Palautus p : palautukset){
            tehtavia += p.getTehtavia();
            tunteja += p.getTunteja();
            
            f.format("%s %2s %3s %s %-35s %s %2s %s", 
                    "viikko", p.getViikko(), p.getTehtavia(), "tehtavaa",
                    p.getTehtavat(), "aikaa kului", p.getTunteja(), "tuntia\n");
        }
        f.format("%s %3s %s %3s %s", "yhteensä", tehtavia, "tehtavaa", tunteja, "tuntia");
        System.out.println(f);
        
     //   System.out.println("oliot:");
     //   for (Palautus palautus : palautukset.getPalautukset()) {
     //       System.out.println( palautus );
     //   }
 
    }
}
