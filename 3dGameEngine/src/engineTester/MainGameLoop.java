package engineTester;

import org.lwjgl.opengl.Display;

import models.RawModel;
import models.TexturedModel;
import renderEngine.DisplayManager;
import renderEngine.Loader;
import renderEngine.Renderer;
import shaders.StaticShader;
import textures.ModelTexture;

public class MainGameLoop {
	public static void main(String [] args) {
		
		DisplayManager.createDisplay();
		System.err.println("Game is Running Fine!");
		System.out.println("Hello Git");
		
		Loader loader = new Loader();
		
		Renderer renderer = new Renderer();
		
		StaticShader shader = new StaticShader();
		
		float[] vertices = {
			-0.5f, 0.5f, 0.0f,	//V0
			-0.5f, -0.5f, 0.0f,	//V1
			0.5f,-0.5f,0.0f,	//V2
			0.5f, 0.5f, 0.0f	//V3
			
		};
		
		int[] indices = {
			0,1,3,
			3,1,2
		};
		
		RawModel model = loader.loadToVAO(vertices, indices);
		
		ModelTexture texture = new ModelTexture(loader.loadTexture("image"));
		
		TexturedModel texturedModel = new TexturedModel(model, texture);
		
		while(!Display.isCloseRequested()) {
			renderer.prepare();
			shader.start();
			renderer.render(texturedModel);
			shader.stop();
			DisplayManager.updateDisplay();
		}
		
		shader.cleanUp();
		loader.cleanUp();
		DisplayManager.closeDisplay();
	}

}
