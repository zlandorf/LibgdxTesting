package fr.zlandorf.wildwest.entities.elsa.states

import com.badlogic.gdx.ai.fsm.State
import fr.zlandorf.wildwest.entities.elsa.Elsa

object ElsaStates {
    val GLOBAL_STATE: State<Elsa> = GlobalState()
    val DO_HOUSE_WORK: State<Elsa> = DoHouseWork()
    val COOK_STEW: State<Elsa> = CookStew()
}