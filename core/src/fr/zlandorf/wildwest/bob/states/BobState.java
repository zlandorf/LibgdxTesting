package fr.zlandorf.wildwest.bob.states;

import com.badlogic.gdx.ai.fsm.State;
import fr.zlandorf.wildwest.bob.Bob;

public interface BobState {
    State<Bob> ENTER_MINE_AND_DIG_FOR_NUGGET = new EnterMineAndDigForGoldNugget();
    State<Bob> GO_HOME_AND_SLEEP_TILL_RESTED = new GoHomeAndSleepTillRested();
    State<Bob> VISIT_BANK_AND_DEPOSIT_GOLD = new VisitBankAndDepositGold();

}
