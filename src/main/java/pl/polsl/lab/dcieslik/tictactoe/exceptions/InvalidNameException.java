/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pl.polsl.lab.dcieslik.tictactoe.exceptions;

/**
 * Class representing custom exception to throw if name consists not only of
 * letters.
 *
 * @author Dawid Cieślik
 */
public class InvalidNameException extends Exception {

    /**
     * Class constructor.
     *
     * @param str String to display.
     */
    public InvalidNameException(String str) {
        super(str);
    }
}
