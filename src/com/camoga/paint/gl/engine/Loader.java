package com.camoga.paint.gl.engine;

import static org.lwjgl.opengl.GL11.GL_FLOAT;
import static org.lwjgl.opengl.GL15.GL_ARRAY_BUFFER;
import static org.lwjgl.opengl.GL15.GL_STATIC_DRAW;
import static org.lwjgl.opengl.GL15.glBindBuffer;
import static org.lwjgl.opengl.GL15.glBufferData;
import static org.lwjgl.opengl.GL15.glGenBuffers;
import static org.lwjgl.opengl.GL20.glVertexAttribPointer;
import static org.lwjgl.opengl.GL30.glBindVertexArray;
import static org.lwjgl.opengl.GL30.glGenVertexArrays;

import java.util.ArrayList;

public class Loader {
	public static ArrayList<Integer> vaos = new ArrayList<>();
	public static ArrayList<Integer> vbos = new ArrayList<>();
	
	public static int loadVAO(float[] vertices) {
		int vao = glGenVertexArrays();
		vaos.add(vao);
		glBindVertexArray(vao);
		storeDataInAttribList(0, 2, vertices);
		glBindVertexArray(0);
		return vao;
	}
	
	public static void storeDataInAttribList(int pos, int size, float[] data) {
		int vbo = glGenBuffers();
		glBindBuffer(GL_ARRAY_BUFFER, vbo);
		glBufferData(GL_ARRAY_BUFFER, data, GL_STATIC_DRAW);
		glVertexAttribPointer(pos, size, GL_FLOAT, false, 0, 0);
		glBindBuffer(GL_ARRAY_BUFFER, 0);
		vbos.add(vbo);
	}
}
