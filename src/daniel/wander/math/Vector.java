package daniel.wander.math;

import java.awt.Point;

public class Vector {
	
	double x, y;
	
	public Vector(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	public Vector pointingTo(Point point) {
		Vector vector = new Vector(point.getX() - this.x, point.getY() - this.y);
		vector.normalize();
		return vector;
	}
	
	public static Vector random() {
		return new Vector(1d - 2d * Math.random(), 1d - 2d * Math.random());
	}
	
	public Vector withMagnitude(double magnitude) {
		Vector vector = new Vector(this.x, this.y);
		vector.normalize();
		vector.x *= magnitude;
		vector.y *= magnitude;
		return vector;
	}
	
	public Point toPoint() {
		return new Point((int) this.x, (int) this.y);
	}
	
	public void add(Vector v) {
		this.x += v.x;
		this.y += v.y;
	}
	
	void normalize() {
		double magnitude = Math.sqrt(x * x + y * y);
		this.x /= magnitude;
		this.y /= magnitude;
	}

}
