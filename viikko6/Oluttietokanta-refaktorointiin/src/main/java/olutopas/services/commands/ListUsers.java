/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package olutopas.services.commands;

import olutopas.services.commands.Command;
import java.util.List;
import olutopas.dao.Datamapper;
import olutopas.domain.User;
import olutopas.services.io.IO;

/**
 *
 * @author jiri
 */
public class ListUsers implements Command {

    @Override
    public void execute(IO io, Datamapper m) {
       List<User> users = m.findAll(User.class);
        for (User user : users) {
            System.out.println(user.getName() + " " + user.getRatings().size() + " ratings");
        }
    }
    
}
