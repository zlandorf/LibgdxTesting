package fr.zlandorf.wildwest.entities.bob.states

import com.badlogic.gdx.ai.fsm.State
import com.badlogic.gdx.ai.msg.Telegram
import fr.zlandorf.wildwest.components.bobMapper
import fr.zlandorf.wildwest.domain.Location
import fr.zlandorf.wildwest.entities.bob.Bob
import fr.zlandorf.wildwest.entities.bob.states.BobStates.ENTER_MINE_AND_DIG_FOR_NUGGET

class GoHomeAndSleepTillRested : State<Bob> {
    override fun enter(bob: Bob) {
        val bobComponent = bobMapper.get(bob)
        if (bobComponent.location != Location.SHACK) {
            bob.talk("Walkin' home")
            bobComponent.location = Location.SHACK
        }
    }

    override fun update(bob: Bob) {
        val bobComponent = bobMapper.get(bob)
        if (!bobComponent.isFatigued) {
            bob.talk("All mah fatigue has drained away. Time to find more gold!")

            bob.fsm.changeState(ENTER_MINE_AND_DIG_FOR_NUGGET)
        } else {
            bobComponent.fatigue--
            bob.talk("ZZZZ... ")
        }
    }

    override fun exit(bob: Bob) {

    }

    override fun onMessage(bob: Bob, telegram: Telegram): Boolean {
        return false
    }
}
