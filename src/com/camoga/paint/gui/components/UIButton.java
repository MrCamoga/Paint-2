package com.camoga.paint.gui.components;

import static org.lwjgl.opengl.GL11.GL_TRIANGLES;
import static org.lwjgl.opengl.GL11.glDrawArrays;
import static org.lwjgl.opengl.GL20.glDisableVertexAttribArray;
import static org.lwjgl.opengl.GL20.glEnableVertexAttribArray;
import static org.lwjgl.opengl.GL30.glBindVertexArray;

import com.camoga.paint.gl.engine.Display;
import com.camoga.paint.gl.input.Mouse;
import com.camoga.paint.gl.input.MouseEvent;
import com.camoga.paint.gl.input.MouseEvent.ButtonType;
import com.camoga.paint.gl.input.MouseEvent.EventType;
import com.camoga.paint.gui.event.UIActionListener;

public class UIButton extends UIComponent {

	public static class ButtonColor {
		float[] idle;
		float[] hover;
		float[] clicked;
		
		public ButtonColor(float[] idle, float[] hover, float[] clicked) {
			this.idle = idle;
			this.hover = hover;
			this.clicked = clicked;
		}
		
		public ButtonColor(float[] color) {
			this.idle = color;
			this.hover = new float[] {0.5f+color[0]/2,0.5f+color[1]/2,0.5f+color[2]/2};
			this.clicked = new float[] {0.66f+color[0]/3,0.66f+color[1]/3,0.66f+color[2]/3};
		}
	}
	
	private float[] bgColor;
	private ButtonColor color;
	private boolean pressed;
	
	private UIActionListener action = new UIActionListener() {
		public void onAction(MouseEvent e) {
			System.out.println("click");
		}
	};
	
	public UIButton(int x, int y, int width, int height, ButtonColor color) {
		this.width = width/640.0f;
		this.height = height/360.0f;
		this.x = x/1280.0f;
		this.y = y/720.0f;
		this.color = color;
		bgColor = color.idle;
	}
	
	public boolean onEvent(MouseEvent e) {
		if(super.onEvent(e)) {
			bgColor = color.idle;
			return true;
		}
		if(entered) {
			if(e.getType()==EventType.PRESSED && e.getButton() == ButtonType.LEFT) {
				pressed = true;
				bgColor = color.clicked;
			} else if(pressed && e.getType()==EventType.RELEASED) {
				bgColor = color.hover;
				action.onAction(e);
				pressed = false;
			} else if(e.getType()==EventType.MOVED) {
				bgColor = color.hover;
			}
		} else {
			if(e.getType()==EventType.RELEASED) {
				bgColor = color.idle;
				pressed = false;				
			} else if(e.getType()==EventType.MOVED) {
				bgColor = color.idle;				
			}
			return false;
		}

		return true;
	}

	public void render() {
		Display.shader.start();
		Display.shader.loadPanel(x+xo, y+yo, width, height, bgColor, radius);
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

	public void setActionListener(UIActionListener action) {
		this.action = action;		
	}
}