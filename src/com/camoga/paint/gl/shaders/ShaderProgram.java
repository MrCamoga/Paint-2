package com.camoga.paint.gl.shaders;

import java.nio.file.Files;
import java.nio.file.Paths;

import static org.lwjgl.opengl.GL20.*;

public abstract class ShaderProgram {
	
	int programID;
	int vertexID, fragID;
	
	public ShaderProgram(String vshader, String fshader) {
		vertexID = loadShader(GL_VERTEX_SHADER, vshader);
		fragID = loadShader(GL_FRAGMENT_SHADER, fshader);
		programID = glCreateProgram();
		glAttachShader(programID, vertexID);
		glAttachShader(programID, fragID);
		bindAttributes();
		glLinkProgram(programID);
		glValidateProgram(programID);
		getUniformLocations();
	}

	public void start() {glUseProgram(programID); }
	public void stop() { glUseProgram(0); }
	
	public abstract void bindAttributes();	
	public abstract void getUniformLocations();
	
	public void bindAttribute(int id, String name) {
		glBindAttribLocation(programID, id, name);
	}
	
	public int getUniformLocation(String var) {
		return glGetUniformLocation(programID, var);
	}
	
	public int loadShader(int type, String file) {
		int id = glCreateShader(type);
		glShaderSource(id, readShaderSource(file));
		glCompileShader(id);
		if(glGetShaderi(id, GL_COMPILE_STATUS) != GL_TRUE) throw new RuntimeException(file + ": " + glGetShaderInfoLog(id));
		return id;
	}
	
	private String readShaderSource(String file) {
		try {
			byte[] data = Files.readAllBytes(Paths.get(getClass().getResource(file).toURI()));
			return new String(data);
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return null;
	}
}
