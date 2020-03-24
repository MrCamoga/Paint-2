package com.camoga.paint.gui.components;

import java.util.ArrayList;

import static org.lwjgl.opengl.GL30.*;

import com.camoga.paint.gl.engine.Display;
import com.camoga.paint.gl.input.MouseEvent;
import com.camoga.paint.gui.constraints.PixelConstraint;
import com.camoga.paint.gui.constraints.RelativeConstraint;
import com.camoga.paint.gui.event.UIMouseEventListener;

public class UIMenuBar extends UIComponent implements UIMouseEventListener {
		
	private ArrayList<UIMenu> menus = new ArrayList<>();
	
	public UIMenuBar() {
		//TODO menubar, menus, menuitems, priority events etc
		height = 30f;
		width = 1;
		x = 0;
		y = 0;
		
//		constraints.setWidth(new RelativeConstraint(1));
//		constraints.setHeight(new PixelConstraint(30));
	}
	
	public boolean onEvent(MouseEvent e) {
		for(UIMenu menu : menus) {
			if(menu.onEvent(e)) return true;
		}
		return false;
	}
	
	public void add(UIComponent c) {
		throw new RuntimeException("Illo no e");
	}
	
	public void add(UIMenu menu) {
		menus.add(menu);
	}
	
	public void render() {
		Display.shader.start();
		Display.shader.loadPanel(x, y, width, height/(float)Display.getHeight(), new float[] {0.7f,0.7f,0.7f}, 0);
		glBindVertexArray(Display.vao);
		glEnableVertexAttribArray(0);
		glEnableVertexAttribArray(1);
		glDrawArrays(GL_TRIANGLES, 0, 6);
		glDisableVertexAttribArray(0);
		glDisableVertexAttribArray(1);
		Display.shader.stop();
		super.render();
	}
}
