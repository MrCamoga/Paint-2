package com.camoga.paint.gl.shaders;

import static org.lwjgl.opengl.GL20.*;

import com.camoga.paint.gl.engine.Display;

public class CursorShader extends ShaderProgram {

	private int loc_size, loc_offset, loc_subimage;
	private static float SCALE = 2f;
	
	public CursorShader() {
		super("/vcursor.glsl", "/fcursor.glsl");
	}

	public void loadCursor(float x, float y, float xo, float yo, float tx, float ty) {
		glUniform2f(loc_offset, -1+2*(x-xo*SCALE), 1-2*(y-yo*SCALE));
		glUniform2f(loc_size, 16*SCALE/(float)Display.getWidth(), 16*SCALE/(float)Display.getHeight());
		glUniform2f(loc_subimage, tx, ty);
	}

	public void bindAttributes() {
		super.bindAttribute(0, "pos");
		super.bindAttribute(1, "texcoords");		
	}

	public void getUniformLocations() {
		loc_size = getUniformLocation("size");
		loc_offset = getUniformLocation("offset");
		loc_subimage = getUniformLocation("subimage");
	}

}
