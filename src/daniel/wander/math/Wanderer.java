package daniel.wander.math;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;

public class Wanderer {
	
	Vector particle;
	
	Point[] trail = new Point[50];
	
	double segmentLength = 10;
	
	public Wanderer(Vector particle, int trailLength, double segmentLength) {
		this(particle);
		this.trail = new Point[trailLength];
		this.segmentLength = segmentLength;
	}
	
	public Wanderer(Vector particle) {
		this.particle = particle;
	}
	
	void addToTrail(Point p) {
		Point[] temp = trail.clone();
		for (int i = 1; i < temp.length; i++)
			trail[i] = temp[i - 1];
		trail[0] = p;
	}
	
	public int getTrailLength() {
		return trail.length;
	}
	
	public void update(Dimension bounds) {
		addToTrail(particle.toPoint());
		Vector movement = Vector.random().withMagnitude(segmentLength);
		double nextX = particle.x + movement.x, nextY = particle.y + movement.y;
		if (nextX > bounds.width || nextX < 0)
			movement.x *= -1;
		if (nextY > bounds.height || nextY < 0)
			movement.y *= -1;
		particle.add(movement);
	}
	
	public void render(Graphics g) {
		for (int i = trail.length - 1; i > 0; i--) {
			g.setColor(Color.getHSBColor(1f * i / (trail.length + 1), 1, 1f - 0.25f * i / (trail.length + 1)));
			g.drawLine(trail[i].x, trail[i].y, trail[i - 1].x, trail[i - 1].y);
		}
	}

}
