/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Blackjack;

/**
 *
 * @author Kostia
 */
public class Result {
    private Integer numberOfHands;
    private Integer bet;
    private String pCards;
    private String dCards;
    private Integer tPlayer;
    private Integer tDealer;
    private String winner;
    private String over21;
    private Integer playerWins;
    private Integer dealerWins;
    private double winRatio;
    private Integer money;
    
    // constructor
    public Result(Integer numberOfHands, Integer bet, String pCards, Integer tPlayer, 
            String dCards, Integer tDealer, String winner, String over21, 
            Integer playerWins, Integer dealerWins, double winRatio, Integer money){
        this.numberOfHands = numberOfHands;
        this.bet = bet;
        this.pCards = pCards;
        this.tPlayer = tPlayer;
        this.dCards = dCards;
        this.tDealer = tDealer;
        this.winner = winner;
        this.over21 = over21;
        this.playerWins = playerWins;
        this.dealerWins = dealerWins;
        this.winRatio = winRatio;
        this.money = money;
    }

    /**
     * @return the maxHand
     */
    public Integer getNumberOfHands() {
        return numberOfHands;
    }

    /**
     * @param numberOfHands the maxHand to set
     */
    public void setNumberOfHands(Integer numberOfHands) {
        this.numberOfHands = numberOfHands;
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
     * @return the pCards
     */
    public String getpCards() {
        return pCards;
    }

    /**
     * @param pCards the pCards to set
     */
    public void setpCards(String pCards) {
        this.pCards = pCards;
    }

    /**
     * @return the dCards
     */
    public String getdCards() {
        return dCards;
    }

    /**
     * @param dCards the dCards to set
     */
    public void setdCards(String dCards) {
        this.dCards = dCards;
    }

    /**
     * @return the tPlayer
     */
    public Integer gettPlayer() {
        return tPlayer;
    }

    /**
     * @param tPlayer the tPlayer to set
     */
    public void settPlayer(Integer tPlayer) {
        this.tPlayer = tPlayer;
    }

    /**
     * @return the tDealer
     */
    public Integer gettDealer() {
        return tDealer;
    }

    /**
     * @param tDealer the tDealer to set
     */
    public void settDealer(Integer tDealer) {
        this.tDealer = tDealer;
    }

    /**
     * @return the winner
     */
    public String getWinner() {
        return winner;
    }

    /**
     * @param winner the winner to set
     */
    public void setWinner(String winner) {
        this.winner = winner;
    }

    /**
     * @return the over21
     */
    public String getOver21() {
        return over21;
    }

    /**
     * @param over21 the over21 to set
     */
    public void setOver21(String over21) {
        this.over21 = over21;
    }

    /**
     * @return the playerWins
     */
    public Integer getPlayerWins() {
        return playerWins;
    }

    /**
     * @param playerWins the playerWins to set
     */
    public void setPlayerWins(Integer playerWins) {
        this.playerWins = playerWins;
    }

    /**
     * @return the dealerWins
     */
    public Integer getDealerWins() {
        return dealerWins;
    }

    /**
     * @param dealerWins the dealerWins to set
     */
    public void setDealerWins(Integer dealerWins) {
        this.dealerWins = dealerWins;
    }

    /**
     * @return the winRatio
     */
    public double getWinRatio() {
        return winRatio;
    }

    /**
     * @param winRatio the winRatio to set
     */
    public void setWinRatio(double winRatio) {
        this.winRatio = winRatio;
    }

    /**
     * @return the money
     */
    public Integer getMoney() {
        return money;
    }

    /**
     * @param money the money to set
     */
    public void setMoney(Integer money) {
        this.money = money;
    }
}