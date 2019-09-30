package com.example.simpleblackjack;

import java.util.ArrayList;
import java.util.List;

public class Game {

    public Game() {
        //Deck initialized and shuffled
        String[] deckOfCards = deck();


        //Dealer given two cards
        List<String> dealer = new ArrayList<String>();
        dealer.add(deckOfCards[0]);
        dealer.add(deckOfCards[1]);


        //Player given two cards
        List<String> player = new ArrayList<String>();
        player.add(deckOfCards[2]);
        player.add(deckOfCards[3]);


        //Check for 21 on both (method)
        boolean dealerWin = false;
        boolean playerWin = false;

        //dealer
        if (blackjackCheck(deckOfCards[0], deckOfCards[1])) {

            //Dealer black jack
            dealerWin = true;

        }

        //player
        if (blackjackCheck(deckOfCards[2], deckOfCards[3])) {

            //player black jack
            playerWin = true;

        }

        //Checks who won blackjack and tie scenario
        if (dealerWin || playerWin) {

            if (dealerWin && playerWin) {
                //Output tie message and offer to replay game
                System.out.println("It's A Tie!");
            } else if (dealerWin) {
                //Output dealer wins
                System.out.println("Dealer Wins");
            } else {
                //Player victory
                System.out.println("Player Wins!");
            }


        } //End of blackjack check


        // Prototype two card draw game
        //Check who has higher value cards
        int dealerC1, dealerC2, dealerTotal;
        int playerC1, playerC2, playerTotal;


        //Dealer values
        dealerC1 = cardValue(deckOfCards[0]);
        dealerC2 = cardValue(deckOfCards[1]);
        dealerTotal = dealerC1 + dealerC2;


        //Player values
        playerC1 = cardValue(deckOfCards[2]);
        playerC2 = cardValue(deckOfCards[3]);
        playerTotal = playerC1 + playerC2;

        //Calls method to determine winner
        System.out.println(winner(playerTotal, dealerTotal));



                /*
                //Option for player to hit/stay
                //
                //Check over 21
                //
                //Option to hit stay
                //
                //Dealer draw
                */

    }

    /**
     * Important: Creates deck of String type cards, then shuffles the deck,
     * returns String object deck
     *
     * @return String[] fulldeck;
     */
    public static String[] deck() {

        //Creates deck of cards String array, end product will be a double matrix array
        String[] cardClass = {"Hearts", "Clubs", "Diamonds", "Spades"};

        //Possible cards in a type
        String[] cardNumber = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King", "Ace"};

        //52 card value, can be increased theoretically for larger collections like a casino
        int numDeck;
        numDeck = cardClass.length * cardNumber.length;

        //Sets deck array to max of 52 indexes (for 52 card)
        String[] fullDeck = new String[numDeck];


        //Iterating to create deck
        for(int c=0; c < cardNumber.length; c++)
        {
            for(int q=0; q < cardClass.length; q++)
            {
                fullDeck[cardClass.length*c + q] = cardNumber[c] + " of " + cardClass[q];
            }
        }


        //We have full deck of cards now, but they need to be shuffled randomly each playthrough
        for (int s=0; s<numDeck; s++){

            //Randomizing card variable
            int randomCard;
            randomCard = s + (int)(Math.random()*(numDeck-s));

            //Place holder String
            String delta = fullDeck[randomCard];

            //Fills out deck with new order of cards
            fullDeck[randomCard] = fullDeck[s];
            fullDeck[s]=delta;


        }

        //This returns the randomized deck
        return fullDeck;

    }


    /**
     * Changes the string value of the card to a int value for totalling
     *
     * @param card (String)
     * @return int
     */
    public static int cardValue(String card) {

        int cardVal;

        // Splits line (card name ex. ace of diamionds) into individual words within an array (ie.
        // splitStr[0] = "ace", splitStr[1]="of", splitStr[2]="diamonds"
        String[] splitStr = card.split("\\s+");

        // splitStr[0] holds first word of each card name (ex. ACE of hearts, TEN of diamonds, etc)
        // We convert that index to int for value
        if(splitStr[0].equals("King") || splitStr[0].equals("Queen") || splitStr[0].equals("Jack")) {

            cardVal = 10;

        }
        else if (splitStr[0].equals("Ace")) {

            cardVal = 11;

        }
        else {

            //Translates card string value into int value
            int changeValue = Integer.parseInt(splitStr[0]);
            cardVal = changeValue;

        }

        return cardVal;

    }


    /**
     * Determines if player or dealer hit blackjack (21)
     *
     * @param card1 (String)
     * @param card2 (String)
     * @return boolean
     */
    public static boolean blackjackCheck(String card1, String card2){

        boolean gameoverBK = false;

        String[] splitStr1 = card1.split("\\s+");
        String[] splitStr2 = card2.split("\\s+");


        // As with the value method, the first index holds the numerical designator (ex.
        // splitStr1[0] = "Ace" or "Ten" etc.
        if (splitStr1[0].equals("Ace") || splitStr2[0].equals("Ace"))
        {
            //If first card is ace, check second card for 10 value
            if (splitStr1[0].equals("Ace"))
            {
                if (splitStr2[0].equals("King") || splitStr2[0].equals("Queen") || splitStr2[0].equals("Jack"))
                {
                    gameoverBK = true;
                }
            }
            else
            {
                if (splitStr1[0].equals("King") || splitStr1[0].equals("Queen") || splitStr1[0].equals("Jack"))
                {
                    gameoverBK = true;
                }
            }
            //The else above is if the second card is an ace, check the first card

        }

        //If return of true, means blackjack victory
        return gameoverBK;

    }


    /**
     *  Determines if player wins, loses or ties
     *
     * @param player
     * @param dealer
     * @return String;
     */
    public static String winner (int player, int dealer) {

        String win = "";

        if (player > dealer)
        {
            win = "Congrats you've won!";
        }
        else if(player == dealer)
        {
            win = "It's a tie!";
        }
        else
            win = "dealer wins";

        return win;

    }

    public static boolean bust(int total){

        boolean bustCheck = false;

        if (total > 21){
            bustCheck = true;
        }

        return bustCheck;
    }

}