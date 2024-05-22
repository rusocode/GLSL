#version 400 core

uniform vec2 u_resolution;
uniform float u_time;

out vec4 out_Color; // Esta es una manera mas moderna y flexible de definir la salida de color en GLSL

float blinnWyvillCosineApproximation(float x) {
    float x2 = x * x;
    float x4 = x2 * x2;
    float x6 = x4 * x2;

    float fa = (4.0 / 9.0);
    float fb = (17.0 / 9.0);
    float fc = (22.0 / 9.0);

    float y = fa * x6 - fb * x4 + fc * x2;
    return y;
}

// Traza una linea en Y usando un valor entre 0,0 y 1,0
float plot(vec2 st) {
    return smoothstep(0.02, 0.0, abs(st.y - st.x));
}

void main() {
    vec2 st = gl_FragCoord.xy / u_resolution;

    float y = st.x;

    vec3 color = vec3(y);

    // Traza una linea y la almacena en pct
    float pct = plot(st);
    color = (1.0 - pct) * color + pct * vec3(0.0, 1.0, 0.0);

    out_Color = vec4(color, 1.0);
}

// gl_FragCoord: guarda la coordenada del pixel o screen fragment del thread actual