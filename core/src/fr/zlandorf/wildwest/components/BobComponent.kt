package fr.zlandorf.wildwest.components

import com.badlogic.ashley.core.Component
import fr.zlandorf.wildwest.domain.Location

data class BobComponent(
    var location: Location = Location.SHACK,
    var goldCarried: Int = 0,
    var fatigue: Int  = 0,
    var wealth: Int  = 0
) : Component