package fr.zlandorf.wildwest.entities.elsa

import com.badlogic.ashley.core.Entity
import com.badlogic.gdx.ai.GdxAI
import com.badlogic.gdx.ai.fsm.DefaultStateMachine
import com.badlogic.gdx.ai.fsm.State
import com.badlogic.gdx.ai.fsm.StateMachine
import com.badlogic.gdx.ai.msg.Telegram
import com.badlogic.gdx.ai.msg.Telegraph
import fr.zlandorf.wildwest.components.ElsaComponent
import fr.zlandorf.wildwest.components.FSMComponent
import fr.zlandorf.wildwest.components.fsmMapper
import fr.zlandorf.wildwest.entities.elsa.states.ElsaStates.DO_HOUSE_WORK
import fr.zlandorf.wildwest.entities.elsa.states.ElsaStates.GLOBAL_STATE

class Elsa : Entity(), Telegraph {

    @Suppress("UNCHECKED_CAST")
    val fsm: StateMachine<Elsa, State<Elsa>>
        get() = fsmMapper.get(this).fsm as StateMachine<Elsa, State<Elsa>>

    init {
        add(ElsaComponent())
        add(FSMComponent(DefaultStateMachine(this, DO_HOUSE_WORK, GLOBAL_STATE)))
    }

    override fun handleMessage(msg: Telegram): Boolean {
        println(String.format("Received message %s from %s", msg.message, msg.sender))
        return fsm.handleMessage(msg)
    }

    fun talk(message: String) {
        logger.info(javaClass.simpleName, message)
    }

    companion object {
        private val logger = GdxAI.getLogger()
    }

}
