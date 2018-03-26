
import java.awt.Color;
import java.awt.image.BufferedImage;

public class CImageFactory {

	public static CImage getInstance(int type, int width, int height, Color color) {
		CImage image = new CImage(width, height, BufferedImage.TRANSLUCENT);
		image.draw(type, color);
		return image;
	}
}
