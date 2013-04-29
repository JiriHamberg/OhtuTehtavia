/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package olutopas.services.io;

import java.util.Scanner;

/**
 *
 * @author jiri
 */
public class ScannerIO implements IO {

    private Scanner s = new Scanner(System.in);

    @Override
    public void printLine(String s) {
        System.out.println(s);
    }

    @Override
    public void print(String s) {
        System.out.println(s);
    }

    @Override
    public String nextLine() {
        return s.nextLine();
    }
}
