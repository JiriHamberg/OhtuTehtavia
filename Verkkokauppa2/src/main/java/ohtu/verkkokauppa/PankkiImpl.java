package ohtu.verkkokauppa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class PankkiImpl implements Pankki {

    //private ApplicationContext ctx = new FileSystemXmlApplicationContext("src/main/resources/spring-context.xml");
    
    public  Kirjanpito kirjanpito;

    @Autowired
    public PankkiImpl(Kirjanpito kp) {
        kirjanpito = kp;
    }

    @Override
    public boolean tilisiirto(String nimi, int viitenumero, String tililta, String tilille, int summa) {
        kirjanpito.lisaaTapahtuma("tilisiirto: tililtä " + tilille + " tilille " + tilille
                + " viite " + viitenumero + " summa " + summa + "e");

        // täällä olisi koodi joka ottaa yhteyden pankin verkkorajapintaan
        return true;
    }
}
