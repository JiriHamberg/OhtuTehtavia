/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package statistics.matcher;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jiri
 */
public class QueryBuilder {
    
    List<Matcher> conditionals = new ArrayList<Matcher>();
    
    public QueryBuilder playsIn(String s){
        conditionals.add(new PlaysIn(s));
        return this;
    }
    
    public QueryBuilder hasAtLeast(int value, String field){
        conditionals.add(new HasAtLeast(value, field));
        return this;
    }
    
    public QueryBuilder hasFewerThan(int value, String field){
        conditionals.add(new HasFewerThan(value, field));
        return this;
    }

    public QueryBuilder oneOf(Matcher ... matchers) {
        conditionals.add(new Or(matchers));
        return this;
    }
    
    public QueryBuilder not(Matcher matcher){
        conditionals.add(new Not(matcher));
        return this;
    }
    
    public Matcher build(){
        Matcher[] matchers = {};
        matchers = conditionals.toArray(matchers);
        Matcher query = new And(matchers);
        this.conditionals = new ArrayList<Matcher>();
        return query;
    }
    
    
}
