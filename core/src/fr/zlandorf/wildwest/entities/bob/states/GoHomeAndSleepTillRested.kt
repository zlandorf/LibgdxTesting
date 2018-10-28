package fr.zlandorf.wildwest.entities.bob.states

import com.badlogic.gdx.ai.fsm.State
import com.badlogic.gdx.ai.msg.MessageManager
import com.badlogic.gdx.ai.msg.Telegram
import fr.zlandorf.wildwest.components.bobMapper
import fr.zlandorf.wildwest.domain.Location
import fr.zlandorf.wildwest.entities.bob.Bob
import fr.zlandorf.wildwest.entities.bob.states.BobStates.EAT_STEW
import fr.zlandorf.wildwest.entities.bob.states.BobStates.ENTER_MINE_AND_DIG_FOR_NUGGET
import fr.zlandorf.wildwest.entities.elsa
import fr.zlandorf.wildwest.messaging.Messages

private const val THRESHOLD_TIREDNESS = 5
private fun Bob.isFatigued() = bobMapper.get(this).fatigue >= THRESHOLD_TIREDNESS

class GoHomeAndSleepTillRested : State<Bob> {

    override fun enter(bob: Bob?) {
        if (bob == null) return

        val bobComponent = bobMapper.get(bob)
        if (bobComponent.location != Location.SHACK) {
            bob.talk("Walkin' home")
            bobComponent.location = Location.SHACK

            MessageManager.getInstance().dispatchMessage(
                    0f,
                    bob,
                    elsa,
                    Messages.HI_HONEY_IM_HOME,
                    null
            )
        }
    }

    override fun update(bob: Bob?) {
        if (bob == null) return

        val bobComponent = bobMapper.get(bob)
        if (!bob.isFatigued()) {
            bob.talk("All mah fatigue has drained away. Time to find more gold!")
            bob.fsm.changeState(ENTER_MINE_AND_DIG_FOR_NUGGET)
        } else {
            bobComponent.fatigue--
            bob.talk("ZZZZ... ")
        }
    }

    override fun exit(bob: Bob?) {
    }

    override fun onMessage(bob: Bob?, telegram: Telegram?): Boolean {
        if (telegram?.message == Messages.STEW_READY) {
            bob?.run {
                talk("Okay Hun, ahm a comin'!")
                fsm.changeState(EAT_STEW)
                return true
            }
        }
        return false
    }
}
