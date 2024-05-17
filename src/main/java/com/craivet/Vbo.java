package com.craivet;

import java.util.ArrayList;
import java.util.List;

import static org.lwjgl.opengl.GL15.*;

/**
 * Un buffer en OpenGL es un area de memoria reservada en la GPU que se utiliza para almacenar datos como vertices, colores,
 * normales, coordenadas de textura, elementos de indices, etc. Cada buffer tiene un identificador unico que permite referenciarlo
 * y manipularlo.
 * <p>
 * La funcion {@code glBindBuffer()} toma dos parametros:
 * <ol>
 * <li>{@code target}: Especifica el tipo de buffer que se desea vincular. Los valores comunes son:
 * <ul>
 * <li><b>GL_ARRAY_BUFFER:</b> Para vincular un buffer de vertices (Vertex Buffer Object, VBO)
 * <li><b>GL_ELEMENT_ARRAY_BUFFER:</b> Para vincular un buffer de elementos de indices (Element Buffer Object, EBO)
 * </ul>
 * <li>{@code buffer}: Es el identificador del objeto de buffer que se desea vincular. Si el valor es 0, se desvincularan todos los buffers
 * actualmente vinculados para el target especificado.
 * </ol>
 * Una vez que un buffer esta vinculado, todas las operaciones posteriores relacionadas con ese tipo de buffer afectaran al buffer
 * vinculado. Por ejemplo, si se vincula un VBO (GL_ARRAY_BUFFER) y luego se llama a glBufferData(), los datos se cargaran en ese
 * VBO vinculado.
 * <p>
 * Es importante desvincular el buffer cuando se haya terminado de trabajar con el, vinculando un buffer con un identificador 0.
 * Esto evita que se modifiquen accidentalmente los datos del buffer en operaciones posteriores.
 */

public class Vbo {

    private final List<Integer> vbos = new ArrayList<>();

    /**
     * Inicializa un objeto vbo y lo vincula al vao actual.
     */
    public void init() {
        int id = glGenBuffers();
        vbos.add(id);
        // Vincula el vbo al vao actual
        glBindBuffer(GL_ARRAY_BUFFER, id);
    }

    public List<Integer> getVbos() {
        return vbos;
    }

}
