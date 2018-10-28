package fr.zlandorf.wildwest.components

import com.badlogic.ashley.core.Component
import com.badlogic.gdx.ai.fsm.State
import com.badlogic.gdx.ai.fsm.StateMachine

data class FSMComponent<T>(
        val fsm: StateMachine<T, State<T>>
) : Component

