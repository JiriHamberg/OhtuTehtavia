/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package olutopas.services.commands;

import olutopas.services.commands.Command;
import olutopas.dao.Datamapper;
import olutopas.domain.Rating;
import olutopas.services.io.IO;

/**
 *
 * @author jiri
 */
public class MyRatings implements Command{

    @Override
    public void execute(IO io, Datamapper m) {
        io.printLine("Ratings by " + m.getUser().getName());
        for (Rating rating : m.getUser().getRatings()) {
            System.out.println(rating);
        }
    }
    
}
