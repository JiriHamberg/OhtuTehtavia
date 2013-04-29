/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package olutopas.services.commands;

import olutopas.dao.Datamapper;
import olutopas.domain.Beer;
import olutopas.domain.Brewery;
import olutopas.services.io.IO;

/**
 *
 * @author jiri
 */
public class FindBrewery implements Command {

    @Override
    public void execute(IO io, Datamapper datamapper) {
        io.printLine("brewery to find: ");
        String n = io.nextLine();
        Brewery foundBrewery = datamapper.findWithName(Brewery.class, n);

        if (foundBrewery == null) {
            System.out.println(n + " not found");
            return;
        }

        io.printLine(foundBrewery.toString());
        for (Beer bier : foundBrewery.getBeers()) {
            io.printLine("   " + bier.getName());
        }
    }
    
}
