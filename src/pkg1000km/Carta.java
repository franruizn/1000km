/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pkg1000km;

import java.util.ArrayList;
//Hola
public class Carta implements Comparable {
    // Atributos de la clase Carta
    private String tipoCarta; // Una cadena que almacena el tipo de carta (por ejemplo, "SEMAFORO VERDE").
    private Integer valor; // Un entero que almacena el valor de la carta (por ejemplo, la cantidad de kilómetros o 0 para cartas especiales).
    private boolean defensa; // Un booleano que indica si la carta es una carta de defensa.

    // Constructores de la clase Carta
    public Carta() {
        // Constructor vacío, no inicializa los atributos.
    }

    public Carta(String tipoCarta, Integer valor, boolean defensa) {
        this.tipoCarta = tipoCarta; // Inicializa el tipo de carta.
        this.valor = valor; // Inicializa el valor de la carta.
        this.defensa = defensa; // Inicializa si la carta es de defensa.
    }

    // Métodos de la clase Carta
    public String getTipoCarta() {
        return tipoCarta; // Devuelve el tipo de la carta.
    }

    public void setTipoCarta(String tipoCarta) {
        this.tipoCarta = tipoCarta; // Establece el tipo de la carta.
    }

    public Integer getValor() {
        return valor; // Devuelve el valor de la carta.
    }

    public void setValor(Integer valor) {
        this.valor = valor; // Establece el valor de la carta.
    }

    public boolean isDefensa() {
        return defensa; // Devuelve si la carta es de defensa.
    }

    public void setDefensa(boolean defensa) {
        this.defensa = defensa; // Establece si la carta es de defensa.
    }

    @Override
    public int compareTo(Object o) {
        // Implementa el método de comparación necesario para ordenar las cartas.
        // Compara las cartas basándose en su valor.
        return valor.compareTo(((Carta) o).valor);
    }

    @Override
    public String toString() {
        return tipoCarta; // Devuelve una representación en cadena de la carta (solo el tipo de carta).
    }
}
