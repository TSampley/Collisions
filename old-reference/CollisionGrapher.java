
import com.sun.j3d.utils.universe.*;
import javax.media.j3d.*;
import javax.vecmath.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.LinkedList;

public class CollisionGrapher extends JPanel {
	Timer tmr;
	LinkedList<Particle> balls;
	BranchGroup group;
	TransformGroup VpTG;
	SimpleUniverse activeUniverse;
	DirectionalLight light;
	double zoom;

	boolean yes = false;

	public CollisionGrapher() {
		balls = new LinkedList<Particle>();
		// //balls
		balls.add(new Particle(new Point3d(-.3, 0, .3), .05, Color.blue, new Vector3d(20, 0, -20), 1000, 0));
		balls.add(new Particle(new Point3d(0, 0, 0), .05, Color.yellow, new Vector3d(0, 0, 0), 1000, 0));
		balls.add(new Particle(new Point3d(.3, 0, -.3), .05, Color.magenta, new Vector3d(0, 0, 0), 1000, 0));
		balls.add(new Particle(new Point3d(0, 0, 0), .05, Color.gray, new Vector3d(0, 0, 0), 2000, 0));

		// balls.add(new MovingBall(new Point3d(0, 0, 0), .07, Color.yellow, new
		// Vector3d(0, 0, 0), 986700000)); //sun
		// balls.add(new MovingBall(new Point3d(0.73, 0, 0), .0063765, new Color(0, 100,
		// 255), new Vector3d(0, 900000, 0), 299)); //earth
		// int num = 180;
		// balls.add(new MovingBall(new Point3d(1, 0, 0), .05, Color.yellow, new
		// Vector3d(0, -1000000000000d, 0), 986700000));
		// balls.add(new MovingBall(new Point3d(-1, 0, 0), .05, Color.yellow, new
		// Vector3d(0, 1000000000000d, 0), 986700000));
		// for (int i = 0; i < num; i++)
		// balls.add(new MovingBall(new Point3d(Math.cos(Math.toRadians(i * 360/num)) *
		// 0.73 + .74, Math.sin(Math.toRadians(i * 360/num)) * 0.73, 0), .01, new
		// Color(0, 100, 255), new Vector3d(Math.cos(Math.toRadians(i * 360/num + 90)) *
		// 3600000d, Math.sin(Math.toRadians(i * 360/num + 90)) * 3600000d, 0), 29900));
		// for (int i = 0; i < num; i++)
		// balls.add(new MovingBall(new Point3d(Math.cos(Math.toRadians(i * 360/num)) *
		// 0.73 - .74, Math.sin(Math.toRadians(i * 360/num)) * 0.73, 0), .01, new
		// Color(0, 100, 255), new Vector3d(Math.cos(Math.toRadians(i * 360/num + 90)) *
		// 3600000d, Math.sin(Math.toRadians(i * 360/num + 90)) * 3600000d, 0), 29900));

		// Creates a ring of balls-----------
		// double momentum = 0;
		// double r = 4;
		// int num = 90;
		// for (int i = 1; i < num; i++)
		// balls.add(new Particle(new Point3d(Math.cos(Math.toRadians(i * 360/num)) * r,
		// Math.sin(Math.toRadians(i * 360/num)) * r, 0), .1, Color.yellow, new
		// Vector3d(Math.cos(Math.toRadians(i * 360/num + 90)) * momentum,
		// Math.sin(Math.toRadians(i * 360/num + 90)) * momentum, 0), .01, 0));
		//// for (int i = 0; i < num; i++)
		//// balls.add(new Particle(new Point3d(0, Math.cos(Math.toRadians(i * 360/num))
		// * 1.5 + .75, Math.sin(Math.toRadians(i * 360/num)) * 1.5), .1, Color.blue,
		// new Vector3d(Math.cos(Math.toRadians(i * 360/num + 90)) * momentum,
		// Math.sin(Math.toRadians(i * 360/num + 90)) * momentum, 0), 1000, -1));
		// balls.add(new Particle(new Point3d(r, 0, 0), .1, Color.yellow, new
		// Vector3d(0, .0005, 0), .01, 0));
		// r = 2.6;
		// num = 60;
		// for (int i = 1; i < num; i++)
		// balls.add(new Particle(new Point3d(Math.cos(Math.toRadians(i * 360/num)) * r,
		// Math.sin(Math.toRadians(i * 360/num)) * r, 0), .1, Color.green, new
		// Vector3d(Math.cos(Math.toRadians(i * 360/num + 90)) * momentum,
		// Math.sin(Math.toRadians(i * 360/num + 90)) * momentum, 0), .01, 0));
		// balls.add(new Particle(new Point3d(r, 0, 0), .1, Color.green, new Vector3d(0,
		// .0005, 0), .01, 0));
		// r = 1.2;
		// num = 30;
		// for (int i = 1; i < num; i++)
		// balls.add(new Particle(new Point3d(Math.cos(Math.toRadians(i * 360/num)) * r,
		// Math.sin(Math.toRadians(i * 360/num)) * r, 0), .1, Color.blue, new
		// Vector3d(Math.cos(Math.toRadians(i * 360/num + 90)) * momentum,
		// Math.sin(Math.toRadians(i * 360/num + 90)) * momentum, 0), .01, 0));
		// balls.add(new Particle(new Point3d(r, 0, 0), .1, Color.blue, new Vector3d(0,
		// .0005, 0), .1, 0));
		// balls.add(new Particle(new Point3d(0, 0, 0), .5, Color.gray, new Vector3d(0,
		// 0, 0), 10000, 0));

		// Creates a Cube of balls-----------
		// for (double x = -3; x <= 3; x++)
		// for (double y = -3; y <= 3; y++)
		// for (double z = -3; z <= 3; z++)
		// balls.add(new Particle(new Point3d(x/2, y/2, z/2), .15, Color.green, new
		// Vector3d(0, 0, 0), 100, 0));

		// Creates a Sphere of balls---------
		// Vector3d vec = new Vector3d(0, 0, 3);
		// double constant = 15;
		// double theta = 180 / constant;
		// for (double x = 1; x < constant; x++)
		// {
		// double degree = 180/constant;
		// double sin = Math.sin(Math.toRadians(theta)), cos =
		// Math.cos(Math.toRadians(theta));
		// double newX = vec.y * cos - vec.z * sin;
		// double newY = vec.y * sin + vec.z * cos;
		// vec.y = newX;
		// vec.z = newY;
		//
		// for (double y = 0; y < constant * 2; y++)
		// {
		// sin = Math.sin(Math.toRadians(theta));
		// cos = Math.cos(Math.toRadians(theta));
		// newX = vec.x * cos - vec.y * sin;
		// newY = vec.x * sin + vec.y * cos;
		// vec.x = newX;
		// vec.y = newY;
		//
		// balls.add(new Particle(new Point3d(vec.x, vec.y, vec.z), .1, Color.green, new
		// Vector3d(0, 0, 0), 1000, .00));
		// }
		// }
		//
		// balls.add(new Particle(new Point3d(0, 0, 0), .1, Color.green, new Vector3d(0,
		// 0, 0), 1, balls.size() * -.005));

		// for (int i = 0; i < num; i++)
		// balls.add(new Particle(new Point3d(Math.cos(Math.toRadians(i * 360/num)) *
		// 1.5, Math.sin(Math.toRadians(i * 360/num)) * 1.5, 0), .1, Color.yellow, new
		// Vector3d(Math.cos(Math.toRadians(i * 360/num + 90)) * momentum,
		// Math.sin(Math.toRadians(i * 360/num + 90)) * momentum, 0), 600, 0));
		//
		// double num = 6.6;
		// double theta = 360 / num;
		// for (int i = 0; i < num; i++)
		// balls.add(new Particle(new Point3d(Math.cos(Math.toRadians(i * theta + .5 *
		// theta)) * 1.5, Math.sin(Math.toRadians(i * theta + .5 * theta)) * 1.5, 0),
		// .1, Color.yellow, new Vector3d(Math.cos(Math.toRadians(i * theta + 90)) *
		// momentum, Math.sin(Math.toRadians(i * theta + 90)) * momentum, 0), 600, 2));
		//
		// for (int i = 0; i < num; i++)
		// balls.add(new Particle(new Point3d(Math.cos(Math.toRadians(i * theta)) * 1.5,
		// Math.sin(Math.toRadians(i * theta)) * 1.5, 0), .1, Color.blue, new
		// Vector3d(Math.cos(Math.toRadians(i * theta + 90)) * momentum,
		// Math.sin(Math.toRadians(i * theta + 90)) * momentum, 0), 600, -2));

		// for (int i = 0; i < 50; i++)
		// {
		// balls.add(new Particle(new Point3d(Math.random() * 2 - 1, Math.random() * 2 -
		// 1, Math.random() * 2 - 1), .05, new Color(0, 100, 255), new
		// Vector3d(Math.random()*8 - 4, Math.random()*8 - 4, Math.random()*8 - 4), 200,
		// 0));
		// }
		// for (int i = 0; i < 50; i++)
		// {
		// balls.add(new Particle(new Point3d(Math.random() * 2 - 1, Math.random() * 2 -
		// 1, Math.random() * 2 - 1), .07, new Color(255, 100, 0), new
		// Vector3d(Math.random()*8 - 4, Math.random()*8 - 4, Math.random()*8 - 4), 400,
		// 0));
		// }
		// for (int i = 0; i < 50; i++)
		// {
		// balls.add(new Particle(new Point3d(Math.random() * 2 - 1, Math.random() * 2 -
		// 1, Math.random() * 2 - 1), .1, new Color(255, 0, 255), new
		// Vector3d(Math.random()*8 - 4, Math.random()*8 - 4, Math.random()*8 - 4), 800,
		// 0));
		// }
		// balls.add(new Particle(new Point3d(0, -.5, 0), 1, new Color(0, 100, 255), new
		// Vector3d(0, -.01, 0), .5, 0));
		// balls.add(new Particle(new Point3d(0, .5, 0), 1, new Color(0, 100, 255), new
		// Vector3d(0, .01, 0), .5, 0));
		// balls.add(new Particle(new Point3d(-.1, 0, 0), .1, new Color(0, 100, 255),
		// new Vector3d(0, 0, 0), .1, 0));
		// balls.add(new Particle(new Point3d(.3, 0, 0), .1, new Color(0, 100, 255), new
		// Vector3d(-15, -15, 0), 5000, 0));
		// balls.add(new Particle(new Point3d(0, -1, 0), .05, new Color(0, 100, 255),
		// new Vector3d(2200, 0, 0), 90000, 0));
		// balls.add(new Particle(new Point3d(0, 1, 0), .05, new Color(0, 100, 255), new
		// Vector3d(-2200, 0, 0), 250000, 0));
		//
		// for (int i = -4; i < 5; i++)
		// for (int j = -4; j < 5; j++)
		// balls.add(new MovingBall(new Point3d(i/2.0, j/2.0, 0), .1, new Color(100,
		// 100, 100), new Vector3d(0, 0, 0), 1000000d));

		// -----------------------------------------------------------
		Canvas3D canvas = new Canvas3D(SimpleUniverse.getPreferredConfiguration());
		SimpleUniverse universe = new SimpleUniverse(canvas);
		activeUniverse = universe;

		group = new BranchGroup();
		group.setCapability(BranchGroup.ALLOW_DETACH);
		group.setCapability(BranchGroup.ALLOW_CHILDREN_WRITE);

		System.out.println(balls.size());
		for (Particle b : balls)
			group.addChild(b);

		// setup lighting for the world
		Color3f light1Color = new Color3f(1f, 1f, 1f);
		BoundingSphere bounds = new BoundingSphere(new Point3d(0.0, 0.0, 0.0), 100.0); // standard bounds
		Vector3f light1Direction = new Vector3f(4.0f, -7.0f, -12.0f);
		DirectionalLight light1 = new DirectionalLight(light1Color, light1Direction);
		light1.setInfluencingBounds(bounds);
		// --------------

		VpTG = universe.getViewingPlatform().getViewPlatformTransform();

		zoom = 10; // put the camera 4 meters back
		Transform3D Trfcamera = new Transform3D();
		Trfcamera.setTranslation(new Vector3d(0.0f, 0.0f, zoom));
		VpTG.setTransform(Trfcamera);

		light = light1;
		group.addChild(light1);

		universe.addBranchGraph(group);

		// -----------------------------------------------------------

		setLayout(new BorderLayout());
		add(canvas);
		JButton b = new JButton("Start");
		b.addActionListener(new ButtonListener());
		b.addKeyListener(new PauseListener());
		b.setFocusable(true);
		add("South", b);
		setPreferredSize(new Dimension(700, 800));

		tmr = new Timer(1000 / 60, new MoveListener());
	}

