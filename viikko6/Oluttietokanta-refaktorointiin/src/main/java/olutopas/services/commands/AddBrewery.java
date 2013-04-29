/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package olutopas.services.commands;

import olutopas.dao.Datamapper;
import olutopas.domain.Brewery;
import olutopas.services.io.IO;

/**
 *
 * @author jiri
 */
public class AddBrewery implements Command{

    @Override
    public void execute(IO io, Datamapper m) {
        io.printLine("brewery to add: ");
        String name = io.nextLine();
        Brewery brewery = m.findWithName(Brewery.class, name);

        if (brewery != null) {
            io.printLine(name + " already exists!");
            return;
        }

        m.saveObject(new Brewery(name));
    }
    
}
