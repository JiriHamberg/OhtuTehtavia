/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package olutopas.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 *
 * @author jiri
 */
@Entity
public class Rating {
    @Id
    private Integer id;

    @ManyToOne
    private Beer beer;

    @ManyToOne
    private User user;
 
    private int value;

    public Rating() {
    }

    public Rating(Beer beer, User user, int value){
        this.beer=beer;
        this.user=user;
        this.value=value;
    }
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Beer getBeer() {
        return beer;
    }

    public void setBeer(Beer beer) {
        this.beer = beer;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
    
    @Override
    public String toString(){
        return beer + " " + value + " points";
    }
    
    
}
