package com.camoga.paint.gui;

import static org.lwjgl.opengl.GL11.GL_TRIANGLES;
import static org.lwjgl.opengl.GL11.glDrawArrays;
import static org.lwjgl.opengl.GL20.glDisableVertexAttribArray;
import static org.lwjgl.opengl.GL20.glEnableVertexAttribArray;
import static org.lwjgl.opengl.GL30.glBindVertexArray;

import java.util.ArrayList;

import com.camoga.paint.gl.engine.Display;
import com.camoga.paint.gl.input.MouseEvent;

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
		Display.shader.loadPanel(x+xo,y+yo,width,height, backgroundColor);
		glBindVertexArray(Display.vao);
		glEnableVertexAttribArray(0);
		glDrawArrays(GL_TRIANGLES, 0, 6);
		glDisableVertexAttribArray(0);
		glBindVertexArray(0);
		super.render();
	}

	public void tick() {
		if(inside()) {
			entered = true;
			//iterate to know if this is the component on which the mouse is
		} else {
			entered = false;
		}
		
		for(UIComponent child : childs) {
			child.tick();
		}
	}
}
