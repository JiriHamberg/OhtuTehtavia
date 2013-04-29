/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package olutopas.services.commands;

import olutopas.services.commands.Command;
import java.util.List;
import olutopas.dao.Datamapper;
import olutopas.domain.Beer;
import olutopas.services.io.IO;

/**
 *
 * @author jiri
 */
public class ListBeers implements Command {

    @Override
    public void execute(IO io, Datamapper m) {
        List<Beer> beers = m.findAllOrdered(Beer.class, "brewery.name");
        for (Beer beer : beers) {
            io.printLine(beer.toString());
            if (beer.getRatings() != null && !beer.getRatings().isEmpty()) {
                io.printLine("  ratings given "+beer.getRatings().size() + " average " + beer.averageRating());
            } else {
                io.printLine("  no ratings");
            }
        }
    }
    
}
