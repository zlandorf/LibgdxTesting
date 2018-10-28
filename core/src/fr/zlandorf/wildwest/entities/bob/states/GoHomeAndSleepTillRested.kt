package fr.zlandorf.wildwest.entities.bob.states

import com.badlogic.gdx.ai.fsm.State
import com.badlogic.gdx.ai.msg.Telegram
import fr.zlandorf.wildwest.domain.Location
import fr.zlandorf.wildwest.entities.bob.Bob
import fr.zlandorf.wildwest.entities.bob.states.BobStates.ENTER_MINE_AND_DIG_FOR_NUGGET

class GoHomeAndSleepTillRested : State<Bob> {
    override fun enter(bob: Bob) {
        if (bob.location != Location.SHACK) {
            bob.talk("Walkin' home")
            bob.location = Location.SHACK
        }
    }

    override fun update(bob: Bob) {
        if (!bob.isFatigued) {
            bob.talk("All mah fatigue has drained away. Time to find more gold!")

            bob.fsm.changeState(ENTER_MINE_AND_DIG_FOR_NUGGET)
        } else {
            bob.decreaseFatigue()
            bob.talk("ZZZZ... ")
        }
    }

    override fun exit(bob: Bob) {

    }

    override fun onMessage(bob: Bob, telegram: Telegram): Boolean {
        return false
    }
}
