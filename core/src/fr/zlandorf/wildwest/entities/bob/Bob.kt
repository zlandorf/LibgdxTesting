package fr.zlandorf.wildwest.entities.bob

import com.badlogic.ashley.core.Entity
import com.badlogic.gdx.ai.GdxAI
import com.badlogic.gdx.ai.fsm.DefaultStateMachine
import com.badlogic.gdx.ai.fsm.State
import com.badlogic.gdx.ai.fsm.StateMachine
import com.badlogic.gdx.ai.msg.Telegram
import com.badlogic.gdx.ai.msg.Telegraph
import fr.zlandorf.wildwest.components.BobComponent
import fr.zlandorf.wildwest.components.FSMComponent
import fr.zlandorf.wildwest.components.fsmMapper
import fr.zlandorf.wildwest.entities.bob.states.BobStates.GO_HOME_AND_SLEEP_TILL_RESTED

class Bob : Entity(), Telegraph {

    @Suppress("UNCHECKED_CAST")
    val fsm: StateMachine<Bob, State<Bob>> get() = fsmMapper.get(this).fsm as StateMachine<Bob, State<Bob>>

    init {
        add(BobComponent())
        add(FSMComponent(DefaultStateMachine(this, GO_HOME_AND_SLEEP_TILL_RESTED)))
    }

    override fun handleMessage(msg: Telegram): Boolean {
        println(String.format("Received message %s from %s", msg.message, msg.sender))
        return true
    }

    fun talk(message: String) {
        logger.info(javaClass.simpleName, message)
    }

    companion object {
        private val logger = GdxAI.getLogger()
    }

}
