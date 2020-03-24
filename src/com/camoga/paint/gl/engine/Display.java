package com.camoga.paint.gl.engine;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryUtil.NULL;

import org.lwjgl.glfw.*;
import org.lwjgl.opengl.GL;

import com.camoga.paint.gl.input.Mouse;
import com.camoga.paint.gl.shaders.PanelShader;
import com.camoga.paint.gui.UICursor;
import com.camoga.paint.gui.UIManager;
import com.camoga.paint.gui.UIPanel;
import com.camoga.paint.gui.components.UIButton.ButtonColor;
import com.camoga.paint.gui.components.*;

public class Display {
	private static long window;
	
	private static int WIDTH = 1280;

	private static int HEIGHT = 720;
	
	public static void createDisplay() {
		GLFWErrorCallback.createPrint(System.err).set();
		if(!glfwInit()) throw new RuntimeException("Unable to init GLFW");
		
		glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
		glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE);
		glfwWindowHint(GLFW_DECORATED, GLFW_TRUE);
		
		window = glfwCreateWindow(WIDTH, HEIGHT, "Paint", NULL, NULL);
		if(window == NULL) throw new RuntimeException("Unable to create window");
		
		glfwMakeContextCurrent(window);
		glfwShowWindow(window);
		
		GL.createCapabilities();
		glfwSetInputMode(window, GLFW_CURSOR, GLFW_CURSOR_HIDDEN);
		
		GLFWWindowSizeCallback.create((id, w, h) -> {
			glViewport(0, 0, w, h);
			WIDTH = w;
			HEIGHT = h;
			render();
		}).set(window);
		
		GLFWMouseButtonCallback.create((w, b, a, m) -> mouse.invoke(b,a,m)).set(window);

		glfwSwapInterval(GLFW_FALSE);
		glViewport(0, 0, WIDTH, HEIGHT);
		
		init();
	}
	
	public static PanelShader shader;
	public static int vao;
	public static Mouse mouse;
	public static void init() {
		vao = Loader.loadVAO(new float[] {
				0,0,
				0,-2,
				2,-2,
				0,0,
				2,-2,
				2,0}, new float[] {
					0, 0,
					0, 1,
					1, 1,
					0, 0,
					1, 1,
					1, 0
				});
		
		shader = new PanelShader();
		UIPanel panel = new UIPanel(0, 0, 1280, 720);
		panel.setBackground(0.975f, 0.975f, 0.975f);
		UIPanel panel2 = new UIPanel(10, 10, 50, 50);
		panel2.setBackground(0.7f, 0.5f, 0.5f);
		UIButton button = new UIButton(5, 5, 30, 30, new ButtonColor(new float[] {0.3f,1f,0.6f}));
		UIButton button2 = new UIButton(1,1,15,15, new ButtonColor(new float[] {0.2f,0.5f,1}));
		button2.setActionListener((e)-> System.out.println("button 2 click"));
		button.add(button2);
		panel2.add(button);
		panel.add(panel2);
		panel.add(new UICanvas());
//		UIMenuBar menubar = new UIMenuBar();
		UITabPane tabpane = new UITabPane();
		
		
		
		panel.add(tabpane);
		
		UIManager.addCursor(new UICursor());
				
		mouse = new Mouse();
		UIManager.getScene().add(panel);
	}
	
	public static void render() {
		glClearColor(0.7f, 0.7f, 0.7f, 1);
		glClear(GL_COLOR_BUFFER_BIT);
		
		UIManager.render();
		
		glfwSwapBuffers(window);
	}
	
	public static void tick() {
		mouse.tick();
		UIManager.tick();
		glfwPollEvents();
	}
	
	public static boolean shouldClose() {
		return glfwWindowShouldClose(window);
	}

	public static int getWidth() {
		return WIDTH;
	}
	
	public static int getHeight() {
		return HEIGHT;
	}

	public static long getWindow() {
		return window;
	}
	
	public static float getAspect() {
		return WIDTH/(float)HEIGHT;
	}
}