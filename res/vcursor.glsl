#version 400 core

in vec2 pos;
in vec2 texcoords;

out vec2 ftexcoords;

uniform vec2 offset;
uniform vec2 size;

void main() {
	gl_Position = vec4(pos*size+offset,1,1);
	
	ftexcoords = texcoords;
}