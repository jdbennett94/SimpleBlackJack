package com.example.simpleblackjack;

import java.util.ArrayList;
import java.util.List;


/**
 * Game class, records player value including cards in hand, and totals
 * methods include deck creation and randomization, win, lose methods, bust
 * methods, hit methods for dealer and player, and more
 */
public class Game {

    //Global variable declarations for game player aspects
    public static List<String> dealer;
    public static List<String> player;
    public static int hitCount;
    public static int topcard;
    public static String[] deckOfCards;
    public static Boolean playerbust;
    public static Boolean dealerbust;
    public static int dealerTotal;
    public static int playerTotal;
    public static int playerHitCount;
    public static int dealerHitCount;


    /**
     * Game object method, sets initial values for global variables and creates
     * initial 2 cards for each player's hands and sets their value as well.
     * Additionally initializes deck with a randomized collection of string repres.
     * of the cards
     */
    public Game() {

        //Deck initialized and shuffled
        deckOfCards = deck();
        topcard =4;
        hitCount=0;
        playerbust = false;
        dealerbust = false;
        playerHitCount = 0;
        dealerHitCount = 0;


        //Create ArrayList of both players
        dealer = new ArrayList<String>();
        player = new ArrayList<String>();

        //Dealer given two cards
        dealer.add(deckOfCards[0]);
        dealer.add(deckOfCards[1]);

        //Player given two cards
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


        //Sets first two card
        int dealerC1, dealerC2;
        int playerC1, playerC2;


        //Dealer values
        dealerC1 = cardValue(deckOfCards[0]);
        dealerC2 = cardValue(deckOfCards[1]);
        dealerTotal = dealerC1 + ace(dealerC2,dealerC1);


        //Player values
        playerC1 = cardValue(deckOfCards[2]);
        playerC2 = cardValue(deckOfCards[3]);
        playerTotal = playerC1 + ace(playerC2, playerC1);



    }





    /**
     * Hit method which determines that if the hit process is not being done by the
     * dealer, iterates through hit methods up to 3 times and unveiling the card
     * each time. Ace value conditions are taken into account by having the ace method
     * called and the top card of the deck is incremented to account for "pulling" a
     * card from the top of the deck. Additionally, player hand value are increased
     * accordingly with their selected card
     * @param Dealer boolean
     *              boolean object taken in to determine if player or dealer
     * @return int
     *              int is returned, the number of hits taken
     */
    public static int hit(Boolean Dealer) {

        //Increases hit, adds card to player hand, adds card value to player total

        //increases card number so top card is proper

        if (!Dealer) {
            player.add(deckOfCards[topcard]);
            int cardVal = cardValue(deckOfCards[topcard]);
            cardVal = ace(cardVal, playerTotal);
            playerTotal += cardVal;
            topcard++;

            //playerbust = bust(playerTotal); //Checks if bust -- not sure if this is needed

            playerHitCount++;
            return playerHitCount;
        } else {
            dealer.add(deckOfCards[topcard]);
            int cardVal = cardValue(deckOfCards[topcard]);
            cardVal = ace(cardVal, dealerTotal);
            dealerTotal += cardVal;
            topcard++;

            //playerbust = bust(dealerTotal); //Checks if bust -- not sure if this is needed
            dealerHitCount++;
            return dealerHitCount;

        }
    }






    /**
     * Important: Creates deck of String type cards, then shuffles the deck, Variation
     * of demo'd deck processes researched, String representation was the most easy to
     * visualize
     *
     * @return String[] fulldeck;
     */
    public static String[] deck() {

        //Possible cards in a type
        String[] cardNumber = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King", "Ace"};

        //Creates deck of cards String array, end product will be a double matrix array
        String[] cardClass = {"Hearts", "Clubs", "Diamonds", "Spades"};


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
     * Blackjack determiner method, if player/dealer has 21
     * @param total int
     *              playerTotal or dealerTotal value of cards in two card hand
     * @return boolean
     *              boolean is returned either confirming (true) 21 or false (!21)
     */
    public static boolean blackJack(int total)
    {
        if(total==21)
            return true;
        return false;
    }


    /**
     * Bust method that determines if player/dealer total value has exceeded 21
     * ie has busted out
     * @param total int
     *              Either playerTotal or dealerTotal passed through to determine
     * @return boolean
     *              boolean is returned. True means bust , false means in the game
     */
    public static boolean bust(int total)
    {
        if(total>21)
            return true;
        return false;
    }



    /**
     * Player win method determines if playerTotal (value of cards in hand) exceeds
     * the dealerTotal (value of cards in dealer's hand)
     * @return boolean
     *              boolean is returned. True if player greater than dealer. False not
     */
    public  static boolean playerWin() {

        if (playerTotal > dealerTotal)
            return true;
        return false;
    }


    /**
     * Dealer win method determines if dealerTotal (value of cards in dealer hand)
     * is greater than player hand value
     * @return boolean
     *              boolean is returned. True if dealer is greater than player. False
     *              not
     */
    public static  boolean dealerWin() {

            if (dealerTotal > playerTotal)
                return true;
            return false;
    }


    /**
     * Ace method is essentially the conditional ace card scenario. The method change
     * the value of an ace card from 11 to 1 if the greater value would incur a bust
     * scenario with the cards in hand
     * @param cardVal int
     *                Ace card in question
     * @param playerTotal int
     *                Total current value of the player's hand
     * @return int
     *              int is returned, changing ace value to 1 if bust occurs
     */
    public static int ace(int cardVal, int playerTotal)
    {

            if(cardVal==11)
                if(bust(cardVal+playerTotal))
                    return 1;
            return cardVal;

    }


    /**
     * Tie scenario method, determines if the values of player's hand and dealer's
     * are equal
     * @return boolean
     *              boolean is returned. True if equal value hands, false if
     *              otherwise
     */
    public static boolean tie(){

        if(playerTotal == dealerTotal){
            return true;
        }
        return false;

    }

}