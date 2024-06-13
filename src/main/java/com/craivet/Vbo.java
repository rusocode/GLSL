package com.craivet;

import java.util.ArrayList;
import java.util.List;

import static org.lwjgl.opengl.GL15.*;

public class Vbo {

    private final List<Integer> vbos = new ArrayList<>();

    public void init() {
        int id = glGenBuffers();
        vbos.add(id);
        glBindBuffer(GL_ARRAY_BUFFER, id);
    }

    public List<Integer> getVbos() {
        return vbos;
    }

}
