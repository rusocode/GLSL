package com.craivet;

import com.craivet.render.*;

import org.lwjgl.opengl.Display;

public class Launcher {

    public static void main(String[] args) {

        DisplayManager.create();

        Loader loader = new Loader();
        Renderer renderer = new Renderer(loader);

        while (!Display.isCloseRequested()) {
            renderer.render();
            DisplayManager.update();
        }

        // Libera los recursos
        renderer.clean();
        loader.clean();
        DisplayManager.close();
    }

}