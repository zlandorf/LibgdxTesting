package fr.zlandorf.wildwest.components

import com.badlogic.ashley.core.ComponentMapper

val bobMapper = ComponentMapper.getFor(BobComponent::class.java) ?: throw IllegalAccessException()