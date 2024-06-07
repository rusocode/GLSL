#version 400 core

#define PI 3.14159265359

uniform vec2 resolution;
uniform float time;

out vec4 out_Color;

float diagonalSmooth(vec2 st, float y) {
    return smoothstep(y - 0.01, y, st.y) - smoothstep(y, y + 0.01, st.y);
}

void main() {

    vec2 st = gl_FragCoord.xy / resolution;

    float sinuosityLevel = PI;
    float speed = 2;

    float y = sin(st.x * sinuosityLevel + (time * speed)) * 0.5 + 0.5;
    float diagSmooth = diagonalSmooth(st, y);

    vec3 gradient = vec3(y);
    vec3 green = vec3(0.0, 1.0, 0.0);

    vec3 color = /* ((1.0 - diagSmooth) * gradient) + */ (diagSmooth * green);

    out_Color = vec4(color, 1.0);

}