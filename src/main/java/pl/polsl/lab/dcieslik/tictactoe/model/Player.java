/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pl.polsl.lab.dcieslik.tictactoe.model;

import pl.polsl.lab.dcieslik.tictactoe.exceptions.InvalidNameException;


/**
 * Represents a player in a game - each player has a name.
 *
 * @author Dawid Cieślik
 */
public class Player {

    /**
     * Player name.
     */
    private String name;

    /**
     * Class constructor.
     *
     * @param name player's name.
     */
    public Player(String name) {
        this.name = name;
    }

    /**
     * Sets a player's name.
     *
     * @param name new name.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns a player's name.
     *
     * @return player's name.
     */
    public String getName() {
        return this.name;
    }
}