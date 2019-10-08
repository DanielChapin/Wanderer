package daniel.wander.math;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

public class Wanderer {
	
	Vector particle;
	
	Point[] trail = new Point[50];
	
	public Wanderer(Vector particle, int trailLength) {
		this(particle);
		trail = new Point[trailLength];
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
	
	public void update(Point randomPoint) {
		addToTrail(particle.toPoint());
		particle.add(particle.pointingTo(randomPoint).withMagnitude(25f));
	}
	
	public void render(Graphics g) {
		for (int i = trail.length - 1; i > 0; i--) {
			g.setColor(Color.getHSBColor(1f * i / (trail.length + 1), 1, 1f - 0.5f * i / (trail.length + 1)));
			g.drawLine(trail[i].x, trail[i].y, trail[i - 1].x, trail[i - 1].y);
		}
	}

}
