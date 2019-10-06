package com.camoga.paint.gl.shaders;

import com.camoga.paint.gl.engine.Display;
import com.camoga.paint.gui.UIPanel;

import static org.lwjgl.opengl.GL20.*;

import java.util.Arrays;

public class PanelShader extends ShaderProgram {

	private int loc_size, loc_pos, loc_color;
	
	public PanelShader() {
		super("/vshader.glsl", "/fshader.glsl");
	}
	
	public void getUniformLocations() {
		loc_size = getUniformLocation("size");
		loc_pos = getUniformLocation("offset");
		loc_color = getUniformLocation("color");
	}
	
	public void loadPanel(float x, float y, float width, float height, float[] color) {
		glUniform2f(loc_size, width, height);
		glUniform2f(loc_pos, 2*x-1, 1-2*y);
		glUniform3fv(loc_color, color);
	}

	public void bindAttributes() {
		bindAttribute(0, "pos");
	}

}
