package com.camoga.paint.gl.engine;

import static org.lwjgl.glfw.GLFW.GLFW_FALSE;
import static org.lwjgl.glfw.GLFW.GLFW_RESIZABLE;
import static org.lwjgl.glfw.GLFW.GLFW_TRUE;
import static org.lwjgl.glfw.GLFW.GLFW_VISIBLE;
import static org.lwjgl.glfw.GLFW.glfwCreateWindow;
import static org.lwjgl.glfw.GLFW.glfwInit;
import static org.lwjgl.glfw.GLFW.glfwMakeContextCurrent;
import static org.lwjgl.glfw.GLFW.glfwPollEvents;
import static org.lwjgl.glfw.GLFW.glfwShowWindow;
import static org.lwjgl.glfw.GLFW.glfwSwapBuffers;
import static org.lwjgl.glfw.GLFW.glfwSwapInterval;
import static org.lwjgl.glfw.GLFW.glfwWindowHint;
import static org.lwjgl.glfw.GLFW.glfwWindowShouldClose;
import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.glClear;
import static org.lwjgl.opengl.GL11.glClearColor;
import static org.lwjgl.opengl.GL11.glViewport;
import static org.lwjgl.system.MemoryUtil.NULL;

import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.glfw.GLFWMouseButtonCallback;
import org.lwjgl.glfw.GLFWWindowSizeCallback;
import org.lwjgl.opengl.GL;

import com.camoga.paint.gl.input.Mouse;
import com.camoga.paint.gl.shaders.PanelShader;
import com.camoga.paint.gui.UIButton;
import com.camoga.paint.gui.UIButton.ButtonColor;
import com.camoga.paint.gui.UIManager;
import com.camoga.paint.gui.UIPanel;

public class Display {
	private static long window;
	
	private static int WIDTH = 1280;

	private static int HEIGHT = 720;
	
	public static void createDisplay() {
		GLFWErrorCallback.createPrint(System.err).set();
		if(!glfwInit()) throw new RuntimeException("Unable to init GLFW");
		
		glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
		glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE);
		
		window = glfwCreateWindow(WIDTH, HEIGHT, "Paint", NULL, NULL);
		if(window == NULL) throw new RuntimeException("Unable to create window");
		
		glfwMakeContextCurrent(window);
		glfwShowWindow(window);
		
		GL.createCapabilities();
		
		GLFWWindowSizeCallback.create((id, w, h) -> {
			glViewport(0, 0, w, h);
			WIDTH = w;
			HEIGHT = h;
		}).set(window);
		
		GLFWMouseButtonCallback.create((w, b, a, m) -> mouse.invoke(b,a,m)).set(window);

		glfwSwapInterval(1);
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
					2,0});
		
		shader = new PanelShader();
		UIPanel panel = new UIPanel(10, 10, 150, 200);
		panel.setBackground(0.5f, 0.5f, 0.5f);
		UIPanel panel2 = new UIPanel(10, 10, 50, 50);
		panel2.setBackground(0.7f, 0.5f, 0.5f);
		panel.add(panel2);
		UIButton button = new UIButton(5, 5, 30, 30, new ButtonColor(new float[] {0.3f,1f,0.6f}));
		UIButton button2 = new UIButton(0,0,15,15, new ButtonColor(new float[] {0.2f,0.5f,1}));
		panel2.add(button);
		button.add(button2);
		mouse = new Mouse();
		UIManager.setScene(panel);
		System.out.println(panel.getX()+","+panel.getY()+","+panel.getWidth()+","+panel.getHeight());
		System.out.println(panel2.getX()+","+panel2.getY()+","+panel2.getWidth()+","+panel2.getHeight());
	}
	
	public static void render() {
		glClearColor(0.7f, 0.7f, 0.7f, 1);
		glClear(GL_COLOR_BUFFER_BIT);
		
		shader.start();
		UIManager.render();
		shader.stop();
		
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
}