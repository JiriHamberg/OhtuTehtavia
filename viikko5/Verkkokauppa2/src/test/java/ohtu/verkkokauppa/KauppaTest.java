/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ohtu.verkkokauppa;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import org.mockito.stubbing.Answer;
/**
 *
 * @author jiri
 */
public class KauppaTest {
    
    public KauppaTest() {
    }

    @Test
    public void kunTehdaanOstosNiinKutsutaanTilisiirtoaOikeillaTilinumerollaNimillaJaHinnalla(){
        Pankki pankki = mock(Pankki.class);      
        Viitegeneraattori viite = mock(Viitegeneraattori.class);     
        Varasto varasto = mock(Varasto.class);
        
        when(varasto.saldo(20)).thenReturn(1);
        when(varasto.haeTuote(20)).thenReturn(new Tuote(20, "Keppana", 18));
        when(viite.uusi()).thenReturn(1);        
        
        Kauppa k = new Kauppa(varasto, pankki, viite);
        k.aloitaAsiointi();
        k.lisaaKoriin(20);
        k.tilimaksu("Jiri", "112233-44");
        verify(pankki).tilisiirto(eq("Jiri"), eq(1), eq("112233-44"), any(String.class), eq(18));        
    }
    
    @Test
    public void kunTehdaanOstosKahdellaTuotteellaNiinKutsutaanTilisiirtoaOikeillaTilinumerollaNimillaJaHinnalla(){
        Pankki pankki = mock(Pankki.class);      
        Viitegeneraattori viite = mock(Viitegeneraattori.class);     
        Varasto varasto = mock(Varasto.class);
        
        when(varasto.saldo(20)).thenReturn(1);
        when(varasto.haeTuote(20)).thenReturn(new Tuote(20, "Keppana", 18));
        when(varasto.saldo(21)).thenReturn(1);
        when(varasto.haeTuote(21)).thenReturn(new Tuote(21, "Saippua", 3));
        when(viite.uusi()).thenReturn(1);       
        
        Kauppa k = new Kauppa(varasto, pankki, viite);
        k.aloitaAsiointi();
        k.lisaaKoriin(20);
        k.lisaaKoriin(21);
        k.tilimaksu("Jiri", "112233-44");
        verify(pankki).tilisiirto(eq("Jiri"), eq(1), eq("112233-44"), any(String.class), eq(18+3));        
    }
    
    @Test
    public void kunTehdaanOstosKahdellaSamallaTuotteellaNiinKutsutaanTilisiirtoaOikeillaTilinumerollaNimillaJaHinnalla(){
        Pankki pankki = mock(Pankki.class);      
        Viitegeneraattori viite = mock(Viitegeneraattori.class);     
        Varasto varasto = mock(Varasto.class);
        
        when(varasto.saldo(20)).thenReturn(2).thenReturn(1).thenReturn(0);
        when(varasto.haeTuote(20)).thenReturn(new Tuote(20, "Keppana", 18));
        when(viite.uusi()).thenReturn(1);       
        
        Kauppa k = new Kauppa(varasto, pankki, viite);
        k.aloitaAsiointi();
        k.lisaaKoriin(20);
        k.lisaaKoriin(20);
        k.tilimaksu("Jiri", "112233-44");
        verify(pankki).tilisiirto(eq("Jiri"), eq(1), eq("112233-44"), any(String.class), eq(2*18));        
    }
    
    @Test
    public void kunTehdaanOstosJaTuoteLoppuNiinKutsutaanTilisiirtoaOikeillaTilinumerollaNimillaJaHinnalla(){
        Pankki pankki = mock(Pankki.class);      
        Viitegeneraattori viite = mock(Viitegeneraattori.class);     
        Varasto varasto = mock(Varasto.class);
        
        when(varasto.saldo(20)).thenReturn(0);
        when(varasto.haeTuote(20)).thenReturn(new Tuote(20, "Keppana", 18));
        when(varasto.saldo(21)).thenReturn(1);
        when(varasto.haeTuote(21)).thenReturn(new Tuote(21, "Saippua", 3));
        when(viite.uusi()).thenReturn(1);       
        
        Kauppa k = new Kauppa(varasto, pankki, viite);
        k.aloitaAsiointi();
        k.lisaaKoriin(20);
        k.lisaaKoriin(21);
        k.tilimaksu("Jiri", "112233-44");
        verify(pankki).tilisiirto(eq("Jiri"), eq(1), eq("112233-44"), any(String.class), eq(3));        
    }
    
    @Test
    public void aloitaAsiointiNollaaEdellisenOstoksenTiedot(){
        Pankki pankki = mock(Pankki.class);      
        Viitegeneraattori viite = mock(Viitegeneraattori.class);     
        Varasto varasto = mock(Varasto.class);
        
        when(varasto.saldo(20)).thenReturn(1);
        when(varasto.haeTuote(20)).thenReturn(new Tuote(20, "Keppana", 18));
        when(varasto.saldo(21)).thenReturn(1);
        when(varasto.haeTuote(21)).thenReturn(new Tuote(21, "Saippua", 3));
        when(viite.uusi()).thenReturn(1);      
        
        Kauppa k = new Kauppa(varasto, pankki, viite);
        k.aloitaAsiointi();
        k.lisaaKoriin(20);
        k.aloitaAsiointi();
        k.lisaaKoriin(21);
        k.tilimaksu("Jiri", "112233-44");
        verify(pankki).tilisiirto(eq("Jiri"), eq(1), eq("112233-44"), any(String.class), eq(3));
        
    }
    
    @Test
    public void kauppaPyytaaUudenViitteenJokaiselleOstokselle(){
        Pankki pankki = mock(Pankki.class);      
        Viitegeneraattori viite = mock(Viitegeneraattori.class);     
        Varasto varasto = mock(Varasto.class);
        
        when(varasto.saldo(20)).thenReturn(1);
        when(varasto.haeTuote(20)).thenReturn(new Tuote(20, "Keppana", 18));
        when(varasto.saldo(21)).thenReturn(1);
        when(varasto.haeTuote(21)).thenReturn(new Tuote(21, "Saippua", 3));
        when(viite.uusi()).thenReturn(1).thenReturn(2);      
        
        Kauppa k = new Kauppa(varasto, pankki, viite);
        k.aloitaAsiointi();
        k.lisaaKoriin(20);
        k.tilimaksu("Jiri", "112233-44");
        verify(pankki).tilisiirto(any(String.class), eq(1), any(String.class), any(String.class), any(Integer.class));
        k.aloitaAsiointi();
        k.lisaaKoriin(21);
        k.tilimaksu("Jiri", "112233-44");
        verify(pankki).tilisiirto(any(String.class), eq(2), any(String.class), any(String.class), any(Integer.class));
        
    }
    
    
    
}