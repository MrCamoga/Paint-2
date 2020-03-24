package com.camoga.paint.gui;

import com.camoga.paint.gl.engine.Display;
import com.camoga.paint.gl.shaders.CursorShader;

public class UICursor {

	private float x, y;
	private int uuid;
	private int tool;
	
	public enum CursorTypes {
		DEFAULT(0,0,0), RUBBER(1,6,12), BUCKET(2,1,9);
		
		private int id, x, y;
		
		CursorTypes(int id, int x, int y) {
			this.id = id;
			this.x = x;
			this.y = y;
		}

		public int getId() {
			return id;
		}

		public int getX() {
			return x;
		}

		public int getY() {
			return y;
		}
	}
	
	public UICursor() {
		
	}
	
	public void update(float x, float y, int tool, int uuid) {
		this.x = x;
		this.y = y;
		this.tool = tool;
		this.uuid = uuid;
	}

	public void loadCursor(CursorShader cursorshader) {
		cursorshader.loadCursor(x, y, CursorTypes.values()[tool].x/(float)Display.getWidth(), CursorTypes.values()[tool].y/(float)Display.getHeight(), (tool%16)/16.0f, (tool/16)/16.0f);
	}
}