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
public class AddBeer implements Command {

    @Override
    public void execute(IO io, Datamapper m) {
        io.printLine("to which brewery: ");
        String name = io.nextLine();
        Brewery brewery = m.findWithName(Brewery.class, name);

        if (brewery == null) {
            io.printLine(name + " does not exist");
            return;
        }

        io.printLine("beer to add: ");

        name = io.nextLine();

        Beer exists = m.findWithName(Beer.class, name);
        if (exists != null) {
            io.printLine(name + " exists already");
            return;
        }

        brewery.addBeer(new Beer(name));
        m.saveObject(brewery);
        io.printLine(name + " added to " + brewery.getName());
    }
}
