package com.craivet.render;

import com.craivet.RawModel;
import com.craivet.shaders.Shader;

import static com.craivet.render.DisplayManager.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL30.*;

public class Renderer {

    private final Shader shader;
    private final RawModel quad;

    private static final String FRAGMENT_FILE = "src/main/java/com/craivet/shaders/FragmentShader.glsl";

    private float time;

    public Renderer(Loader loader) {
        shader = new Shader(FRAGMENT_FILE);
        // Define los vertices (en este caso, solo necesitamos 4 vertices para cubrir toda la pantalla)
        float[] vertices = {
                -1.0f, -1.0f, // Vertice inferior izquierdo
                1.0f, -1.0f, // Vertice inferior derecho
                1.0f, 1.0f, // Vertice superior derecho
                -1.0f, 1.0f  // Vertice superior izquierdo
        };
        quad = loader.loadToVAO(vertices);
    }

    public void render() {
        prepare();
        shader.start();
        shader.loadResolution(WIDTH, HEIGHT);
        time += DisplayManager.getFrameTimeSeconds();
        shader.loadTime(time);
        glBindVertexArray(quad.getVaoID());
        glEnableVertexAttribArray(0);
        /* Renderiza el cuadrado que cubre toda la pantalla. Tecnicamente renderiza tres triangulos en forma de abanico utilizando
         * los primeros cuatro vertices del buffer de vertices actual. */
        glDrawArrays(GL_TRIANGLE_FAN, 0, 4); // TODO Reemplazar el numero magico 4 por la cantidad de vertices = vertices.length / dimensions
        glDisableVertexAttribArray(0); // Deshabilita el atributo 0
        glBindVertexArray(0);
        shader.stop();
    }

    public void clean() {
        shader.clean();
    }

    private void prepare() {
        glClearColor(0, 0, 0, 1); // Establece el color de limpieza a negro opaco
        /* Limpia el buffer de color utilizando el color establecido previamente con glClearColor() (si es que se especifico un
         * color). Si no se establece un color previamente, OpenGL utilizara un color predeterminado (normalmente, negro). Seria
         * una buena opcion utilizar el color por defecto para evitar agregar una linea. */
        glClear(GL_COLOR_BUFFER_BIT);
    }

}
