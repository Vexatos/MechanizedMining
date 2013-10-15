package dark.mining.common.privateutils;

import static org.lwjgl.opengl.GL11.*;

/**
 * @author Archadia
 *
 */
public class RenderUtils {

	public void renderGrid(float r, float g, float b, float sizeX, float sizeY) {
		glColor3f(r, g, b);
		glBegin(GL_QUADS);
		{
			glVertex2f(0,0);
			glVertex2f(0,sizeY);
			glVertex2f(sizeX,sizeY);
			glVertex2f(sizeX,0);
		}
		glEnd();
	}
}
