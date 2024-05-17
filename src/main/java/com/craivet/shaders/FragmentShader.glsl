#version 400 core

out vec4 out_Color; // Esta es una manera mas moderna y flexible de definir la salida de color en GLSL

void main() {
    // gl_FragColor = vec4(1.0, 0.0, 1.0, 1.0); // gl_FragColor es una variable predefinida y mas antigua
    out_Color = vec4(1.0, 0.5, 0.2, 1.0);
}