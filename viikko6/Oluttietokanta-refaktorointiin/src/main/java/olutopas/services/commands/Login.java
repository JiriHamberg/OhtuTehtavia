/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package olutopas.services.commands;

import olutopas.services.commands.Command;
import olutopas.dao.Datamapper;
import olutopas.domain.User;
import olutopas.services.io.IO;

/**
 *
 * @author jiri
 */
public class Login implements Command {

    @Override
    public void execute(IO io, Datamapper m) {
        while (true) {
            io.printLine("\nLogin (give ? to register a new user)\n");

            io.printLine("username: ");
            String name = io.nextLine();

            if (name.equals("?")) {
                register(io, m);
                continue;
            }

            User user = m.findWithName(User.class, name);

            if (user != null) {
                m.setUser(user);
                io.printLine("\nWelcome to Ratebeer " + m.getUser().getName());
                return;
            }
            io.printLine("unknown user");
        }
    }
    
    private void register(IO io, Datamapper m){
        io.printLine("Register a new user");
        io.printLine("give username: ");
        String name = io.nextLine();
        User u = m.findWithName(User.class, name);
        if (u != null) {
            io.printLine("user already exists!");
            return;
        }
        m.saveObject(new User(name));
        io.printLine("user created!\n");
    }
    
}
