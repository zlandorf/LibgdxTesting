package fr.zlandorf.wildwest

import com.badlogic.ashley.core.Engine
import com.badlogic.gdx.ApplicationAdapter
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.ai.GdxAI
import com.badlogic.gdx.ai.msg.MessageManager
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import fr.zlandorf.wildwest.entities.bob
import fr.zlandorf.wildwest.entities.elsa
import fr.zlandorf.wildwest.systems.FSMSystem

class WildWestGame : ApplicationAdapter() {
    private var batch: SpriteBatch? = null
    private var img: Texture? = null

    private val engine = Engine()

    override fun create() {
        batch = SpriteBatch()
        img = Texture("badlogic.jpg")

        engine.addSystem(FSMSystem())
        engine.addEntity(bob)
        engine.addEntity(elsa)
    }

    override fun render() {
        val deltaTime = Gdx.graphics.deltaTime

        Gdx.gl.glClearColor(1f, 0f, 0f, 1f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)
        batch?.apply {
            begin()
            draw(img, 0f, 0f)
            end()
        }

        engine.update(deltaTime)
        GdxAI.getTimepiece().update(deltaTime)
        MessageManager.getInstance().update()
    }

    override fun dispose() {
        batch?.dispose()
        img?.dispose()
    }
}
