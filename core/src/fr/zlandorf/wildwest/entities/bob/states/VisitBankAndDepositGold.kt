package fr.zlandorf.wildwest.entities.bob.states

import com.badlogic.gdx.ai.fsm.State
import com.badlogic.gdx.ai.msg.Telegram
import fr.zlandorf.wildwest.components.bobMapper
import fr.zlandorf.wildwest.domain.Location
import fr.zlandorf.wildwest.entities.bob.Bob
import fr.zlandorf.wildwest.entities.bob.states.BobStates.ENTER_MINE_AND_DIG_FOR_NUGGET
import fr.zlandorf.wildwest.entities.bob.states.BobStates.GO_HOME_AND_SLEEP_TILL_RESTED

private const val COMFORT_LEVEL = 5
private fun Bob.isWealthyEnough() = bobMapper.get(this).wealth >= COMFORT_LEVEL

class VisitBankAndDepositGold : State<Bob> {
    override fun enter(bob: Bob?) {
        if (bob == null) return

        val bobComponent = bobMapper.get(bob)
        if (bobComponent.location != Location.BANK) {
            bob.talk("Goin' to the bank. Yes siree")
            bobComponent.location = Location.BANK
        }
    }

    override fun update(bob: Bob?) {
        if (bob == null) return

        val bobComponent = bobMapper.get(bob)
        bobComponent.wealth += bobComponent.goldCarried
        bobComponent.goldCarried = 0
        bob.talk("Depositing gold. Total savings now: " + bobComponent.wealth)

        if (bob.isWealthyEnough()) {
            bob.talk("WooHoo! Rich enough for now. Back home to mah li'lle lady")
            bob.fsm.changeState(GO_HOME_AND_SLEEP_TILL_RESTED)
        } else {
            bob.fsm.changeState(ENTER_MINE_AND_DIG_FOR_NUGGET)
        }
    }

    override fun exit(bob: Bob?) {
        bob?.talk("Leavin' the bank")
    }

    override fun onMessage(bob: Bob?, telegram: Telegram?): Boolean {
        return false
    }
}
