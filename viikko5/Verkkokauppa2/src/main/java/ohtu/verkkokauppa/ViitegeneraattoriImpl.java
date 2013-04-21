package ohtu.verkkokauppa;

import org.springframework.stereotype.Component;

@Component
public class ViitegeneraattoriImpl implements Viitegeneraattori {
    
    public ViitegeneraattoriImpl(){
        seuraava = 1;    
    }
    
    private int seuraava;
    
    @Override
    public int uusi(){
        return seuraava++;
    }
}
