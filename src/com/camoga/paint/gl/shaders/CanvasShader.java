package com.camoga.paint.gl.shaders;

import static org.lwjgl.opengl.GL20.*;

public class CanvasShader extends ShaderProgram {

	private int loc_offset, loc_size;
	
	public CanvasShader() {
		super("/imagevshader.glsl", "/imagefshader.glsl");
	}

	public void bindAttributes() {
		super.bindAttribute(0, "pos");
		super.bindAttribute(1, "texcoords");
	}

	public void getUniformLocations() {
		loc_offset = getUniformLocation("offset");
		loc_size = getUniformLocation("size");
	}
	
	public void loadPos(float x, float y, float width, float height) {
		glUniform2f(loc_offset, 2*x-1, 1-2*y);
		glUniform2f(loc_size, width, height);
	}
}