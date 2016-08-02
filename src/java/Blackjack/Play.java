package Blackjack;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Random;
import javax.faces.bean.ManagedBean;

@ManagedBean
public class Play {

    // variables
    private Integer playerMoney = null;
    private Integer money = null;
    private Integer bet = null;
    private Integer usedBet = null;
    //numberofplayes
    private Integer maxHands = null;
    //type of player: begginer or basic player
    private String playerType = null;

    private ArrayList<String> results = new ArrayList<>();
    private Integer playerWins = 0;
    private Integer dealerWins = 0;
    private Double winRatio = 0.0;
    private Integer numberOfHands = 0;
    boolean autoWin = false;
    boolean playerWin = false;
    boolean bust = false;
    boolean push = false;
    boolean dBust = false;
    private String winner = null;
    private String over21 = null;
    private Integer tplayer;
    private Integer tdealer;
    
    // testing::
    ArrayList<Result> res = new ArrayList<Result>();

    // constructor
    public Play() {

    }

    /**
     * @return the playerMoney
     */
    public Integer getPlayerMoney() {
        return playerMoney;
    }

    /**
     * @param playerMoney the playerMoney to set
     */
    public void setPlayerMoney(Integer playerMoney) {
        this.playerMoney = playerMoney;
    }
    
    /**
     * @return the money
     */
    public Integer getMoney() {
        return money;
    }

    /**
     * @return the bet
     */
    public Integer getBet() {
        return bet;
    }

    /**
     * @param bet the bet to set
     */
    public void setBet(Integer bet) {
        this.bet = bet;
    }
    
    /**
     * @return the usedBet
     */
    public Integer getUsedBet() {
        return usedBet;
    }

    /**
     * @return the maxHands
     */
    public Integer getMaxHands() {
        return maxHands;
    }

    /**
     * @param maxHands the maxHands to set
     */
    public void setMaxHands(Integer maxHands) {
        this.maxHands = maxHands;
    }

    /**
     * @return the playerType
     */
    public String getPlayerType() {
        return playerType;
    }

    /**
     * @param playerType the playerType to set
     */
    public void setPlayerType(String playerType) {
        this.playerType = playerType;
    }

    /**
     * @return the playerWins
     */
    public Integer getPlayerWins() {
        return playerWins;
    }

    /**
     * @return the dealerWins
     */
    public Integer getDealerWins() {
        return dealerWins;
    }

    /**
     * @return the winRatio
     */
    public Double getWinRatio() {
        return winRatio;
    }
    
    /**
     * @return the numberOfHands
     */
    public Integer getNumberOfHands() {
        return numberOfHands;
    }

    /**
     * @return the winner
     */
    public String getWinner() {
        return winner;
    }

    /**
     * @return the over21
     */
    public String getOver21() {
        return over21;
    }

    /**
     * @return the tplayer
     */
    public int getTplayer() {
        return tplayer;
    }

    /**
     * @return the tdealer
     */
    public int getTdealer() {
        return tdealer;
    }

    /**
     * @param c
     * @return integer rank value of card
     */
    private int scrub(Card c) {
        int r = c.rank();
        return r;
    }

    /**
     * @param deck
     * @param card
     * @param count
     * @return
     */
    private ArrayList<Card> discardPile(ArrayList<Card> deck, Card card, int count) {
        deck.add(count, card);
        return deck;
    }

    /**
     * @param deck
     * @param spot
     * @return
     */
    private Card getCard(ArrayList<Card> deck, int spot) {
        return deck.get(spot);
    }

    /**
     * @param hand
     * @return return total value of hand
     */
    private int totalHand(ArrayList<Integer> hand) {
        int counter = 0;
        for (Integer i : hand) {
            counter += i;
        }
        if (hand.contains(1))
            if((counter + 10) < 22)
                counter += 10;
        return counter;
    }

    /**
     * @return random bet value
     */
    private int randomBet() {
        int[] bets = new int[10];
        bets[0] = 5;
        for (int i = 1; i < bets.length; i++) {
            bets[i] = bets[i - 1] + 5;
        }
        int rnd = new Random().nextInt(bets.length);
        return bets[rnd];
    }

    private String nextMove(ArrayList<Integer> hand, ArrayList<Integer> dealerHand) {
        String move = null;
        // check which move method to use
        if (playerType.equals("Beginner")) {
            move = Move.beginner(hand);
        }
        if (playerType.equals("Intermediate")) {
            move = Move.intermediate(hand, dealerHand);
        }
        return move;
    }
    
