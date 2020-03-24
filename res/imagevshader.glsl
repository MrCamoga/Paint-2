#version 400 core

in vec2 pos;
in vec2 texcoords;

out vec2 ftexcoords;

uniform vec2 size;
uniform vec2 offset;

void main() {
	gl_Position = vec4(offset+size*pos, 1.0, 1.0);
	ftexcoords = texcoords;
}
