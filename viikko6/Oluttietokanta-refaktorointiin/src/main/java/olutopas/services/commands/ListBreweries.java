/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package olutopas.services.commands;

import olutopas.services.commands.Command;
import java.util.List;
import olutopas.dao.Datamapper;
import olutopas.domain.Brewery;
import olutopas.services.io.IO;

/**
 *
 * @author jiri
 */
public class ListBreweries implements Command{

    @Override
    public void execute(IO io, Datamapper m) {
        List<Brewery> breweries = m.findAll(Brewery.class);
        for (Brewery brewery : breweries) {
            io.printLine(brewery.toString());
        }
    }
    
}
