#version 400 core

in vec2 ftexcoords;

uniform sampler2D cursors;
uniform vec2 subimage;

void main() {
	vec4 color = texture2D(cursors, ftexcoords/16.0 + subimage);
	if(color.a < 0.5) discard;
	gl_FragColor = texture2D(cursors, ftexcoords/16.0 + subimage);
}
