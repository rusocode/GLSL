package com.craivet.render;

import com.craivet.*;

import java.nio.FloatBuffer;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL30.*;

/**
 * Carga modelos 3D en memoria.
 * <p>
 * El renderizado con Index Buffer (buffer de indices) es una tecnica utilizada en graficos por computadora para optimizar la
 * representacion de geometria 3D. Esta tecnica se aplica especialmente al utilizar primitivas graficas como triangulos.
 * <p>
 * En el contexto de OpenGL o Direct3D, el Index Buffer es un tipo de buffer que almacena indices que apuntan a los vertices en un
 * Vertex Buffer Object (VBO). En lugar de enviar cada vertice de forma independiente, se utilizan indices para referenciar a los
 * vertices en el VBO, permitiendo la reutilizacion de vertices comunes entre multiples triangulos.
 * <p>
 * El proceso general del renderizado con Index Buffer implica los siguientes pasos:
 * <ol>
 * <li><b>Creacion de Vertex Buffer Object (VBO):</b>
 * Se almacenan los datos de los vertices, como posiciones, normales y coordenadas de textura, en un buffer de vertices.
 * <li><b>Creacion de Index Buffer:</b>
 * Se crea un buffer de indices que contiene informacion sobre como se conectan los vertices para formar triangulos.
 * <li><b>Envio de Datos a la GPU:</b>
 * Ambos buffers (VBO e Index Buffer) se envian a la GPU.
 * <li><b>Proceso de Renderizado:</b>
 * Durante el proceso de renderizado, la GPU utiliza los datos del Index Buffer para determinar como los vertices deben ser
 * ensamblados en triangulos.
 * <li><b>Optimizacion de Uso de Memoria:</b>
 * La principal ventaja del uso de Index Buffer es la optimizacion del uso de memoria, ya que permite la reutilizacion de vertices
 * compartidos por multiples triangulos, reduciendo asi la cantidad de datos que se deben enviar y almacenar.
 * </ol>
 * Esta tecnica es eficaz para modelos 3D con geometria repetitiva, ya que reduce la redundancia en los datos de vertices y mejora
 * la eficiencia en la representacion de modelos complejos.
 */

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

    /**
     * Elimina todos los vaos y vbos.
     */
    public void clean() {
        /* Elimina los vaos previamente creados. Al hacerlo, se liberan los recursos asociados al objeto, liberando memoria y
         * recursos de la GPU. Es crucial desvincular el vao antes de eliminarlo utilizando glBindVertexArray(0) para evitar
         * posibles problemas. Este mismo enfoque se aplica a los metodos glDeleteBuffers() y glDeleteTextures(). */
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
        /* Carga los datos en el buffer vinculado. Los datos se cargan desde el objeto buffer y se indican como datos estaticos
         * para dibujar, lo que permite a OpenGL realizar optimizaciones para un mejor rendimiento. */
        glBufferData(GL_ARRAY_BUFFER, buffer, GL_STATIC_DRAW);
        /* Esta linea configura el atributo de vertice 0 (posicion de vertices) para que use 2 componentes de punto flotante (x, y)
         * por vertice, sin normalizacion, con los datos empaquetados uno despues del otro en el buffer de vertices, comenzando
         * desde el inicio del buffer. Esta configuracion se aplicara si el vbo (Array Buffer Object) esta habilitado. */
        glVertexAttribPointer(0, 2, GL_FLOAT, false, 0, 0);
        glBindBuffer(GL_ARRAY_BUFFER, 0); // Desvincula el vbo
        glBindVertexArray(0); // Es importante desvincular el vao despues de configurarlo o antes de configurar otro vao diferente
    }

}
