package com.craivet.render;

import com.craivet.RawModel;
import com.craivet.shaders.Shader;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL30.*;

public class Renderer {

    private final RawModel quad;
    private final Shader shader;

    private static final String FRAGMENT_FILE = "src/main/java/com/craivet/shaders/FragmentShader.glsl";

    public Renderer(Loader loader) {
        // Define los vertices (en este caso, solo necesitamos 4 vertices para cubrir toda la pantalla)
        float[] vertices = {
                -1.0f, -1.0f, // Vertice inferior izquierdo
                1.0f, -1.0f, // Vertice inferior derecho
                1.0f, 1.0f, // Vertice superior derecho
                -1.0f, 1.0f  // Vertice superior izquierdo
        };
        quad = loader.loadToVAO(vertices);
        shader = new Shader(FRAGMENT_FILE);
    }

    public void render() {
        prepare();
        shader.start();
        glBindVertexArray(quad.getVaoID()); // Vincula el objeto vao especificado por el id
        glEnableVertexAttribArray(0); // Habilita el atributo 0 que hace referencia a la posicion de los vertices ya que viene deshabilitado por defecto
        /* Renderiza el cuadrado que cubre toda la pantalla. Tecnicamente renderiza tres triangulos en forma de abanico utilizando
         * los primeros cuatro vertices del buffer de vertices actual. */
        glDrawArrays(GL_TRIANGLE_FAN, 0, 4); // TODO Reemplazar el numero magico 4 por la cantidad de vertices = vertices.length / dimensions
        glDisableVertexAttribArray(0); // Deshabilita el atributo 0
        glBindVertexArray(0); // Desvincula el vao
        shader.stop();
    }

    public void clean() {
        shader.clean();
    }

    /**
     * Se llama una vez en cada fotograma y simplemente prepara a OpenGL para renderizar el siguiente frame.
     */
    private void prepare() {
        glClearColor(0, 0, 0, 1); // Establece el color de limpieza a negro opaco
        /* Limpia el buffer de color utilizando el color establecido previamente con glClearColor() (si es que se especifico un
         * color). Si no se establece un color previamente, OpenGL utilizara un color predeterminado (normalmente, negro). Seria
         * una buena opcion utilizar el color por defecto para evitar agregar una linea. */
        glClear(GL_COLOR_BUFFER_BIT);
    }

}
