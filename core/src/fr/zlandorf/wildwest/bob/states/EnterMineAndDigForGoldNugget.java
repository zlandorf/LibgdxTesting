package fr.zlandorf.wildwest.bob.states;

import com.badlogic.gdx.ai.fsm.State;
import com.badlogic.gdx.ai.msg.Telegram;
import fr.zlandorf.wildwest.Location;
import fr.zlandorf.wildwest.bob.Bob;

public class EnterMineAndDigForGoldNugget implements State<Bob> {

    @Override
    public void enter(Bob bob) {
        if (bob.getLocation() != Location.GOLD_MINE) {
            bob.talk("Walkin' to the goldmine");
            bob.setLocation(Location.GOLD_MINE);
        }
    }

    @Override
    public void update(Bob bob) {
        bob.talk("Pickin' up a nugget");
        bob.addGold(1);
        bob.increaseFatigue();

        if (bob.isPocketsFull()) {
            bob.getFsm().changeState(BobState.VISIT_BANK_AND_DEPOSIT_GOLD);
        }
    }

    @Override
    public void exit(Bob bob) {
        bob.talk("Ah'm leavin' the goldmine with mah pockets full o' sweet gold");
    }

    @Override
    public boolean onMessage(Bob bob, Telegram telegram) {
        return false;
    }
}
