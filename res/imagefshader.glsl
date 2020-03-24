#version 400 core

in vec2 ftexcoords;

uniform sampler2D image;

void main() {
	gl_FragColor = texture2D(image, ftexcoords);
}
