
package ohtu.verkkokauppa;

import java.util.ArrayList;
import org.springframework.stereotype.Component;

@Component
public class KirjanpitoImpl implements Kirjanpito {

    
    public KirjanpitoImpl() {
        tapahtumat = new ArrayList<String>();
    }

    private ArrayList<String> tapahtumat;


    @Override
    public void lisaaTapahtuma(String tapahtuma) {
        tapahtumat.add(tapahtuma);
    }

    @Override
    public ArrayList<String> getTapahtumat() {
        return tapahtumat;
    }
}
