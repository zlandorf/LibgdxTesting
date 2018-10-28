package fr.zlandorf.wildwest.components

import com.badlogic.ashley.core.ComponentMapper

val bobMapper = ComponentMapper.getFor(BobComponent::class.java) ?: throw IllegalAccessException()
val elsaMapper = ComponentMapper.getFor(ElsaComponent::class.java) ?: throw IllegalAccessException()
val fsmMapper = ComponentMapper.getFor(FSMComponent::class.java) ?: throw IllegalAccessException()
