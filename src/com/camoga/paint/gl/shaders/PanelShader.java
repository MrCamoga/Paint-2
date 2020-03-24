package com.camoga.paint.gl.shaders;

import static org.lwjgl.opengl.GL20.glUniform1f;
import static org.lwjgl.opengl.GL20.glUniform2f;
import static org.lwjgl.opengl.GL20.glUniform3fv;

public class PanelShader extends ShaderProgram {

	private int loc_size, loc_pos, loc_color, loc_radius;
	
	public PanelShader() {
		super("/vshader.glsl", "/fshader.glsl");
	}
	
	public void getUniformLocations() {
		loc_size = getUniformLocation("size");
		loc_pos = getUniformLocation("offset");
		loc_color = getUniformLocation("color");
		loc_radius = getUniformLocation("radius");
	}
	
	public void loadPanel(float x, float y, float width, float height, float[] color, float radius) {
		glUniform2f(loc_size, width, height);
		glUniform2f(loc_pos, 2*x-1, 1-2*y);
		glUniform3fv(loc_color, color);
		glUniform1f(loc_radius, radius);
	}

	public void bindAttributes() {
		bindAttribute(0, "pos");
	}

}
