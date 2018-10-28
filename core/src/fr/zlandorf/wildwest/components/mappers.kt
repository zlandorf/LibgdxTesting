package fr.zlandorf.wildwest.components

import com.badlogic.ashley.core.ComponentMapper
import com.badlogic.gdx.ai.fsm.State
import fr.zlandorf.wildwest.entities.bob.Bob

val bobMapper = ComponentMapper.getFor(BobComponent::class.java) ?: throw IllegalAccessException()
val fsmMapper = ComponentMapper.getFor(FSMComponent::class.java) ?: throw IllegalAccessException()
