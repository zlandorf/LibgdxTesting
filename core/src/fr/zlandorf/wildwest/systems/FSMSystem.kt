package fr.zlandorf.wildwest.systems

import com.badlogic.ashley.core.Entity
import com.badlogic.ashley.core.Family
import com.badlogic.ashley.systems.IteratingSystem
import fr.zlandorf.wildwest.components.FSMComponent
import fr.zlandorf.wildwest.components.fsmMapper

class FSMSystem : IteratingSystem(
        Family.all(
                FSMComponent::class.java
        ).get()
) {

    private var elapsedTime : Float = 0f

    override fun processEntity(entity: Entity?, deltaTime: Float) {
        elapsedTime += deltaTime

        if (elapsedTime >= 0.8f) {
            entity?.let {
                fsmMapper.get(it).fsm.update()
            }
            elapsedTime = 0f
        }
    }

}