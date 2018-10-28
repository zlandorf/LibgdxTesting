package fr.zlandorf.wildwest.bob.states;

import com.badlogic.gdx.ai.fsm.State;
import com.badlogic.gdx.ai.msg.Telegram;
import fr.zlandorf.wildwest.Location;
import fr.zlandorf.wildwest.bob.Bob;

public class GoHomeAndSleepTillRested implements State<Bob> {
    @Override
    public void enter(Bob bob) {
        if (bob.getLocation() != Location.SHACK) {
            bob.talk("Walkin' home");
            bob.setLocation(Location.SHACK);
        }
    }

    @Override
    public void update(Bob bob) {
        if (!bob.isFatigued()) {
            bob.talk("All mah fatigue has drained away. Time to find more gold!");

            bob.getFsm().changeState(BobState.ENTER_MINE_AND_DIG_FOR_NUGGET);
        } else {
            bob.decreaseFatigue();
            bob.talk("ZZZZ... ");
        }
    }

    @Override
    public void exit(Bob bob) {

    }

    @Override
    public boolean onMessage(Bob bob, Telegram telegram) {
        return false;
    }
}
