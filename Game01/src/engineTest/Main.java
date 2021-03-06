package engineTest;

import org.lwjgl.opengl.Display;

import renderEngine.DisplayManager;
import renderEngine.Loader;
import renderEngine.RawModel;
import renderEngine.Renderer;

public class Main {

	public static void main(String[] args) {
		
		DisplayManager.createDisplay();
		
		Loader loader = new Loader();
		Renderer renderer = new Renderer();
		
		
		float[] vertices = {
				-0.5f, 0.5f, 0f, 	//V0
				-0.5f, -0.5f, 0f, 	//V1
				0.5f, -0.5f, 0f,	//V2
				0.5f, 0.5f, 0f,		//V3
		};
		
		int[] indices = {
				0,1,3, 		// Top left triangle (V0,V1,V3)
				3,1,2		// Bottom right triangle (V3,V1,V2)
		};
		
		RawModel model = loader.loadToVAO(vertices, indices);
		
		while(!Display.isCloseRequested()) {
			renderer.prepare();
			renderer.render(model);
			DisplayManager.updateDisplay();
		}
		
		loader.cleaUp();
		
		DisplayManager.destroyDisplay();

	}

}
