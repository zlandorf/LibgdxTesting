package fr.zlandorf.wildwest.entities.bob.states

import com.badlogic.gdx.ai.fsm.State
import com.badlogic.gdx.ai.msg.Telegram
import fr.zlandorf.wildwest.entities.bob.Bob

class EatStew: State<Bob> {
    override fun update(bob: Bob?) {
        bob?.run {
            talk("Tastes real good too!")
            fsm.revertToPreviousState()
        }
    }

    override fun enter(bob: Bob?) {
        bob?.talk("Smells real good Elsa!")
    }

    override fun exit(bob: Bob?) {
        bob?.run {
            talk("Thankya li'lle lady. Ah better get back to whatever ah wuz doin'")
        }
    }

    override fun onMessage(bob: Bob?, telegram: Telegram?) = false
}