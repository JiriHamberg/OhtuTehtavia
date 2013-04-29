/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package olutopas.services.commands;

import olutopas.dao.Datamapper;
import olutopas.services.io.IO;

/**
 *
 * @author jiri
 */
public class Quit implements Command {

    @Override
    public void execute(IO io, Datamapper m) {        
        io.printLine("bye");
    }
    
}
