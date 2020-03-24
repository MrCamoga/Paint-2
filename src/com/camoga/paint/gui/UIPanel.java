package com.camoga.paint.gui;

import static org.lwjgl.opengl.GL11.GL_TRIANGLES;
import static org.lwjgl.opengl.GL11.glDrawArrays;
import static org.lwjgl.opengl.GL20.glDisableVertexAttribArray;
import static org.lwjgl.opengl.GL20.glEnableVertexAttribArray;
import static org.lwjgl.opengl.GL30.glBindVertexArray;

import java.util.ArrayList;

import com.camoga.paint.gl.engine.Display;
import com.camoga.paint.gl.input.MouseEvent;
import com.camoga.paint.gui.components.UIComponent;

public class UIPanel extends UIComponent {
	
	private float[] backgroundColor = new float[] {0.6f,0.6f,0.6f};
	
	public UIPanel(int x, int y, int width, int height) {
		this.width = width/640.0f;
		this.height = height/360.0f;
		this.x = x/1280.0f;
		this.y = y/720.0f;
	}
	
	public void setBackground(float r, float g, float b) {
		backgroundColor[0] = r;
		backgroundColor[1] = g;
		backgroundColor[2] = b;
	}
	
	public float[] getBackground() {
		return backgroundColor;
	}

	public boolean onEvent(MouseEvent e) {
		if(super.onEvent(e)) return true;
		return false;
	}

	public void render() {
		Display.shader.start();
		Display.shader.loadPanel(x+xo,y+yo,width,height, backgroundColor, radius);
		glBindVertexArray(Display.vao);
		glEnableVertexAttribArray(0);
		glEnableVertexAttribArray(1);
		glDrawArrays(GL_TRIANGLES, 0, 6);
		glDisableVertexAttribArray(1);
		glDisableVertexAttribArray(0);
		glBindVertexArray(0);
		Display.shader.stop();
		super.render();
	}

	public void tick() {
		super.tick();
	}
}
