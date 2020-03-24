#version 400 core

in vec2 ftexcoords;

uniform vec3 color;
uniform float radius;

void main() {
	if(radius > 0) {
		float rs = radius*radius;
		float cr = 1-radius;

		if(ftexcoords.x < radius) {
			if(ftexcoords.y < radius) {
				vec2 d = ftexcoords-vec2(radius);
				if(dot(d,d) > rs) discard;
			} else if(ftexcoords.y > cr) {
				vec2 d = ftexcoords-vec2(radius,cr);
				if(dot(d,d) > rs) discard;
			}
		} else if(ftexcoords.x > cr) {
			if(ftexcoords.y < radius) {
				vec2 d = ftexcoords-vec2(cr,radius);
				if(dot(d,d) > rs) discard;
			} else if(ftexcoords.y > cr) {
				vec2 d = ftexcoords-vec2(cr);
				if(dot(d,d) > rs) discard;
			}
		}
	}

	gl_FragColor = vec4(color,1);
}
