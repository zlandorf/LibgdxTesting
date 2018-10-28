package fr.zlandorf.wildwest.entities.elsa.states

import com.badlogic.gdx.ai.fsm.State
import com.badlogic.gdx.ai.msg.MessageManager
import com.badlogic.gdx.ai.msg.Telegram
import fr.zlandorf.wildwest.components.elsaMapper
import fr.zlandorf.wildwest.entities.bob
import fr.zlandorf.wildwest.entities.elsa.Elsa
import fr.zlandorf.wildwest.entities.elsa.states.ElsaStates.DO_HOUSE_WORK
import fr.zlandorf.wildwest.messaging.Messages

class CookStew : State<Elsa> {
    override fun update(elsa: Elsa?) {
        elsa?.talk("Cookin' mah stew")
    }

    override fun enter(elsa: Elsa?) {
        if (elsa == null) return

        if (!elsaMapper.get(elsa).isCooking) {
            elsa.talk("Ima start cookin'")
            MessageManager.getInstance().dispatchMessage(
                    1.5f,
                    elsa,
                    elsa,
                    Messages.STEW_READY
            )
            elsaMapper.get(elsa).isCooking = true
        }
    }

    override fun exit(elsa: Elsa?) {
        elsa?.talk("Puttin' the stew on the table")
    }

    override fun onMessage(elsa: Elsa?, telegram: Telegram?): Boolean {
        if (telegram?.message == Messages.STEW_READY) {
            elsa?.run {
                talk("Stew's ready")
                MessageManager.getInstance().dispatchMessage(
                        0f,
                        elsa,
                        bob,
                        Messages.STEW_READY
                )
                elsaMapper.get(elsa).isCooking = false
                fsm.changeState(DO_HOUSE_WORK)
                return true
            }
        }
        return false
    }
}