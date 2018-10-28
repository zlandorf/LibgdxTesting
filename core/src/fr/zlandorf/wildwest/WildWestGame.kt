package fr.zlandorf.wildwest

import com.badlogic.ashley.core.Engine
import com.badlogic.gdx.ApplicationAdapter
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import fr.zlandorf.wildwest.entities.bob.Bob
import fr.zlandorf.wildwest.systems.BobSystem

class WildWestGame : ApplicationAdapter() {
    private var batch: SpriteBatch? = null
    private var img: Texture? = null

    private val engine = Engine()

    override fun create() {
        batch = SpriteBatch()
        img = Texture("badlogic.jpg")

        engine.addSystem(BobSystem())
        engine.addEntity(Bob())
    }

    override fun render() {
        Gdx.gl.glClearColor(1f, 0f, 0f, 1f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)
        batch?.apply {
            begin()
            draw(img, 0f, 0f)
            end()
        }
        engine.update(Gdx.graphics.deltaTime)
    }

    override fun dispose() {
        batch?.dispose()
        img?.dispose()
    }
}
