package fr.zlandorf.wildwest.entities.elsa.states

import com.badlogic.gdx.ai.fsm.State
import com.badlogic.gdx.ai.msg.Telegram
import fr.zlandorf.wildwest.entities.elsa.Elsa

class DoHouseWork : State<Elsa> {
    override fun update(elsa: Elsa?) {
        elsa?.talk("Moppin' the floor")
    }

    override fun enter(elsa: Elsa?) {
    }

    override fun exit(elsa: Elsa?) {
    }

    override fun onMessage(elsa: Elsa?, telegram: Telegram?): Boolean {
        return false
    }

}