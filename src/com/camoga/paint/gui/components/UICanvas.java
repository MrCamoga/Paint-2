package com.camoga.paint.gui.components;

import static org.lwjgl.opengl.GL11.GL_TRIANGLES;
import static org.lwjgl.opengl.GL11.glBindTexture;
import static org.lwjgl.opengl.GL11.glDrawArrays;
import static org.lwjgl.opengl.GL13.GL_TEXTURE0;
import static org.lwjgl.opengl.GL13.glActiveTexture;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL30.glBindVertexArray;

import java.nio.ByteBuffer;
import java.util.stream.Stream;

import com.camoga.paint.gl.engine.Display;
import com.camoga.paint.gl.engine.Loader;
import com.camoga.paint.gl.shaders.CanvasShader;
import com.camoga.paint.gl.textures.Texture;

public class UICanvas extends UIComponent {
	public int[] pixels;
	
	public CanvasShader shader;
	public int textureid;
	
	public UICanvas() {
		shader = new CanvasShader();
		pixels = new int[20*20];
		textureid = glGenTextures();
		loadImage();
	}
	
	public void loadImage() {
		for(int i = 0; i < pixels.length; i++) pixels[i] = ((int)(Math.random()*0x1000000L)<<8)+0xff;
		
		ByteBuffer image = ByteBuffer.allocateDirect(4*pixels.length);
		for(int i : pixels) image.putInt(i);
		image.flip();
		
		glBindTexture(GL_TEXTURE_2D, textureid);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
		glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA, 20, 20, 0, GL_RGBA, GL_UNSIGNED_BYTE, image);
	}
	
	public void tick() {
		loadImage();
	}
	
	public void render() {
		shader.start();
		shader.loadPos(0.3f, 0.3f, 0.6f/Display.getAspect(), 0.6f);
		glBindVertexArray(Display.vao);
		glEnableVertexAttribArray(0);
		glEnableVertexAttribArray(1);
		glBindTexture(GL_TEXTURE_2D, textureid);
		glDrawArrays(GL_TRIANGLES, 0, 6);
		glDisableVertexAttribArray(1);
		glDisableVertexAttribArray(0);
		glBindVertexArray(0);
		shader.stop();
	}
}