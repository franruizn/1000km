/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pkg1000km;

import java.util.LinkedList;

public class Jugador {
    // Atributos de la clase Jugador
    protected LinkedList<Carta> mano; // Una lista enlazada que almacena las cartas en la mano del jugador.
    protected int km; // Un entero que representa la distancia recorrida por el jugador en kilómetros.
    protected boolean jugar; // Un booleano que indica si el jugador puede jugar una carta en su turno.

    // Constructor de la clase Jugador
    public Jugador() {
        km = 0; // Inicializa la distancia recorrida a 0.
        mano = new LinkedList<>(); // Inicializa la mano del jugador como una lista enlazada vacía.
        jugar = false; // Inicializa la capacidad de jugar como false (no puede jugar una carta en el primer turno).
    }

    // Métodos de la clase Jugador
    public LinkedList<Carta> getMano() {
        return mano; // Devuelve la lista de cartas en la mano del jugador.
    }

    public void setMano(LinkedList<Carta> mano) {
        this.mano = mano; // Establece la lista de cartas en la mano del jugador.
    }

    public int getKm() {
        return km; // Devuelve la distancia recorrida por el jugador en kilómetros.
    }

    public void setKm(int km) {
        this.km = km; // Establece la distancia recorrida por el jugador en kilómetros.
    }

    public boolean isJugar() {
        return jugar; // Devuelve si el jugador puede jugar una carta en su turno.
    }

    public void setJugar(boolean jugar) {
        this.jugar = jugar; // Establece si el jugador puede jugar una carta en su turno.
    }

    @Override
    public String toString() {
        // Un método que genera una representación en cadena del jugador y su mano de cartas.
        // Enumera las cartas en la mano del jugador y muestra la distancia recorrida en kilómetros.
        String resultado = "";
        for (int i = 0; i < mano.size(); i++) {
            if (i == 0) {
                resultado += "[0." + mano.get(i) + ", ";
            } else if (i == mano.size() - 1) {
                resultado += i + "." + mano.get(i) + "]";
            } else {
                resultado += i + "." + mano.get(i) + ", ";
            }
        }
        return resultado + "\nKilómetros: " + km;
    }
}
