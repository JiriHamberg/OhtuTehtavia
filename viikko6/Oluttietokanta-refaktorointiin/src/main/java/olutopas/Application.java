package olutopas;

import com.avaje.ebean.EbeanServer;
import javax.persistence.OptimisticLockException;
import olutopas.dao.Datamapper;
import olutopas.dao.EbeanSqliteDatamapper;
import olutopas.domain.Beer;
import olutopas.domain.Brewery;
import olutopas.domain.User;
import olutopas.services.commands.Command;
import olutopas.services.commands.CommandFactory;
import olutopas.services.io.IO;
import olutopas.services.commands.Login;
import olutopas.services.commands.Quit;
import olutopas.services.io.ScannerIO;

public class Application {

    //applicationin ei tarvitsisi tuntea serveriä, mutta seedauksen kannalta se on helpointa
    private EbeanServer server;
    
    private Datamapper mapper;
    private IO io = new ScannerIO();
    private CommandFactory commands = new CommandFactory();
    
    public Application(Datamapper mapper) {
        this.mapper = mapper;
        //tämä on rumaa
        this.server = ((EbeanSqliteDatamapper) mapper).getServer();
    }

    public void run(boolean newDatabase) {
        if (newDatabase) {
            seedDatabase();
        }

        new Login().execute(io, mapper);

        while (true) {
            menu();
            io.print("> ");
            String command = io.nextLine();
            Command c = commands.getCommand(command);
            c.execute(io, mapper);
            if(c instanceof Quit){
                return;
            }

            io.printLine("\npress enter to continue");
            io.nextLine();
        }
    }

    private void menu() {
        io.printLine("");
        io.printLine("1   find brewery");
        io.printLine("2   find/rate beer");
        io.printLine("3   add beer");
        io.printLine("4   list breweries");
        io.printLine("5   list beers");
        io.printLine("6   add brewery");       
        io.printLine("7   show my ratings");
        io.printLine("0   list users");
        io.printLine("q   quit");
        io.printLine("");
    }

    private void seedDatabase() throws OptimisticLockException {
        Brewery brewery = new Brewery("Schlenkerla");
        brewery.addBeer(new Beer("Urbock"));
        brewery.addBeer(new Beer("Lager"));
        // tallettaa myös luodut oluet, sillä Brewery:n OneToMany-mappingiin on määritelty
        // CascadeType.all
        server.save(brewery);

        // luodaan olut ilman panimon asettamista
        Beer b = new Beer("Märzen");
        server.save(b);

        // jotta saamme panimon asetettua, tulee olot lukea uudelleen kannasta
        b = server.find(Beer.class, b.getId());
        brewery = server.find(Brewery.class, brewery.getId());
        brewery.addBeer(b);
        server.save(brewery);

        server.save(new Brewery("Paulaner"));

        server.save(new User("mluukkai"));
    }

}
