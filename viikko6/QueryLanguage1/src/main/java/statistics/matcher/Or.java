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
public class Or implements Matcher{
    Matcher[] matchers;
    public Or(Matcher ... matchers){
        this.matchers=matchers;
    }

    @Override
    public boolean matches(Player p) {
        for(Matcher m : matchers){
            if(m.matches(p)){
                return true;
            }
        }
        return false;
    }
        
}
