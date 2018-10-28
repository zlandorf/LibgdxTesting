package fr.zlandorf.wildwest.components

import com.badlogic.ashley.core.Component
import fr.zlandorf.wildwest.domain.Location

const val THRESHOLD_TIREDNESS = 5
const val MAX_GOLD_CARRIED = 3

data class BobComponent(
    var location: Location = Location.SHACK,
    var goldCarried: Int = 0,
    var fatigue: Int  = 0,
    var wealth: Int  = 0
) : Component {

    val isFatigued: Boolean
        get() = fatigue > THRESHOLD_TIREDNESS

    val arePocketsFull: Boolean
        get() = goldCarried >= MAX_GOLD_CARRIED
}