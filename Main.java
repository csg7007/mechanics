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
	private int x = 200;
	private int y = 200;

	private Graphics g;
	private double rotate = 0;
	AffineTransform trans = null;
	private Image im = null;

	// 构造方法，获得外部Image对像的引用
	public Main(Image im) {
		trans = new AffineTransform();
		if (im != null) {
			this.im = im;
			g = im.getGraphics();
		}
	}

	public void display() {
		// x++;
		// y++;
		if (g != null) {
			// 调用的super.paint(g),让父类做一些事前的工作，如刷新屏幕
			super.paint(g);
			g.setColor(Color.RED); // 设置画图的颜色
			g.fill3DRect(x, y, 100, 100, true); // 填充一个矩形
			// 更新缓图
			this.repaint();
		}
		rotate = (rotate + 10) % 360;
	}

	/**
	 * repaint方法会调用paint方法，并自动获得Graphics对像 然后可以用该对像进行2D画图
	 * 注：该方法是重写了JPanel的paint方法
	 */
	public void paint(Graphics g) {
		// g.drawImage(im, 0, 0, this);
		if (null != im) {
			Graphics2D g2D = (Graphics2D) g;
			trans.setToIdentity();
			trans.setToRotation(Math.toRadians(rotate), 200, 200);
			g2D.drawImage(im, trans, null);
		}
	}

	public static void main(String[] args) {
		// 在自定义Panel的外部定义一个Image绘图区
		Image im = new BufferedImage(500, 500, BufferedImage.TYPE_INT_RGB);

		JFrame jf = new JFrame();
		// 通过构造方法将缓冲缓冲区对像的引用传给自定义Panel
		Main jp = new Main(im);

		jf.setBounds(200, 200, 500, 500);
		jp.setSize(300, 300);
		jf.add(jp);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setVisible(true);

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
