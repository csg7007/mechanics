package com.csg.mechanics;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public class CImage extends BufferedImage {

	private AffineTransform affineTransform;
	private double localX, localY;
	private double curRotateAngle;
	private int type;

	public static final int TYPE_RECT = 0;
	public static final int TYPE_OVAL = 1;

	public CImage(int width, int height, int imageType) {
		super(width, height, imageType);
		this.affineTransform = new AffineTransform();
	}

	public void draw(int type, Color color) {
		Graphics g = this.getGraphics();
		g.setColor(color);
		this.type = type;
		switch (type) {
		case TYPE_RECT:
			g.fill3DRect(0, 0, this.getWidth(), this.getHeight(), true);
			break;
		case TYPE_OVAL:
			g.fillOval(0, 0, this.getWidth(), this.getHeight());
			break;
		default:
			throw new RuntimeException("type 不存在！current type is " + type);
		}

	}

	public int getType() {
		return type;
	}

	public void rotate(double angdeg) {
		this.curRotateAngle += angdeg;
		double posX = this.localX + this.getWidth() / 2d;
		double posY = this.localY + this.getHeight() / 2d;
		System.out.println(this.localX + "--" + this.localY + "=====" + posX + "=" + posY);
		// 按图形中心旋转
		AffineTransform atf = new AffineTransform();
		atf.setToRotation(Math.toRadians(angdeg), 150, 150);
		this.affineTransform.concatenate(atf);
	}

	public void translate(double tx, double ty) {
		this.affineTransform = new AffineTransform();
		this.localX += tx;
		this.localY += ty;
		this.affineTransform.setToTranslation(tx, ty);

	}

	public AffineTransform getAffineTransform() {
		return this.affineTransform;
	}
}