	private class ControlPanel extends JPanel implements KeyListener {
		public ControlPanel() {
		}

		public void keyReleased(KeyEvent e) {
		}

		public void keyPressed(KeyEvent e) {
		}

		public void keyTyped(KeyEvent e) {
		}
	}

	private class ButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (tmr.isRunning()) {
				((JButton) e.getSource()).setText("Start");
				tmr.stop();
			} else {
				((JButton) e.getSource()).setText("Stop");
				tmr.start();
			}
		}
	}

	private class MoveListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			for (int i = balls.size() - 1; i >= 0; i--) {
				balls.get(i).calculateTrajectory(balls);
				balls.get(i).move(1);
				// balls.get(i).keepIn(2);
				if (balls.get(i).outOfBounds()) {
					balls.remove(i);
					System.out.println("out");
				} else
					balls.get(i).show();
			}
			// for (Particle b : balls)
			// {
			//// b.keepIn();
			// b.calculateTrajectory(balls);
			// while(b.checkCollisions(balls));
			// }
			// boolean repeat = false;
			// do
			// {
			// repeat = false;
			// for (Particle b : balls)
			// {
			// repeat = b.checkCollisions(balls) || repeat;
			// }
			// if (repeat)
			// for (Particle b : balls)
			// b.clear();
			// } while (repeat);
			for (Particle b : balls)
				b.checkCollisions(balls);
		}
	}

	private class PauseListener implements KeyListener {
		public void keyReleased(KeyEvent e) {
		}

		public void keyPressed(KeyEvent e) {
		}

		public void keyTyped(KeyEvent e) {
			Transform3D Trfcamera = new Transform3D();
			switch (e.getKeyCode()) {
			case (KeyEvent.VK_UP):
				System.out.println(e.getKeyCode());
				zoom--;
				Trfcamera.setTranslation(new Vector3d(0.0f, 0.0f, zoom));
				VpTG.setTransform(Trfcamera);
				break;
			case (KeyEvent.VK_DOWN):
				System.out.println(e.getKeyCode());
				zoom++;
				Trfcamera.setTranslation(new Vector3d(0.0f, 0.0f, zoom));
				VpTG.setTransform(Trfcamera);
				break;
			case (KeyEvent.VK_SPACE):
				System.out.println(e.getKeyCode());
				tmr.stop();
				break;
			}
		}
	}

	public void addObject(Particle p) {
		group.detach();
		for (int i = group.numChildren(); i > 0; i--)
			group.removeChild(0);

		balls.add(p);
		for (Particle particle : balls) {
			group.addChild(particle);
		}
		group.addChild(light);

		activeUniverse.addBranchGraph(group);
	}

	public static void main(String[] args) {
		JFrame frame = new JFrame("Title..");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		frame.getContentPane().add(new CollisionGrapher());

		frame.pack();
		frame.setVisible(true);
	}
}