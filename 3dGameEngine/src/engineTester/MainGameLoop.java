package engineTester;

import org.lwjgl.opengl.Display;

import renderEngine.DisplayManager;
import renderEngine.Loader;
import renderEngine.RawModel;
import renderEngine.Renderer;
import shaders.StaticShader;

public class MainGameLoop {

	public static void main(String[] args) {
		DisplayManager.createDisplay();
		
		Loader loader = new Loader();
		Renderer renderer = new Renderer();
		StaticShader shader = new StaticShader();
		
		//THE DATA
		float[] vertices = {
				-0.5f, -0.5f, 0.0f,	//left bottom
				0.5f, -0.5f, 0.0f,	//right bottom
				-0.5f, 0.5f, 0.0f,	//left top
				
				0.5f, 0.5f, 0.0f	//top right
		};
		
		int[] indices = {
				0,1,2,
				2,3,1
		};
		
		RawModel model = loader.loadToVAO(vertices, indices);
		
		while(!Display.isCloseRequested()) {
			renderer.prepare();
			
			shader.start();
			renderer.render(model);
			shader.stop();
			
			DisplayManager.updateDisplay();
		}
		
		shader.cleanUp();
		loader.cleanUp();
		DisplayManager.closeDisplay();

	}

}
