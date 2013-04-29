/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package olutopas.services.commands;

import olutopas.dao.Datamapper;
import olutopas.services.io.IO;

/**
 *
 * @author jiri
 */
public class CommandFactory {
    public Command getCommand(String command){
        if (command.equals("q")) {
               return new Quit();
            } else if (command.equals("1")) {
               return new FindBrewery();
            } else if (command.equals("2")) {
                return new FindBeer();
            } else if (command.equals("3")) {
                return new AddBeer();
            } else if (command.equals("4")) {
                return new ListBreweries();
            } else if (command.equals("5")) {
                return new ListBeers();
            } else if (command.equals("6")) {
                return new AddBrewery();
            } else if (command.equals("7")) {
                return new MyRatings();
            } else if (command.equals("0")) {
                return new ListUsers();
            } else {
                return new Unknown();
            }
    }
}
