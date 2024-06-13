package com.craivet.shaders;

import javax.swing.*;
import java.io.*;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL20.*;

public class Shader {

    private final int programID, fragmentShaderID;

    private int resolutionUniformLocation;
    private int timeUniformLocation;

    public Shader(String fragmentFile) {
        fragmentShaderID = loadShader(fragmentFile, GL_FRAGMENT_SHADER);
        programID = glCreateProgram();
        glAttachShader(programID, fragmentShaderID);
        glLinkProgram(programID);
        glValidateProgram(programID);
        getAllUniformLocations();
    }

    public void getAllUniformLocations() {
        resolutionUniformLocation = getUniformLocation("resolution");
        timeUniformLocation = getUniformLocation("time");
    }

    public void start() {
        glUseProgram(programID);
    }

    public void stop() {
        glUseProgram(0);
    }

    public void clean() {
        stop();
        glDetachShader(programID, fragmentShaderID);
        glDeleteShader(fragmentShaderID);
        glDeleteProgram(programID);
    }

    /**
     * Carga las dimensiones de la resolucion en el vector uniforme del shader.
     *
     * @param w ancho de la resolucion.
     * @param h alto de la resolucion.
     */
    public void loadResolution(int w, int h) {
        glUniform2f(resolutionUniformLocation, w, h);
    }

    /**
     * Carga el tiempo a la uniforme de tipo float.
     *
     * @param time tiempo desde que se inicio la aplicacion.
     */
    public void loadTime(float time) {
        glUniform1f(timeUniformLocation, time);
    }

    private int getUniformLocation(String name) {
        return glGetUniformLocation(programID, name);
    }

    private static int loadShader(String file, int type) {
        StringBuilder shaderSource = new StringBuilder();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = reader.readLine()) != null)
                shaderSource.append(line).append("\n");
            reader.close();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error de I/O", "Error", JOptionPane.ERROR_MESSAGE);
        }

        int shaderID = glCreateShader(type);
        glShaderSource(shaderID, shaderSource);
        glCompileShader(shaderID);
        if (glGetShaderi(shaderID, GL_COMPILE_STATUS) == GL_FALSE) {
            System.out.println(glGetShaderInfoLog(shaderID, 500));
            System.err.println("Could not compile shader!");
            System.exit(-1);
        }
        return shaderID;
    }

}
