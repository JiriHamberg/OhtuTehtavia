/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package olutopas.services.commands;

import olutopas.dao.Datamapper;
import olutopas.domain.Beer;
import olutopas.domain.Rating;
import olutopas.services.io.IO;

/**
 *
 * @author jiri
 */
public class FindBeer implements Command {

    @Override
    public void execute(IO io, Datamapper m){
        io.printLine("beer to find: ");
        String n = io.nextLine();
        Beer foundBeer = m.findWithName(Beer.class, n);

        if (foundBeer == null) {
            io.printLine(n + " not found");
            return;
        }

        io.printLine(foundBeer.toString());

        if (foundBeer.getRatings() != null && !foundBeer.getRatings().isEmpty()) {
            io.printLine("  number of ratings: "+ foundBeer.getRatings().size() + " average " + foundBeer.averageRating());
        } else {
            io.printLine("no ratings");
        }

        io.printLine("give rating (leave emtpy if not): ");
        addRating(io, m, foundBeer);
    }
    
    private void addRating(IO io, Datamapper mapper, Beer foundBeer){
        try {
            int value = Integer.parseInt(io.nextLine());
            Rating rating = new Rating(foundBeer, mapper.getUser(), value);
            mapper.saveObject(rating);
        } catch (Exception e) {
        }
    }
    
    
}
