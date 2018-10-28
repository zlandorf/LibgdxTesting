package fr.zlandorf.wildwest.bob;

import com.badlogic.gdx.ai.GdxAI;
import com.badlogic.gdx.ai.Logger;
import com.badlogic.gdx.ai.fsm.DefaultStateMachine;
import com.badlogic.gdx.ai.fsm.State;
import com.badlogic.gdx.ai.fsm.StateMachine;
import com.badlogic.gdx.ai.msg.Telegram;
import com.badlogic.gdx.ai.msg.Telegraph;
import fr.zlandorf.wildwest.Location;
import fr.zlandorf.wildwest.bob.states.BobState;

public class Bob implements Telegraph {

    private final static Logger logger = GdxAI.getLogger();

    public static final int THRESHOLD_THIRST = 5;
    public static final int THRESHOLD_COMFORT = 5;
    public static final int THRESHOLD_TIREDNESS = 5;

    public static final int MAX_GOLD_CARRIED = 3;

    private Location location = Location.SHACK;
    private int goldCarried = 0;
    private int thirst = 0;
    private int fatigue = 0;
    private int moneyInBank = 0;

    private StateMachine<Bob, State<Bob>> fsm = new DefaultStateMachine<Bob, State<Bob>>(this, BobState.GO_HOME_AND_SLEEP_TILL_RESTED);

    @Override
    public boolean handleMessage(Telegram msg) {
        System.out.println(String.format("Received message %s from %s", msg.message, msg.sender));
        return true;
    }

    public void talk(String message) {
        logger.info(getClass().getSimpleName(), message);
    }

    public StateMachine<Bob, State<Bob>> getFsm() {
        return fsm;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public void decreaseFatigue() {
        fatigue--;
    }

    public void increaseFatigue() {
        fatigue++;
    }

    public boolean isFatigued() {
        return fatigue > THRESHOLD_TIREDNESS;
    }

    public void addGold(int count) {
        goldCarried += count;
    }

    public boolean isPocketsFull() {
        return goldCarried >= MAX_GOLD_CARRIED;
    }

    public void addGoldCarriedToWealth() {
        moneyInBank += goldCarried;
        goldCarried = 0;
    }

    public int getWealth() {
        return moneyInBank;
    }

    public void update() {
        thirst += 1;
        fsm.update();
    }
}
