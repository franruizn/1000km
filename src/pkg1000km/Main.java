/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package pkg1000km;

import java.util.Collections;
import java.util.LinkedList;
import java.util.Scanner;

/**
 *
 * @author MEDAC
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // Inicialización de las estructuras de datos para el juego
        LinkedList<Carta> mazo = new LinkedList<>(); //Inicializo mazo, jugadores y mesa
        Jugador jugador1 = new Jugador();
        Jugador jugador2 = new Jugador();
        Mesa mesa = new Mesa();
        
        
        rellenarMazo(mazo); //Relleno el mazo de cartas
        crearJugadores(jugador1, jugador2, mazo); //Empleo el mazo para repartir las cartas a los jugadores
        
        // Inicia los turnos del juego
        turnos(jugador1, jugador2, mazo, mesa);
    }

    public static void rellenarMazo(LinkedList<Carta> mazo) {
        // Crea 48 cartas y las agrega al mazo, luego las baraja
        // Las cartas tienen diferentes tipos y valores
        // Las cartas de "gasolina" suman kilómetros
        // Las cartas de "semaforo verde" y "semaforo rojo" tienen efectos especiales
        // Las cartas con valor 0 se consideran especiales y afectan a otros jugadores
        // Se baraja el mazo al final
        for (int i = 0; i < 48; i++) {
            if (i < 8) {
                mazo.add(new Carta("SEMAFORO VERDE", 0, true));
            } else if (i < 16) {
                mazo.add(new Carta("SEMAFORO ROJO", 0, false));
            } else if (i < 24) {
                mazo.add(new Carta("SIN GASOLINA", 0, false));
            } else if (i < 32) {
                mazo.add(new Carta("GASOLINA", 0, false));
            } else if (i < 34) {
                mazo.add(new Carta("25 KM", 25, false));
            } else if (i < 36) {
                mazo.add(new Carta("50 KM", 50, false));
            } else if (i < 38) {
                mazo.add(new Carta("75 KM", 75, false));
            } else if (i < 44) {
                mazo.add(new Carta("100 KM", 100, false));
            } else if (i < 46) {
                mazo.add(new Carta("150 KM", 150, false));
            } else {
                mazo.add(new Carta("200 KM", 200, false));
            }
        }

        Collections.shuffle(mazo);
    }

    public static void crearJugadores(Jugador jugador1, Jugador jugador2, LinkedList<Carta> mazo) {
        // Reparte 6 cartas a cada jugador y las ordena
        for (int i = 0; i < 6; i++) {
            jugador1.mano.add(mazo.pollFirst());
            jugador2.mano.add(mazo.pollFirst());
        }
        Collections.sort(jugador1.mano);
        Collections.sort(jugador2.mano);
    }

    public static void turnos(Jugador jugador1, Jugador jugador2, LinkedList<Carta> mazo, Mesa mesa) {
        // Inicia un bucle de turnos alternos entre jugadores
        // Los jugadores roban cartas, juegan cartas y se verifica si hay un ganador
        boolean gana1 = false, gana2 = false;
        int turno = 1;
        while (!gana1 || !gana2) {
            System.out.println("- - - - - - - - TURNO " + turno + " - - - - - - - -");
            System.out.println("Turno del jugador 1:");
            System.out.println("Cartas de la mesa:");
            System.out.println(mesa.getJugador1());
            System.out.println(mesa.getJugador2());
            robarCarta(jugador1, mazo);
            jugar1(jugador1, jugador2, mesa);
            System.out.println("Turno del jugador 2:");
            System.out.println("Cartas de la mesa:");
            System.out.println(mesa.getJugador1());
            System.out.println(mesa.getJugador2());
            robarCarta(jugador2, mazo);
            jugar2(jugador2, jugador1, mesa);
            comprobarGanador(jugador1, jugador2, mazo, gana1, gana2);
            turno++;
        }

    }

    public static void robarCarta(Jugador jugador, LinkedList<Carta> mazo) {
        // El jugador roba una carta del mazo y la ordena
        jugador.mano.add(mazo.pollFirst());
        Collections.sort(jugador.mano);
    }

    public static void jugar1(Jugador jugador, Jugador jugadorEsperando, Mesa mesa) {
        // El jugador 1 elige una carta para jugar, con posibles efectos en la mesa y el jugador 2
        // Se verifica si se puede jugar la carta y se actualiza la mano y la mesa
        Scanner entrada = new Scanner(System.in);
        System.out.println("Cartas del Jugador: " + jugador);
        System.out.println("Posición de la carta que quieres jugar: ");
        int pos = entrada.nextInt();

        if (jugador.mano.get(pos).getValor() == 0) {
            if (jugador.mano.get(pos).getTipoCarta().equalsIgnoreCase("semaforo verde")) {
                if (!jugador.jugar) {
                    if (!mesa.getJugador1().isEmpty()) {
                        for (int i = 0; i < mesa.getJugador1().size(); i++) {
                            if (mesa.getJugador1().get(i).getValor() == 0) {
                                if (mesa.getJugador1().get(i).getTipoCarta().equalsIgnoreCase("semaforo rojo") || mesa.getJugador1().get(i).getTipoCarta().equalsIgnoreCase("gasolina")) {
                                    mesa.getJugador1().remove(i);
                                    mesa.getJugador1().add(jugador.mano.get(pos));
                                    jugador.setJugar(true);
                                }
                            }
                        }
                    } else {
                        mesa.getJugador1().add(jugador.mano.get(pos));
                        jugador.setJugar(true);
                    }
                }
            }

            if (jugador.mano.get(pos).getTipoCarta().equalsIgnoreCase("semaforo rojo")) {
                if (jugadorEsperando.jugar) {
                    for (int i = 0; i < mesa.getJugador2().size(); i++) {
                        if (mesa.getJugador2().get(i).getValor() == 0) {
                            mesa.getJugador2().remove(i);
                        }
                    }

                    mesa.getJugador2().add(jugador.mano.get(pos));
                    jugadorEsperando.setJugar(false);
                }
            }

            if (jugador.mano.get(pos).getTipoCarta().equalsIgnoreCase("gasolina")) {
                if (!jugador.jugar) {
                    for (int i = 0; i < mesa.getJugador1().size(); i++) {
                        if (mesa.getJugador1().get(i).getValor() == 0 && mesa.getJugador1().get(i).getTipoCarta().equalsIgnoreCase("sin gasolina")) {
                            mesa.getJugador1().remove(i);
                            mesa.getJugador1().add(jugador.mano.get(pos));
                        }
                    }
                }
            }

            if (jugador.mano.get(pos).getTipoCarta().equalsIgnoreCase("sin gasolina")) {
                if (jugadorEsperando.jugar) {
                    for (int i = 0; i < mesa.getJugador2().size(); i++) {
                        if (mesa.getJugador2().get(i).getValor() == 0) {
                            mesa.getJugador2().remove(i);
                        }
                    }

                    mesa.getJugador2().add(jugador.mano.get(pos));
                    jugadorEsperando.setJugar(false);
                }
            }
        } else {
            if (jugador.jugar) {
                jugador.setKm(jugador.getKm() + jugador.mano.get(pos).getValor());
                mesa.getJugador1().add(jugador.mano.get(pos));
            }
        }

        jugador.mano.remove(pos);

    }

    public static void jugar2(Jugador jugador, Jugador jugadorEsperando, Mesa mesa) {
        // El jugador 2 elige una carta para jugar, con posibles efectos en la mesa y el jugador 1
        // Se verifica si se puede jugar la carta y se actualiza la mano y la mesa
        Scanner entrada = new Scanner(System.in);
        System.out.println("Cartas del Jugador: " + jugador);
        System.out.println("Posición de la carta que quieres jugar: ");
        int pos = entrada.nextInt();

        if (jugador.mano.get(pos).getValor() == 0) {
            if (jugador.mano.get(pos).getTipoCarta().equalsIgnoreCase("semaforo verde")) {
                if (!jugador.jugar) {
                    if (!mesa.getJugador2().isEmpty()) {
                        for (int i = 0; i < mesa.getJugador2().size(); i++) {
                            if (mesa.getJugador2().get(i).getValor() == 0) {
                                if (mesa.getJugador2().get(i).getTipoCarta().equalsIgnoreCase("semaforo rojo") || mesa.getJugador2().get(i).getTipoCarta().equalsIgnoreCase("gasolina")) {
                                    mesa.getJugador2().remove(i);
                                    mesa.getJugador2().add(jugador.mano.get(pos));
                                    jugador.setJugar(true);
                                }
                            }
                        }
                    } else {
                        mesa.getJugador2().add(jugador.mano.get(pos));
                        jugador.setJugar(true);
                    }
                }
            }

            if (jugador.mano.get(pos).getTipoCarta().equalsIgnoreCase("semaforo rojo")) {
                if (jugadorEsperando.jugar) {
                    for (int i = 0; i < mesa.getJugador1().size(); i++) {
                        if (mesa.getJugador1().get(i).getValor() == 0) {
                            mesa.getJugador1().remove(i);
                        }
                    }

                    mesa.getJugador1().add(jugador.mano.get(pos));
                    jugadorEsperando.setJugar(false);
                }
            }

            if (jugador.mano.get(pos).getTipoCarta().equalsIgnoreCase("gasolina")) {
                if (!jugador.jugar) {
                    for (int i = 0; i < mesa.getJugador2().size(); i++) {
                        if (mesa.getJugador2().get(i).getValor() == 0 && mesa.getJugador2().get(i).getTipoCarta().equalsIgnoreCase("sin gasolina")) {
                            mesa.getJugador2().remove(i);
                            mesa.getJugador2().add(jugador.mano.get(pos));
                        }
                    }
                }
            }

            if (jugador.mano.get(pos).getTipoCarta().equalsIgnoreCase("sin gasolina")) {
                if (jugadorEsperando.jugar) {
                    for (int i = 0; i < mesa.getJugador1().size(); i++) {
                        if (mesa.getJugador1().get(i).getValor() == 0) {
                            mesa.getJugador1().remove(i);
                        }
                    }

                    mesa.getJugador1().add(jugador.mano.get(pos));
                    jugadorEsperando.setJugar(false);
                }
            }
        } else {
            if (jugador.jugar) {
                jugador.setKm(jugador.getKm() + jugador.mano.get(pos).getValor());
                mesa.getJugador2().add(jugador.mano.get(pos));
            }
        }

        jugador.mano.remove(pos);

    }

    public static void comprobarGanador(Jugador jugador1, Jugador jugador2, LinkedList<Carta> mazo, boolean gana1, boolean gana2) {
        // Se verifica si un jugador ha alcanzado los 1000 kilómetros o si el mazo está vacío
        // En ese caso, se determina al ganador del juego
        if (jugador1.getKm() >= 1000) {
            gana1 = true;
        }
        if (jugador2.getKm() >= 1000) {
            gana2 = true;
        }
        if (mazo.isEmpty()) {
            if (jugador1.getKm() > jugador2.getKm()) {
                gana1 = true;
            } else {
                gana2 = true;
            }
        }
    }

}
