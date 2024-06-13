package com.craivet;

import java.util.ArrayList;
import java.util.List;

import static org.lwjgl.opengl.GL30.*;

public class Vao {

    private final List<Integer> vaos = new ArrayList<>();

    public int init() {
        int id = glGenVertexArrays();
        vaos.add(id);
        glBindVertexArray(id);
        return id;
    }

    public List<Integer> getVaos() {
        return vaos;
    }

}
