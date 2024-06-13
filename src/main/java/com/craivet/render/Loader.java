package com.craivet.render;

import com.craivet.*;

import java.nio.FloatBuffer;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL30.*;

public class Loader {

    private final Vao vao;
    private final Vbo vbo;

    public Loader() {
        vao = new Vao();
        vbo = new Vbo();
    }

    /**
     * Carga los objetos que estan compuestos solo por vertices, como guis.
     *
     * @param vertices array de vertices.
     * @return el modelo sin procesar.
     */
    public RawModel loadToVAO(float[] vertices) {
        int id = vao.init();
        vbo.init();
        loadVerticesIntoVAO(vertices);
        return new RawModel(id);
    }

    public void clean() {
        for (int vao : vao.getVaos()) glDeleteVertexArrays(vao);
        for (int vbo : vbo.getVbos()) glDeleteBuffers(vbo);
    }

    /**
     * Carga los vertices en el vao.
     *
     * @param vertices array de vertices.
     */
    private void loadVerticesIntoVAO(float[] vertices) {
        FloatBuffer buffer = Utils.storeVerticesInBuffer(vertices);
        glBufferData(GL_ARRAY_BUFFER, buffer, GL_STATIC_DRAW);
        glVertexAttribPointer(0, 2, GL_FLOAT, false, 0, 0);
        glBindBuffer(GL_ARRAY_BUFFER, 0);
        glBindVertexArray(0);
    }

}
