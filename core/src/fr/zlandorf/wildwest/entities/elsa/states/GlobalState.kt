package fr.zlandorf.wildwest.entities.elsa.states

import com.badlogic.gdx.ai.fsm.State
import com.badlogic.gdx.ai.msg.Telegram
import fr.zlandorf.wildwest.entities.elsa.Elsa
import fr.zlandorf.wildwest.entities.elsa.states.ElsaStates.COOK_STEW
import fr.zlandorf.wildwest.messaging.Messages

class GlobalState : State<Elsa> {

    override fun update(elsa: Elsa?) {
    }

    override fun enter(elsa: Elsa?) {
    }

    override fun exit(elsa: Elsa?) {
    }

    override fun onMessage(elsa: Elsa?, telegram: Telegram?): Boolean {
        if (telegram?.message == Messages.HI_HONEY_IM_HOME) {
            elsa?.run {
                talk("Hi honey. let me make you some of mah fine country stew")
                fsm.changeState(COOK_STEW)
                return true
            }
        }
        return false
    }

}