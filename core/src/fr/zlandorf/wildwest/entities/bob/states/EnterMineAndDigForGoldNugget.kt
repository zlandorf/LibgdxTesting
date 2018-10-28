package fr.zlandorf.wildwest.entities.bob.states

import com.badlogic.gdx.ai.fsm.State
import com.badlogic.gdx.ai.msg.Telegram
import fr.zlandorf.wildwest.components.BobComponent
import fr.zlandorf.wildwest.components.bobMapper
import fr.zlandorf.wildwest.domain.Location
import fr.zlandorf.wildwest.entities.bob.Bob
import fr.zlandorf.wildwest.entities.bob.states.BobStates.VISIT_BANK_AND_DEPOSIT_GOLD

private const val MAX_GOLD_CARRIED = 3

private fun Bob.arePocketsFull() = bobMapper.get(this).goldCarried >= MAX_GOLD_CARRIED

class EnterMineAndDigForGoldNugget : State<Bob> {

    override fun enter(bob: Bob) {
        val bobComponent = bobMapper.get(bob)
        if (bobComponent.location != Location.GOLD_MINE) {
            bob.talk("Walkin' to the goldmine")
            bobComponent.location = Location.GOLD_MINE
        }
    }

    override fun update(bob: Bob) {
        bob.talk("Pickin' up a nugget")

        val bobComponent = bobMapper.get(bob)
        bobComponent.goldCarried += 1
        bobComponent.fatigue += 1

        if (bob.arePocketsFull()) {
            bob.fsm.changeState(VISIT_BANK_AND_DEPOSIT_GOLD)
        }
    }

    override fun exit(bob: Bob) {
        bob.talk("Ah'm leavin' the goldmine with mah pockets full o' sweet gold")
    }

    override fun onMessage(bob: Bob, telegram: Telegram): Boolean {
        return false
    }
}
