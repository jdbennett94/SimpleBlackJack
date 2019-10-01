package com.example.simpleblackjack;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Game class, records player value including cards in hand, and totals
 * methods include deck creation and randomization, win, lose methods, bust
 * methods, hit methods for dealer and player, and more
 */
public class Game {

    public static List<String> dealer;
    public static List<String> player;
    public static int hitCount;
    public static int topcard;
    public static String[] deckOfCards;
    public static Boolean playerbust;
    public static Boolean dealerbust;
    public static int dealerTotal;
    public static int playerTotal;
    public static int turn;


    /**
     * Game object method, sets initial values for global variables and
     *
     */
    public Game() {

        //Deck initialized and shuffled
        deckOfCards = deck();
        topcard =4;
        hitCount=0;
        playerbust = false;
        dealerbust = false;


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

        //Checks who won blackjack and tie scenario
        /*if (dealerWin || playerWin) {

            if (dealerWin && playerWin) {
                //Output tie message and offer to replay game
                System.out.println("It's A Tie!");
                return;
            } else if (dealerWin) {
                //Output dealer wins
                System.out.println("Dealer Wins");
                return;
            } else {
                //Player victory
                System.out.println("Player Wins!");
                return;
            }

        //End of blackjack check
        } */


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
        //System.out.println(winner(playerTotal, dealerTotal));
//*******************************************************************************//

        //Hit Or Stay
        //int topcard = 4; //Keeps track of top card
        //boolean playerbust = false; //Keeps track of user bust

        //Reads usr statement of hit or stay, replace with button press
        //System.out.println("Hit or stay?");
        //Scanner kbd = new Scanner (System.in);
        //String decision = kbd.nextLine();

        String decision = "Stay";





        //Is game over or dealer turn?
        if(playerbust == false) {
            System.out.println("Dealer's turn");
        }
        else {
            System.out.println("Game overrrr");
        }



        //Checks if dealer is already higher than user
        if(vsPlayer(dealerTotal, playerTotal)) {

            //Dealer keeps picking up cards till he's above player total
            do {

                //Adds card to dealer hand arrayList, adds value to dealer total, increases top card
                dealer.add(deckOfCards[topcard]);
                dealerTotal += cardValue(deckOfCards[topcard]);
                topcard++;

                System.out.println(dealer);
                System.out.println("Dealer is " + dealerTotal);
                playerbust = bust(dealerTotal); //Checks if bust

            }while((vsPlayer(dealerTotal, playerTotal)) && (playerbust != true));
        }



        //Checks if dealer has bust or tied
        if(bust(dealerTotal)) {
            System.out.println("Player wins!!");
        }
        else if(dealerTotal == playerTotal) {
            System.out.println("It's a tie!");
        }
        else {
            System.out.println("Dealer wins");
        }










        //End of the game process
    }

    /*public int getPlayerTotal(){
        return playerTotal;
    }

    public void setPlayerTotal(int total){
        this.playerTotal = total;
    }


    public int getDealerTotal(){
        return dealerTotal;
    }

    public void setDealerTotal(int total){
        this.dealerTotal = total;
    }
    */


    /**
     * Dealer hit cycle, increments top card of deck, adds to total dealer value
     * @return int
     */
    public static int dealerHit(){

        //if(vsPlayer(dealerTotal, playerTotal)) {

        //Dealer keeps picking up cards till he's above player total

        //Adds card to dealer hand arrayList, adds value to dealer total, increases top card
        dealer.add(deckOfCards[topcard]);
        int cardVal = cardValue(deckOfCards[topcard]);
        if (cardVal == 11 && cardVal+dealerTotal > 21) {
            cardVal = 1;
        }

        dealerTotal += cardVal;
        topcard++;

        dealerbust = bust(dealerTotal); //Checks if bust
        hitCount++;
        return hitCount;

        //reset hitCount in GameActivity
        //}while((vsPlayer(dealerTotal, playerTotal)) && (playerbust != true));
    }





    /**
     * Player hit method to determine based on hit button click
     * @return int
     */
    public static int hit() {
    //Asks user to hit stay, iterates through hits

            //Increases hit, adds card to player hand, adds card value to player total
            //increases card number so top card is proper

            player.add(deckOfCards[topcard]);
            int cardVal2 = cardValue(deckOfCards[topcard]);
            if (cardVal2 == 11 && cardVal2+playerTotal > 21) {
                cardVal2 = 1;
            }

            playerTotal += cardVal2;
            topcard++;


            playerbust = bust(playerTotal); //Checks if bust
            hitCount++;
            return hitCount;
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



    /**
     * Determines if player (dealer or player) has bust (ie gone over 21)
     *
     * @param total int
     * @return boolean
     */
    public static boolean bust(int total){

        boolean bustCheck = false;

        if (total > 21){
            bustCheck = true;
        }

        return bustCheck;
    }



    /**
     * Determines if dealer needs to hit or stay based on player total
     *
     * @param dealer int
     * @param player int
     * @return boolean
     */
    public static boolean vsPlayer(int dealer, int player) {

        boolean hitagain = false;

        if (dealer<player) {
            hitagain = true;
        }

        if (dealer == 21) {
            hitagain = false;
        }


        return hitagain;
    }

}