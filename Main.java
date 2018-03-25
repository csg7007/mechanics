package com.csg.mechanics;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Main extends JPanel {
	private static final long serialVersionUID = -8044839353707359687L;
	private int x = 0;

	private Graphics g2;
	private double rotate = 0;
	AffineTransform trans = null;
	private CImage im = null;
	private Image im2 = null;

	// 构造方法，获得外部Image对像的引用
	public Main(CImage im, Image im2) {
		trans = new AffineTransform();
		this.im = im;
		if (im2 != null) {
			this.im2 = im2;
			g2 = im2.getGraphics();
			g2.setColor(Color.blue);
			g2.fillOval(0, 0, 100, 100);
		}
	}

	public void display() {
		this.repaint();
		rotate = (rotate + 1) % 360;
		x++;
	}

	/**
	 * repaint方法会调用paint方法，并自动获得Graphics对像 然后可以用该对像进行2D画图
	 * 注：该方法是重写了JPanel的paint方法
	 */
	public void paint(Graphics g) {
		g.clearRect(0, 0, 500, 500);
		Graphics2D g2D = (Graphics2D) g;
		if (x <= 1) {
			im.translate(100, 100);
			System.out.println("first t");
		}
		if (null != im) {
			im.rotate(1);

			g2D.drawImage(im, im.getAffineTransform(), null);
		}
		if (null != im2) {
			AffineTransform atf = new AffineTransform();
			atf.setToTranslation(x, 0);
			g2D.drawImage(im2, atf, null);
		}
	}

	public static void main(String[] args) {
		// 在自定义Panel的外部定义一个Image绘图区
		Image im2 = new BufferedImage(100, 100, BufferedImage.TRANSLUCENT);
		JFrame jf = new JFrame();
		// 通过构造方法将缓冲缓冲区对像的引用传给自定义Panel
		Main jp = new Main(CImageFactory.getInstance(CImage.TYPE_RECT, 100, 100, Color.blue), im2);

		jf.setBounds(200, 200, 500, 500);
		jp.setSize(300, 300);
		jf.add(jp);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setVisible(true);
		// jp.display();
		while (true) {
			jp.display();

			try {
				Thread.sleep(30);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
