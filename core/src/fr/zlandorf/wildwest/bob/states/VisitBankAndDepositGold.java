package fr.zlandorf.wildwest.bob.states;

import com.badlogic.gdx.ai.fsm.State;
import com.badlogic.gdx.ai.msg.Telegram;
import fr.zlandorf.wildwest.Location;
import fr.zlandorf.wildwest.bob.Bob;

public class VisitBankAndDepositGold implements State<Bob> {
    @Override
    public void enter(Bob bob) {
        if (bob.getLocation() != Location.BANK) {
            bob.talk("Goin' to the bank. Yes siree");
            bob.setLocation(Location.BANK);
        }
    }

    @Override
    public void update(Bob bob) {
        bob.addGoldCarriedToWealth();
        bob.talk("Depositing gold. Total savings now: " + bob.getWealth());
        bob.getFsm().revertToPreviousState();
    }

    @Override
    public void exit(Bob bob) {
        bob.talk("Leavin' the bank");
    }

    @Override
    public boolean onMessage(Bob bob, Telegram telegram) {
        return false;
    }
}
