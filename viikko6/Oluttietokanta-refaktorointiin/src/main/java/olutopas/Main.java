package olutopas;

import olutopas.domain.Beer;
import olutopas.domain.Brewery;
import olutopas.dao.Datamapper;
import olutopas.dao.EbeanSqliteDatamapper;
import olutopas.domain.Rating;
import olutopas.domain.User;

public class Main {

    public static void main(String[] args) {
        boolean dropAndCreateTables = true;
        Class [] luokat = {Beer.class, Brewery.class, Rating.class, User.class};    
        Datamapper mapper = new EbeanSqliteDatamapper("jdbc:sqlite:beer.db", true, luokat);
        new Application(mapper).run(dropAndCreateTables);
    }

}
