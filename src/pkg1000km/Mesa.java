/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pkg1000km;

import java.util.LinkedList;

public class Mesa extends Jugador {
    // Atributos de la clase Mesa
    private LinkedList<Carta> jugador1; // Una lista enlazada que almacena las cartas jugadas por el Jugador 1 en la mesa.
    private LinkedList<Carta> jugador2; // Una lista enlazada que almacena las cartas jugadas por el Jugador 2 en la mesa.

    // Constructor de la clase Mesa
    public Mesa() {
        jugador1 = new LinkedList<>(); // Inicializa la lista de cartas jugadas por el Jugador 1 en la mesa.
        jugador2 = new LinkedList<>(); // Inicializa la lista de cartas jugadas por el Jugador 2 en la mesa.
    }

    // Métodos de la clase Mesa
    public LinkedList<Carta> getJugador1() {
        return jugador1; // Devuelve la lista de cartas jugadas por el Jugador 1 en la mesa.
    }

    public void setJugador1(LinkedList<Carta> jugador1) {
        this.jugador1 = jugador1; // Establece la lista de cartas jugadas por el Jugador 1 en la mesa.
    }

    public LinkedList<Carta> getJugador2() {
        return jugador2; // Devuelve la lista de cartas jugadas por el Jugador 2 en la mesa.
    }

    public void setJugador2(LinkedList<Carta> jugador2) {
        this.jugador2 = jugador2; // Establece la lista de cartas jugadas por el Jugador 2 en la mesa.
    }
}
