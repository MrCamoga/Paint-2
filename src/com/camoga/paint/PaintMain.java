package com.camoga.paint;

import com.camoga.paint.gl.engine.Display;
import static org.lwjgl.glfw.GLFW.glfwGetTime;

public class PaintMain {
	
	public static void main(String[] args) {
		Display.createDisplay();
		int frames = 0, ticks = 0;
		long timer = System.currentTimeMillis();
		
		double delta = 0;
		double last = glfwGetTime();
		
		while(!Display.shouldClose()) {
			double now = glfwGetTime();
			delta += (now-last)*60D;
			last = now;
			while(delta > 1) {
				Display.tick();
				ticks++;
				delta--;
			}
			Display.render();
			frames++;
			if(System.currentTimeMillis() - timer >= 1000) {
				timer = System.currentTimeMillis();
				System.out.println(frames + " fps, " + ticks + " ups");
				frames = 0;
				ticks = 0;
			}
		}
	}
}
