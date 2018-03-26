import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public class CImage extends BufferedImage {

	private AffineTransform affineTransform;
	private double localX, localY;
	private double curRotateAngle;
	private int shape;
	/** 固定点 */
	private CImageFixPoint fixPoint;
	/** 弹簧 */
	private CImageSpring spring;
	/** y軸速度 */
	private double yVelocity;
	/** x軸速度 */
	private double xVelocity;
	/** 角速度 */
	private double angularSpeed;

	/** 形狀-矩形 */
	public static final int SHAPE_RECT = 0;
	/** 形狀-橢圓形 */
	public static final int SHAPE_OVAL = 1;

	public CImageSpring getSpring() {
		return spring;
	}

	public void setSpring(CImageSpring spring) {
		this.spring = spring;
	}

	public CImageFixPoint getFixPoint() {
		return fixPoint;
	}

	public void setFixPoint(CImageFixPoint fixPoint) {
		this.fixPoint = fixPoint;
	}

	public CImage(int width, int height, int imageType) {
		super(width, height, imageType);
		this.affineTransform = new AffineTransform();
	}

	public void draw(int shape, Color color, int... linePos) {
		Graphics g = this.getGraphics();
		g.setColor(color);
		this.shape = shape;
		switch (shape) {
		case SHAPE_RECT:
			g.fill3DRect(0, 0, this.getWidth(), this.getHeight(), true);
			break;
		case SHAPE_OVAL:
			g.fillOval(0, 0, this.getWidth(), this.getHeight());
			break;
		default:
			throw new RuntimeException("shape 不存在！current shape is " + shape);
		}

	}

	public int getShape() {
		return shape;
	}

	/**
	 * 旋转
	 * 
	 * @param angdeg
	 *            旋转角度
	 */
	public void rotate(double angdeg) {
		this.curRotateAngle += angdeg;
		// 按图形中心旋转
		AffineTransform atf = new AffineTransform();
		atf.setToRotation(Math.toRadians(this.curRotateAngle), this.getWidth() / 2d, this.getHeight() / 2d);
		this.affineTransform.concatenate(atf);
	}

	/**
	 * 平移
	 * 
	 * @param tx
	 *            x轴平移距离
	 * @param ty
	 *            y轴平移距离
	 */
	public void translate(double tx, double ty) {
		this.localX += tx;
		this.localY += ty;
		this.affineTransform.setToTranslation(this.localX, this.localY);
	}

	public AffineTransform getAffineTransform() {
		return this.affineTransform;
	}

	public double getyVelocity() {
		return yVelocity;
	}

	public void setyVelocity(double yVelocity) {
		this.yVelocity = yVelocity;
	}

	public double getxVelocity() {
		return xVelocity;
	}

	public void setxVelocity(double xVelocity) {
		this.xVelocity = xVelocity;
	}

	public double getAngularSpeed() {
		return angularSpeed;
	}

	public void setAngularSpeed(double angularSpeed) {
		this.angularSpeed = angularSpeed;
	}

	public double getBottomY() {
		return this.localY + this.getHeight();
	}

}