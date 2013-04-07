/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ohtu.data_access;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import ohtu.domain.User;
import org.springframework.stereotype.Component;
/**
 *
 * @author jiri
 */


public class FileUserDao implements UserDao {

    private File file;
    private Scanner s;
    private FileWriter fw;
   
    public FileUserDao(String fileName) {
        file = new File(fileName);
        try{
            fw = new FileWriter(file);
        }
        catch(Throwable t){
            System.err.println("Could not open file " + file);
        }
    }
    
    @Override
    public List<User> listAll() {
        ArrayList<User> list = new ArrayList<User>();
        try {
            s = new Scanner(file);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FileUserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        while(s.hasNextLine()){
            String name = s.nextLine();
            String pword = s.nextLine();
            list.add(new User(name, pword));
        }
        return list;
    }

    @Override
    public User findByName(String name) {
        try {
            s = new Scanner(file);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FileUserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        while(s.hasNextLine()){
            String uname = s.nextLine();
            String pword = s.nextLine();
            System.out.println("Found " + uname);
            if(uname.equals(name)){
                return new User(uname, pword);
            }
        }
        return null;
    }

    @Override
    public void add(User user) {
        try {
            fw.write(user.getUsername() + "\n" + user.getPassword() + "\n");
            fw.flush();
        } catch (IOException ex) {
            System.err.println("Could not write to file " + file);
        }
        
    }
    
}
