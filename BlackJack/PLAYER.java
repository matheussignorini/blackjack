package blackjack;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private List<Carta> hand;

    public Player() {
        hand = new ArrayList<>();
    }

    public void drawCards(List<Carta> cards) {
        hand.addAll(cards);
    }

    public void drawCard(Carta card) {
        hand.add(card);
    }

    public void clearHand() {
        hand.clear();
    }

    public void showHand() {
        for (Carta card : hand) {
            System.out.println(card.getValor() + " de " + card.getNaipe());
        }
    }

    public int calculateScore() {
        int score = 0;
        for (Carta card : hand) {
            score += card.getValor();
        }
        return score;
    }

    public boolean checkIfBusted() {
        return calculateScore() > 21;
    }
}

class Dealer extends Player {
    @Override
    public void showHand() {
        System.out.println("Dealer's Hand:");
        super.showHand();
    }
}