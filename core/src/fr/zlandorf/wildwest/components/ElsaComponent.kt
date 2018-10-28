package fr.zlandorf.wildwest.components

import com.badlogic.ashley.core.Component

data class ElsaComponent(
        var isCooking: Boolean = false
) : Component