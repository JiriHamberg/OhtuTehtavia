/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package statistics.matcher;

import statistics.Player;

/**
 *
 * @author jiri
 */
public class HasFewerThan implements Matcher{

    Matcher matcher;
    
    public HasFewerThan(int value, String category){
        matcher = new Not(new HasAtLeast(value, category));
    }
    
    @Override
    public boolean matches(Player p) {
        return matcher.matches(p);
    }
    
}
