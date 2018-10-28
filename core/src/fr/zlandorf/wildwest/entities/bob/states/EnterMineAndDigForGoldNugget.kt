package fr.zlandorf.wildwest.entities.bob.states

import com.badlogic.gdx.ai.fsm.State
import com.badlogic.gdx.ai.msg.Telegram
import fr.zlandorf.wildwest.domain.Location
import fr.zlandorf.wildwest.entities.bob.Bob
import fr.zlandorf.wildwest.entities.bob.states.BobStates.VISIT_BANK_AND_DEPOSIT_GOLD

class EnterMineAndDigForGoldNugget : State<Bob> {

    override fun enter(bob: Bob) {
        if (bob.location != Location.GOLD_MINE) {
            bob.talk("Walkin' to the goldmine")
            bob.location = Location.GOLD_MINE
        }
    }

    override fun update(bob: Bob) {
        bob.talk("Pickin' up a nugget")
        bob.addGold(1)
        bob.increaseFatigue()

        if (bob.arePocketsFull) {
            bob.fsm.changeState(VISIT_BANK_AND_DEPOSIT_GOLD)
        }
    }

    override fun exit(bob: Bob) {
        bob.talk("Ah'm leavin' the goldmine with mah pockets full o' sweet gold")
    }

    override fun onMessage(bob: Bob, telegram: Telegram): Boolean {
        return false
    }
}