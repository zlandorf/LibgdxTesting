package fr.zlandorf.wildwest.components

import com.badlogic.ashley.core.Component
import com.badlogic.gdx.ai.fsm.State
import com.badlogic.gdx.ai.fsm.StateMachine
import fr.zlandorf.wildwest.entities.bob.Bob

data class BobComponent(
        var fsm: StateMachine<Bob, State<Bob>>
) : Component