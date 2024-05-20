package blackjack.game;

import blackjack.Deck;
import blackjack.Player;
import blackjack.Dealer;
import blackjack.utils.IO;

public class BlackJack {
    private Deck deck;
    private Player player;
    private Dealer dealer;

    public BlackJack() {
        deck = new Deck();
        player = new Player();
        dealer = new Dealer();
    }

    public void start() {
        deck.checkAndRefillDeck();
        player.drawCards(deck.dealCards());
        dealer.drawCards(deck.dealCards());
        showHands();
    }

    public void restart() {
        deck.clear();
        player.clearHand();
        dealer.clearHand();
        start();
    }

    public void showHands() {
        dealer.showHand();
        IO.print("");
        player.showHand();
        IO.print("Sua mao vale " + player.calculateScore() + " pontos");
        IO.print("");
    }

    public void hit() {
        player.drawCard(deck.deal());
        showHands();
    }

    public void stand() {
        dealerTurn();
    }

    public void dealerTurn() {
        if (player.calculateScore() == 21) {
            IO.print("Você venceu!");
            return;
        }

        int dealerScore = dealer.calculateScore();
        while (dealerScore < 17) {
            Carta newCard = deck.deal();
            dealer.drawCard(newCard);
            dealerScore = dealer.calculateScore();
            IO.print("Dealer pescou!");
            IO.print("");
            showHands();
        }

        if (dealerScore > 21) {
            IO.print("Dealer estourou! Você venceu!");
            return;
        }
        if (dealerScore == 21) {
            IO.print("Dealer conseguiu 21! Dealer venceu!");
            return;
        }

        int playerScore = player.calculateScore();
        IO.print("Sua pontuação: " + playerScore);
        IO.print("Pontuação do dealer: " + dealerScore);

        if (playerScore > dealerScore) {
            IO.print("Você venceu!");
            return;
        }
        if (playerScore < dealerScore) {
            IO.print("Dealer venceu!");
            return;
        }
        IO.print("Empate!");
    }

    public boolean checkIfBusted() {
        return player.checkIfBusted();
    }

    public void dealerWin() {
        IO.print("O Dealer foi o vencedor!");
    }
}