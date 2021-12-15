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

	public static void main(String[] args) {
		DisplayManager.createDisplay();
		
		Loader loader = new Loader();
		Renderer renderer = new Renderer();
		StaticShader shader = new StaticShader();
		
		//THE DATA
		float[] vertices = {
				-0.5f, 0.5f, 0.0f,	//v0
				-0.5f, -0.5f, 0.0f,	//v1
				0.5f, -0.5f, 0.0f,	//v2
				
				0.5f, 0.5f, 0.0f	//v3
		};
		
		int[] indices = {
				0,1,3,		//v0 v1 v3		top left triangle
				3,1,2		//v3 v1 v2		bottom right triangle
		};
		
		float[] textureCoords = {
				0,0,	//v0
				0,1,	//v1
				1,1,	//v2
				1,0		//v3
		};
		
		RawModel model = loader.loadToVAO(vertices, textureCoords, indices);
		ModelTexture texture = new ModelTexture(loader.loadTexture("test_image"));
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
