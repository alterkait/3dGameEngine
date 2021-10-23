package engineTester;

import org.lwjgl.opengl.Display;

import renderEngine.DisplayManager;
import renderEngine.Loader;
import renderEngine.RawModel;
import renderEngine.Renderer;

public class MainGameLoop {
	public static void main(String [] args) {
		
		DisplayManager.createDisplay();
		System.err.println("Game is Running Fine!");
		System.out.println("Hello Git");
		
		Loader loader = new Loader();
		
		Renderer renderer = new Renderer();
		
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
		
		while(!Display.isCloseRequested()) {
			renderer.prepare();
			renderer.render(model);
			DisplayManager.updateDisplay();
		}
		
		loader.cleanUp();
		DisplayManager.closeDisplay();
	}

}
