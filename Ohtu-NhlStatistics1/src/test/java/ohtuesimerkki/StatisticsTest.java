/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ohtuesimerkki;

import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author jiri
 */
public class StatisticsTest {
    
    Statistics stats;    
    
    
    Reader readerStub = new Reader() {
 
        @Override
        public List<Player> getPlayers() {
            ArrayList<Player> players = new ArrayList<Player>();
 
            players.add(new Player("Semenko", "EDM", 4, 12));
            players.add(new Player("Lemieux", "PIT", 45, 54));
            players.add(new Player("Kurri",   "EDM", 37, 53));
            players.add(new Player("Yzerman", "DET", 42, 56));
            players.add(new Player("Gretzky", "EDM", 35, 89));
 
            return players;
        }
    };
    
    
    
    public StatisticsTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        stats = new Statistics(readerStub);
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of search method, of class Statistics.
     */
    @Test
    public void testSearch() {
        Player p = stats.search("Kurri");
        assertEquals(p.getName(), "Kurri");
        assertEquals(p.getTeam(), "EDM");        
        
        assertEquals(p.getGoals(), 37);
        assertEquals(p.getAssists(), 53);
        
        
        Player k = stats.search("sfagaq");
        assertNull(k);                
    }

    /**
     * Test of team method, of class Statistics.
     */
    @Test
    public void testTeam() {
        List<Player> t1 =  stats.team("EDM");
        assertEquals(t1.size(), 3);
        for(Player p : t1){
            assertEquals(p.getTeam(), "EDM");
        }
    }

    /**
     * Test of topScorers method, of class Statistics.
     */
    @Test
    public void testTopScorers() {
        List<Player> players = stats.topScorers(3); //return n+1 top players, maybe unintended?
        assertEquals(4, players.size());            
        assertEquals(players.get(0).getName(), "Gretzky");
        assertEquals(players.get(1).getName(), "Lemieux");
        assertEquals(players.get(2).getName(), "Yzerman");
    }
}