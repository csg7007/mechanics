import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Main extends JPanel {
	private static final long serialVersionUID = -8044839353707359687L;
	private CImage im = null;
	private CImage im2 = null;
	private int lx=200,ly=200;
	private boolean isFirst=true;
	// 构造方法，获得外部Image对像的引用
	public Main(CImage im, CImage im2) {
		this.im = im;
		this.im2 = im2;
	}

	/**
	 * repaint方法会调用paint方法，并自动获得Graphics对像 然后可以用该对像进行2D画图 注：该方法是重写了JPanel的paint方法
	 */
	public void paint(Graphics g) {
		lx--;
		ly--;
		g.clearRect(0, 0, 600, 600);
		Graphics2D g2D = (Graphics2D) g;
		if(isFirst) {
			im.translate(100, 0);
			im.setAngularSpeed(1);
			g2D.drawImage(im, im.getAffineTransform(), null);
			isFirst=false;
			return;
		}
		if (null != im) {
			ForceAnalysisResult result=ForceAnalysis.analysis(im);
			im.translate(result.getXd(), result.getYd());
			im.rotate(result.getAngle());
			g2D.drawImage(im, im.getAffineTransform(), null);
		}
//		if (null != im2) {
//			im2.translate(2, 5);
//			g2D.drawImage(im2, im2.getAffineTransform(), null);
//		}
		g2D.drawLine(0, 500, 600, 500);
	}

	public static void main(String[] args) {
		JFrame jf = new JFrame();
		Main jp = new Main(CImageFactory.getInstance(CImage.SHAPE_RECT, 100, 100, Color.blue),
				CImageFactory.getInstance(CImage.SHAPE_OVAL, 100, 100, Color.GREEN));
		jf.setBounds(200, 200, 600, 600);
		jp.setSize(500, 500);
		jf.add(jp);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setVisible(true);
		while (true) {
			jp.repaint();
			try {
				Thread.sleep(30);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}