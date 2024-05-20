package blackjack;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Deck {
    private List<Carta> cartas;
    private Random random;

    public Deck() {
        cartas = new ArrayList<>();
        random = new Random();
        refillDeck();
    }

    public void checkAndRefillDeck() {
        if (cartas.size() < 15) {
            refillDeck();
        }
    }

    private void refillDeck() {
        cartas.clear();
        for (Naipe naipe : Naipe.values()) {
            for (int i = 2; i <= 10; i++) {
                cartas.add(new CartaNumero(i, naipe));
            }
            for (int i = 0; i < 3; i++) {
                cartas.add(new CartaFigura(naipe));
            }
            cartas.add(new CartaAs(naipe));
        }
    }

    public List<Carta> dealCards() {
        List<Carta> hand = new ArrayList<>();
        hand.add(deal());
        hand.add(deal());
        return hand;
    }

    public Carta deal() {
        if (!cartas.isEmpty()) {
            int index = random.nextInt(cartas.size());
            return cartas.remove(index);
        }
        return null; // Deck is empty
    }

    public void clear() {
        cartas.clear();
    }
}

class Carta {
    public abstract int getValor();
    public abstract Naipe getNaipe();
}

class CartaAs extends Carta {
    private Naipe naipe;

    public CartaAs(Naipe naipe) {
        this.naipe = naipe;
    }

    @Override
    public int getValor() {
        return 1;
    }

    @Override
    public Naipe getNaipe() {
        return naipe;
    }
}

class CartaFigura extends Carta {
    private Naipe naipe;

    public CartaFigura(Naipe naipe) {
        this.naipe = naipe;
    }

    @Override
    public int getValor() {
        return 10;
    }

    @Override
    public Naipe getNaipe() {
        return naipe;
    }
}

class CartaNumero extends Carta {
    private int numero;
    private Naipe naipe;

    public CartaNumero(int numero, Naipe naipe) {
        this.numero = numero;
        this.naipe = naipe;
    }

    @Override
    public int getValor() {
        return numero;
    }

    @Override
    public Naipe getNaipe() {
        return naipe;
    }
}

enum Naipe {
    OUROS, ESPADAS, PAUS, COPAS
}