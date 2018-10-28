package fr.zlandorf.wildwest.entities.bob.states

import com.badlogic.gdx.ai.fsm.State
import com.badlogic.gdx.ai.msg.Telegram
import fr.zlandorf.wildwest.components.bobMapper
import fr.zlandorf.wildwest.domain.Location
import fr.zlandorf.wildwest.entities.bob.Bob

class VisitBankAndDepositGold : State<Bob> {
    override fun enter(bob: Bob) {
        val bobComponent = bobMapper.get(bob)
        if (bobComponent.location != Location.BANK) {
            bob.talk("Goin' to the bank. Yes siree")
            bobComponent.location = Location.BANK
        }
    }

    override fun update(bob: Bob) {
        val bobComponent = bobMapper.get(bob)
        bobComponent.wealth += bobComponent.goldCarried
        bobComponent.goldCarried = 0
        bob.talk("Depositing gold. Total savings now: " + bobComponent.wealth)
        bob.fsm.revertToPreviousState()
    }

    override fun exit(bob: Bob) {
        bob.talk("Leavin' the bank")
    }

    override fun onMessage(bob: Bob, telegram: Telegram): Boolean {
        return false
    }
}
