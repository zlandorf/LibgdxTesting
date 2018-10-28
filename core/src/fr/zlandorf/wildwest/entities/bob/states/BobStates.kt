package fr.zlandorf.wildwest.entities.bob.states

import com.badlogic.gdx.ai.fsm.State
import fr.zlandorf.wildwest.entities.bob.Bob

object BobStates {
    val ENTER_MINE_AND_DIG_FOR_NUGGET: State<Bob> = EnterMineAndDigForGoldNugget()
    val GO_HOME_AND_SLEEP_TILL_RESTED: State<Bob> = GoHomeAndSleepTillRested()
    val VISIT_BANK_AND_DEPOSIT_GOLD: State<Bob> = VisitBankAndDepositGold()
    val EAT_STEW: State<Bob> = EatStew()
}
