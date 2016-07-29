
package Blackjack;

import javax.faces.bean.ManagedBean;

@ManagedBean
public class Play {
    
    // variables
    private Integer playerMoney = null;
    private Integer bet = null;
    //numberofplayes
    private Integer maxHands = null;
    //type of player: begginer or basic player
    private String playerType = null;
    
    // constructor
    public Play(){
        
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

}
