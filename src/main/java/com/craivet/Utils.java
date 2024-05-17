package com.craivet;

import java.nio.FloatBuffer;

import org.lwjgl.BufferUtils;

public final class Utils {

    private Utils() {
    }

    /**
     * Almacena los vertices en un buffer de enteros.
     *
     * @param vertices array de vertices.
     * @return un buffer de vertices.
     */
    public static FloatBuffer storeVerticesInBuffer(float[] vertices) {
        FloatBuffer buffer = BufferUtils.createFloatBuffer(vertices.length);
        buffer.put(vertices);
        buffer.flip();
        return buffer;
    }

}
