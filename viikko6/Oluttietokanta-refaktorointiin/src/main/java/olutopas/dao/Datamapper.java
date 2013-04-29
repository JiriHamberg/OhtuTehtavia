/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package olutopas.dao;

import java.util.List;
import olutopas.domain.Beer;
import olutopas.domain.Brewery;
import olutopas.domain.User;

/**
 *
 * @author jiri
 */
public interface Datamapper {
    public <T> T findWithName(Class<T> type, String name);
    public <T> List<T> findAll(Class<T> type);
    public <T> List<T> findAllOrdered(Class<T> type, String orderedBy);
    public void saveObject(Object o);
    
    public User getUser();
    public void setUser(User s);

}
