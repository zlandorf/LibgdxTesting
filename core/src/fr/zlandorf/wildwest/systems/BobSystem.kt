package fr.zlandorf.wildwest.systems

import com.badlogic.ashley.core.Entity
import com.badlogic.ashley.core.Family
import com.badlogic.ashley.systems.IteratingSystem
import fr.zlandorf.wildwest.components.BobComponent
import fr.zlandorf.wildwest.components.bobMapper

class BobSystem : IteratingSystem(
        Family.all(
                BobComponent::class.java
        ).get()
) {

    override fun processEntity(entity: Entity?, deltaTime: Float) {
        entity?.let {
            val bob = bobMapper.get(it)

            bob.fsm.update()
        }
    }

}