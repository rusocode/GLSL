package com.craivet;

import java.util.ArrayList;
import java.util.List;

import static org.lwjgl.opengl.GL30.*;

/**
 * Un Vertex Array Object (VAO) es un objeto de OpenGL que encapsula y almacena el estado de configuracion de los atributos de
 * vertice (posicion, normal, coordenadas de textura, etc.) y los buffers de vertices (Vertex Buffer Objects, VBO) asociados a
 * esos atributos. Esto permite cambiar rapidamente entre diferentes configuraciones de atributos de vertice y buffers sin tener
 * que volver a configurar todos los estados cada vez.
 */

public class Vao {

    private final List<Integer> vaos = new ArrayList<>();

    /**
     * Inicializa un objeto vao y lo vincula al contexto actual de OpenGL.
     *
     * @return el identificador del vao inicializado.
     */
    public int init() {
        // Genera un id para el vao (el primer id que genera es el entero 1)
        int id = glGenVertexArrays();
        vaos.add(id);
        /* Vincula el objeto vao identificado por el id al contexto actual de OpenGL, lo que permite configurar y utilizar los
         * atributos de vertice y los buffers de vertices asociados a ese vao. */
        glBindVertexArray(id);
        return id;
    }

    public List<Integer> getVaos() {
        return vaos;
    }

}
