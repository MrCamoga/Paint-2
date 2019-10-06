#version 400 core

in vec2 pos;

uniform vec2 size;
uniform vec2 offset;

void main() {
	gl_Position = vec4(offset+pos*size, 1.0, 1.0);
}