package com.camoga.paint.gui;

import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.GL_TRIANGLES;
import static org.lwjgl.opengl.GL11.glBindTexture;
import static org.lwjgl.opengl.GL11.glDrawArrays;
import static org.lwjgl.opengl.GL20.glDisableVertexAttribArray;
import static org.lwjgl.opengl.GL20.glEnableVertexAttribArray;
import static org.lwjgl.opengl.GL30.glBindVertexArray;

import java.util.ArrayList;

import com.camoga.paint.gl.engine.Display;
import com.camoga.paint.gl.input.MouseEvent;
import com.camoga.paint.gl.shaders.CursorShader;
import com.camoga.paint.gl.textures.Texture;
import com.camoga.paint.gui.UICursor.CursorTypes;
import com.camoga.paint.gui.components.UIMenuBar;
import com.camoga.paint.gui.event.UIMouseEventListener;

public class UIManager {
	
	private static UIRootPanel scene = new UIRootPanel();
	private static UIMenuBar menubar = new UIMenuBar();
	
	private static ArrayList<UIMouseEventListener> priorityEvents = new ArrayList<>();
		
	private static ArrayList<UICursor> cursors = new ArrayList<>();
	private static int cursortexture = Texture.loadTexture("/cursors/cursors.png");
	private static CursorShader cursorshader = new CursorShader();
	
	private UIManager() {}
	
	public static void render() {
		scene.render();
		menubar.render();
		renderCursors();
	}
	
	private static void renderCursors() {
		cursorshader.start();
		glBindVertexArray(Display.vao);
		glEnableVertexAttribArray(0);
		glEnableVertexAttribArray(1);
		glBindTexture(GL_TEXTURE_2D, cursortexture);
		cursors.forEach(c -> {
			c.loadCursor(cursorshader);
			glDrawArrays(GL_TRIANGLES, 0, 6);			
		});
		glDisableVertexAttribArray(1);
		glDisableVertexAttribArray(0);
		glBindVertexArray(0);
		cursorshader.stop();
	}

	public static void tick() {
		menubar.tick();
		scene.tick();
	}
	
	public static void onEvent(MouseEvent e) {
		for(UIMouseEventListener p : priorityEvents) {
			if(!p.onEvent(e)) continue;
			priorityEvents.remove(p);
			return;
		}
		scene.onEvent(e);
	}
	
	public static UIRootPanel getScene() {
		return scene;
	}
	
	public static void addCursor(UICursor cursor) {
		cursors.add(cursor);
	}
	
	public static void removeCursor(UICursor cursor) {
		cursors.remove(cursor);
	}
	
	public static ArrayList<UICursor> getCursors() {
		return cursors;
	}
	
	public static void addPriorityEvent(UIMouseEventListener e) {
		priorityEvents.add(e);
	}
}