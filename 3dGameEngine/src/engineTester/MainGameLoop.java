package engineTester;

import org.lwjgl.opengl.Display;

import renderEngine.DisplayManager;
import renderEngine.Loader;
import renderEngine.RawModel;
import renderEngine.Renderer;

public class MainGameLoop {

	public static void main(String[] args) {
		DisplayManager.createDisplay();
		
		Loader loader = new Loader();
		Renderer renderer = new Renderer();
		
		//THE DATA
		float[] vertices = {
				-0.5f, -0.5f, 0.0f,	//left bottom
				0.5f, -0.5f, 0.0f,	//right bottom
				-0.5f, 0.5f, 0.0f,	//left top
				
				-0.5f, 0.5f, 0.0f,	//left top
				0.5f, -0.5f, 0.0f,	//right bottom
				0.5f, 0.5f, 0.0f	//top right
		};
		
		RawModel model = loader.loadToVAO(vertices);
		
		while(!Display.isCloseRequested()) {
			renderer.prepare();
			renderer.render(model);
			
			DisplayManager.updateDisplay();
		}
		
		loader.cleanUp();
		DisplayManager.closeDisplay();

	}

}