    private double roundTo2Decimals(double winRatio){
        DecimalFormat df2 = new DecimalFormat("###.##");
        return Double.valueOf(df2.format(winRatio));
    }
    
    /**
     * @return the results
     */
    public ArrayList<String> getResults(){
        return results;
    }

    /**
     * @return the null
     */
    public String play() {

        // simulation variables
        boolean handOver = false;
        boolean playerTurnOver = false;
        int cardsLeft = 0;
        String move = "";
        ArrayList<Integer> discard = new ArrayList<Integer>();
        int discardC = 0;
        int discardP = 0;
        ArrayList<Integer> hand = new ArrayList<Integer>();
        ArrayList<Integer> dealerHand = new ArrayList<Integer>();
        playerWins = 0;
        dealerWins = 0;
        winRatio = null;
        numberOfHands = 0;
        autoWin = false;
        playerWin = false;
        boolean bust = false;
        boolean push = false;
        boolean dBust = false;
        winner = null;
        over21 = null;
        
        money = playerMoney;
        
        if (playerType == null) {
            playerType = "Beginner";
        }

        // user entered info checkers
        // check if player money set,
        // if not, start with $100
        if (money == null) {
            money = playerMoney = 100;
        }

        // if no maxHands set, set to 5
        if (maxHands == null) {
            maxHands = 5;
        }

        // build deck and shuffle
        DeckOfCards deck = new DeckOfCards();
        deck.shuffle(100);

        // main iterative loop
        while (numberOfHands < maxHands) {

            // if bet isn't null, use bet.
            // else use a radom bet each hand.
            if (bet != null){
                usedBet = bet;
            } else {
                usedBet = randomBet();
            }

            // play hand
            while (!handOver) {

                // reset values to false
                push = false;
                autoWin = false;
                playerWin = false;
                bust = false;
                dBust = false;

                // clear all hands
                hand.clear();
                dealerHand.clear();

                // each gets 1 card
                int temp = scrub(deck.deal());
                discard.add(temp);
                hand.add(temp); // player hand
                temp = scrub(deck.deal());
                discard.add(temp);
                dealerHand.add(temp); // dealer hand

                // each gets second card
                temp = scrub(deck.deal());
                discard.add(temp);
                hand.add(temp); // player hand
                temp = scrub(deck.deal());
                // dealers second card is not added to discard to keep hidden untill dealer move
                dealerHand.add(temp); // dealer hand

                // count value of cards
                tplayer = totalHand(hand);
                tdealer = totalHand(dealerHand);

                /**
                 * **** Start check for Blackjack *****
                 */
                // if hand contains an ACE
                if (hand.contains(1)) // if ACE = 11 and total hand less than 22
                {
                    if ((tplayer + 10) < 22) // count ACE as 11
                    {
                        tplayer += 10;
                    }
                }

                // if dealerHand contains an ACE
                if (dealerHand.contains(1)) // if ACE == 11 and total dealerHand less than 22
                {
                    if ((tdealer + 10) < 22) // count ACE as 11
                    {
                        tdealer += 10;
                    }
                }

                // determine if autowin is to occur or push based on Blackjack
                if (tdealer == 21 && tplayer != 21) {
                    autoWin = true;
                }
                if (tplayer == 21 && tdealer != 21) {
                    autoWin = true;
                    playerWin = true;
                }
                if (tplayer == 21 && tdealer == 21) {
                    push = true;
                }

                if (push | autoWin) {
                    break;
                }

                /**
                 * * End check for Blackjack **
                 */

                // decide what to do.
                move = nextMove(hand, dealerHand);

                // player decision
                while (!move.equals("stand") && !autoWin && !bust) {
                    if (move.equals("hit")) {
                        temp = scrub(deck.deal());
                        discard.add(temp);
                        hand.add(temp);
                    } else if (move.equals("double down")) {
                        temp = scrub(deck.deal());
                        discard.add(temp);
                        hand.add(temp);
                        // double the bet
                        usedBet += usedBet;
                        // break out of while loop
                        move = "stand";
                        break;
                    }
                    move = nextMove(hand, dealerHand);
                }

                // get player total and check if player busted
                tplayer = totalHand(hand);
                // if hand contains an ACE
            if (hand.contains(1)) // if ACE = 11 and total hand less than 22
            {
                if ((tplayer + 10) < 22) // count ACE as 11
                {
                    tplayer += 10;
                }
            }
                if(tplayer > 21){
                    bust = true;
                }
                
                // place dealers second card to discard (reveal dealers second card)
                discard.add(dealerHand.get(1));

                // Dealer decision (if necessary)
                if (!bust && !autoWin) {
                    move = Move.dealer_move(dealerHand);
                    while (!move.equals("stand")) {
                        temp = scrub(deck.deal());
                        discard.add(temp);
                        dealerHand.add(temp);
                        move = Move.dealer_move(dealerHand);
                    }
                }

                // end current hand
                handOver = true;
            }

            //check deck for reshuffle
            if (deck.cardsLeft() < 20) {
                deck = new DeckOfCards();
                deck.shuffle(100);
            }

            //get total of dealerHand
            tdealer = totalHand(dealerHand);
            
            // if dealerHand contains an ACE
            if (dealerHand.contains(1)) // if ACE == 11 and total dealerHand less than 22
            {
                if ((tdealer + 10) < 22) // count ACE as 11
                {
                    tdealer += 10;
                }
            }

            //finish game
            if (tdealer > 21) {
                dBust = true;
            }
            if (tdealer < tplayer && !bust) {
                playerWin = true;
            }
            if (!bust && dBust) {
                playerWin = true;
            }
            if (tdealer == tplayer) {
                push = true;
            }

            //enact payout
            money -= usedBet; // initial bet
            if (playerWin && autoWin) {
                money += usedBet * (3 / 2);
                playerWins++;
            } else if (playerWin) {
                money += usedBet * 2;
                playerWins++;
            } else if (push) {
                money += usedBet;
            } else {
                dealerWins++;
            }
            
            /**
             * Increment hands played
             */
            numberOfHands++;

            // who won?
            if(!playerWin && !dBust && !push)
                winner = "Dealer";
            else if(autoWin && !playerWin)
                winner = "Dealer";
            else if(playerWin)
                winner = "Player";
            else if(autoWin && playerWin)
                winner = "Player";
            else if(push)
                winner = "Push";
            else
                winner = "ERROR: UNKNOWN WINNER";
            
            // Anyone Bust?
            if(bust)
                over21 = "Player";
            else if(dBust)
                over21 = "Dealer";
            else
                over21 = "No";
            
            // Winning Ratio
            if(playerWins > 0 || dealerWins > 0)
                winRatio = ((double)playerWins/(double)(playerWins+dealerWins))*100 ;
            else
                winRatio = 0.0;
            winRatio = roundTo2Decimals(winRatio);

            //string of cards
            String pCards = null;
            String dCards = null;
            for (int i = 0; i<hand.size(); i++){
                if (i==0){
                    pCards = hand.get(i).toString();
                } else {
                    pCards = pCards + ", " + hand.get(i).toString();
                }
            }
            for (int i = 0; i<dealerHand.size(); i++){
                if (i==0){
                    dCards = dealerHand.get(i).toString();
                } else {
                    dCards = dCards + ", " + dealerHand.get(i).toString();
                }
            }
            
            /**
             * Results stored as follows:
             *
             * Hands played: numberOfHands.
             * Current player money: playerMoney.
             * Bet used: usedBet.
             * Player card total: tplayer.
             * Dealer card total: tdealer.
             * who won?: winner = check who won. 
             * Anyone bust?: over21.
             * Number of player wins: playerWins.
             * Number of dealer wins: dealerWins.
             * Winning ratio: winRatio.
             * Players money that is left: money
             */
            results.add("Hand: " + numberOfHands.toString());
            results.add("Bet: $" + usedBet.toString());
            results.add("Player cards: " + pCards);
            results.add("Players total cards value: " + tplayer.toString());
            results.add("Dealer cards: " + dCards);
            results.add("Dealers total cards value: " + tdealer.toString());
            results.add("The winner is: " + winner);
            results.add("Anyone bust?: " + over21);
            results.add("Total player wins: " + playerWins.toString());
            results.add("Total dealer wins: " + dealerWins.toString());
            results.add("Player winning ratio: " + winRatio.toString() + "%");
            results.add("Money on hand: $" + money.toString());
            results.add("--------------------");
            
            // Test::
            res.add(new Result(numberOfHands,usedBet,pCards,tplayer,dCards,
                    tdealer,winner,over21,playerWins,dealerWins,winRatio,money));
            
            // reset hand switch
            handOver = false;
        }

        return null;
    }
    
    public ArrayList<Result> getRes(){
        return res;
    }



}
