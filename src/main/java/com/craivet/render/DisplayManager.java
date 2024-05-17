package com.craivet.render;

import javax.swing.*;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.*;

/**
 * Administra la visualizacion de la ventana.
 */

public class DisplayManager {

    public static final int WIDTH = 800, HEIGHT = 600;
    private static final int FPS = 120;

    public static void create() {

        try {
            Display.setTitle("GLSL");
            Display.setDisplayMode(new DisplayMode(WIDTH, HEIGHT));
            Display.create(new PixelFormat());
        } catch (LWJGLException e) {
            JOptionPane.showMessageDialog(null, "Error", e.getMessage(), JOptionPane.ERROR_MESSAGE);
            Display.destroy();
            System.exit(1);
        }

        GL11.glViewport(0, 0, WIDTH, HEIGHT);
    }

    public static void update() {
        Display.sync(FPS);
        Display.update();
    }

    public static void close() {
        Display.destroy();
    }

}
