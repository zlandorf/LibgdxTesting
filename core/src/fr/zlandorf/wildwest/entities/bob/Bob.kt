package fr.zlandorf.wildwest.entities.bob

import com.badlogic.ashley.core.Entity
import com.badlogic.gdx.ai.GdxAI
import com.badlogic.gdx.ai.fsm.DefaultStateMachine
import com.badlogic.gdx.ai.fsm.State
import com.badlogic.gdx.ai.fsm.StateMachine
import com.badlogic.gdx.ai.msg.Telegram
import com.badlogic.gdx.ai.msg.Telegraph
import fr.zlandorf.wildwest.domain.Location
import fr.zlandorf.wildwest.components.BobComponent
import fr.zlandorf.wildwest.components.bobMapper
import fr.zlandorf.wildwest.entities.bob.states.BobStates.GO_HOME_AND_SLEEP_TILL_RESTED

class Bob : Entity(), Telegraph {

    var location = Location.SHACK
    private var goldCarried = 0
    private var fatigue = 0
    var wealth = 0
        private set

    val isFatigued: Boolean
        get() = fatigue > THRESHOLD_TIREDNESS

    val arePocketsFull: Boolean
        get() = goldCarried >= MAX_GOLD_CARRIED

    val fsm: StateMachine<Bob, State<Bob>> get() = bobMapper.get(this).fsm

    init {
        add(BobComponent(DefaultStateMachine(this, GO_HOME_AND_SLEEP_TILL_RESTED)))
    }

    override fun handleMessage(msg: Telegram): Boolean {
        println(String.format("Received message %s from %s", msg.message, msg.sender))
        return true
    }

    fun talk(message: String) {
        logger.info(javaClass.simpleName, message)
    }

    fun decreaseFatigue() {
        fatigue--
    }

    fun increaseFatigue() {
        fatigue++
    }

    fun addGold(count: Int) {
        goldCarried += count
    }

    fun addGoldCarriedToWealth() {
        wealth += goldCarried
        goldCarried = 0
    }



    companion object {

        private val logger = GdxAI.getLogger()

        const val THRESHOLD_TIREDNESS = 5

        const val MAX_GOLD_CARRIED = 3
    }

}
