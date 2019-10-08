package daniel.wander.main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;

import daniel.wander.math.Vector;
import daniel.wander.math.Wanderer;

public class Main extends JFrame {
	
	Canvas canvas = new Canvas();
	static float BACKGROUND_COLOR = 0.8f;
	
	Wanderer[] wanderers = new Wanderer[1];
	
	public static void main(String[] args) {
		new Main();
	}
	
	Main() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(1200, 800);
		setLocationRelativeTo(null);
		setTitle("Wandering");
		setBackground(new Color(BACKGROUND_COLOR, BACKGROUND_COLOR, BACKGROUND_COLOR));
		
		add(canvas);
		
		setVisible(true);
		canvas.createBufferStrategy(3);
		
		for (int i = 0; i < wanderers.length; i++) {
			wanderers[i] = new Wanderer(new Vector(Math.random() * getWidth(), Math.random() * getHeight()));
			for (int j = 0; j < wanderers[i].getTrailLength(); j++)
				wanderers[i].update(getRandomPoint());
		}
		
		startLoop();
	}
	
	void startLoop() {
		long now = System.nanoTime(), lastLoop = now, loopTime = 1000000000 / 60;
		while (true) {
			if ((now = System.nanoTime()) - lastLoop > loopTime) {
				update();
				render();
				lastLoop = now;
			}
		}
	}
	
	void update() {
		for (Wanderer wanderer : wanderers)
			wanderer.update(getRandomPoint());
	}
	
	void render() {
		BufferStrategy bs = canvas.getBufferStrategy();
		Graphics g = bs.getDrawGraphics();
		g.setColor(getBackground());
		g.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
		for (Wanderer wanderer : wanderers) 
			wanderer.render(g);
		g.dispose();
		bs.show();
	}
	
	Point getRandomPoint() {
		return new Point((int) (Math.random() * getWidth()), (int) (Math.random() * getHeight()));
	}

}
