
import javax.media.j3d.*;
import javax.vecmath.*;
import java.awt.Color;
import java.util.LinkedList;

public class Particle extends Ball {
	/*
	 * inherited from Ball private Sphere s; protected Point3f origin; protected
	 * double radius; private Color3f color;
	 */
	protected static int record = 0;
	protected Vector3d momentum;
	protected double mass;
	protected double charge;
	protected LinkedList<Particle> collided;

	public Particle(Point3d p, double r, Color c, Vector3d s, double m, double ch) {
		super(p, r, c);
		momentum = s;
		mass = m;
		charge = ch;
		collided = new LinkedList<Particle>();
	}

	public void calculateTrajectory(LinkedList<Particle> balls) {
		for (Particle b : balls)
			if (b != this) {
				// F = G(mOne*mTwo)/r^2 for attraction G = gravitational constant =
				// 6.67428x10^-11 (distance^3)/(mass * time^2)
				Vector3d dist = new Vector3d(origin.x - b.origin.x, origin.y - b.origin.y, origin.z - b.origin.z);
				double force = -1 * (charge * b.charge) / (dist.length() * dist.length());
				force += .000000006 * ((mass * b.mass) / (dist.length() * dist.length()));
				Vector3d directionOfForce = new Vector3d(b.origin.x - origin.x, b.origin.y - origin.y,
						b.origin.z - origin.z);
				momentum.x += force * directionOfForce.x / directionOfForce.length();
				momentum.y += force * directionOfForce.y / directionOfForce.length();
				momentum.z += force * directionOfForce.z / directionOfForce.length();
			}
	}

	public void move(double num) {
		origin.x += ((momentum.x) / mass) / num;
		origin.y += ((momentum.y) / mass) / num;
		origin.z += ((momentum.z) / mass) / num;
		clear();
		// if (origin.x >= 1 || origin.x <= -1)
		// momentum.x = -momentum.x;
		// if (origin.y >= 1 || origin.y <= -1)
		// momentum.y = -momentum.y;
		// if (origin.z >= 1 || origin.z <= -1)
		// momentum.z = -momentum.z;
	}

	public void clear() {
		collided = new LinkedList<Particle>();
	}

	public void show() {
		Transform3D temp = new Transform3D();
		temp.setTranslation(new Vector3d(origin.x, origin.y, origin.z));
		setTransform(temp);
	}

	public boolean checkCollisions(LinkedList<Particle> particles) {
		boolean val = false;
		for (Particle p : particles)
			if (p != this) {
				Vector3d dist = new Vector3d(origin.x - p.origin.x, origin.y - p.origin.y, origin.z - p.origin.z);
				if (dist.length() <= radius + p.radius && (collided.size() == 0 || !hasBeenHitBy(p))) {
					val = true;
					// double q = calcBackupFactor(this, p);
					// moveBackward(q); p.moveBackward(q);
					Vector3d dir = new Vector3d(origin.x - p.origin.x, origin.y - p.origin.y, origin.z - p.origin.z);

					double thetaX;
					if (dir.y != 0)
						thetaX = Math.atan(dir.z / dir.y);
					else
						thetaX = 0;
					rotateX(2 * Math.PI - thetaX, dir, momentum, p.momentum);
					double thetaZ;
					if (dir.x != 0)
						thetaZ = Math.atan(dir.y / dir.x);
					else
						thetaZ = 0;
					rotateZ(2 * Math.PI - thetaZ, momentum, p.momentum);
					// if (momentum.x > 0)
					// if (p.momentum.x > 0)
					// if (p.momentum.x < momentum.x)
					// {
					// double temp = momentum.x;
					// momentum.x = p.momentum.x;
					// p.momentum.x = temp;
					// }
					// else;
					// else
					// {
					// double temp = momentum.x;
					// momentum.x = p.momentum.x;
					// p.momentum.x = temp;
					// }
					// else
					// if (p.momentum.x < 0)
					// if (p.momentum.x < momentum.x)
					// {
					// double temp = momentum.x;
					// momentum.x = p.momentum.x;
					// p.momentum.x = temp;
					// }
					// else;
					// else;
					double temp = momentum.x;
					momentum.x = p.momentum.x;
					p.momentum.x = temp;
					rotateZ(thetaZ, momentum, p.momentum);
					rotateX(thetaX, momentum, p.momentum);
					// moveForward(q); p.moveForward(q);
					p.addCollision(this);
				}
			}
		return val;
	}

	public static void rotateX(double theta, Tuple3d... vecs) {
		double sin = Math.sin(theta), cos = Math.cos(theta);

		for (Tuple3d v : vecs) {
			double newY = v.y * cos - v.z * sin;
			double newZ = v.y * sin + v.z * cos;
			v.y = newY;
			v.z = newZ;
		}
	}

	public static void rotateZ(double theta, Tuple3d... vecs) {
		double sin = Math.sin(theta), cos = Math.cos(theta);

		for (Tuple3d v : vecs) {
			double newX = v.x * cos - v.y * sin;
			double newY = v.x * sin + v.y * cos;
			v.x = newX;
			v.y = newY;
		}
	}

	public boolean addCollision(Particle b) {
		return collided.add(b);
	}

	public boolean hasBeenHitBy(Particle ball) {
		for (Particle b : collided)
			if (ball == b)
				return true;

		return false;
	}

	public boolean outOfBounds() {
		return origin.x > 100000 || origin.x < -100000 || origin.y > 100000 || origin.y < -100000 || origin.z > 100000
				|| origin.z < -100000;
	}

	public void keepIn(double B) {
		if (origin.x > B || origin.x < -B)
			momentum.x *= -1;
		if (origin.y > B || origin.y < -B)
			momentum.y *= -1;
		if (origin.z > B || origin.z < -B)
			momentum.z *= -1;
	}

	public static double calcBackupFactor(Particle one, Particle two) {
		// test for points on 'graph'
		double qResultOne = findQResult(1, one, two), qResultTwo = findQResult(2, one, two);
		double m = qResultTwo - qResultOne;
		double b = qResultOne - m;

		return -b / m;
	}

	private static double findQResult(double q, Particle one, Particle two) {
		return Math.sqrt(Math
				.pow((one.origin.x - q * one.momentum.x / one.mass) - (two.origin.x - q * two.momentum.x / two.mass), 2)
				+ Math.pow(
						(one.origin.y - q * one.momentum.y / one.mass) - (two.origin.y - q * two.momentum.y / two.mass),
						2)
				+ Math.pow(
						(one.origin.z - q * one.momentum.z / one.mass) - (two.origin.z - q * two.momentum.z / two.mass),
						2))
				- one.radius - two.radius;
	}

	private void moveBackward(double q) {
		origin.x -= q * momentum.x / mass;
		origin.y -= q * momentum.y / mass;
		origin.z -= q * momentum.z / mass;
	}

	private void moveForward(double q) {
		origin.x += q * momentum.x / mass;
		origin.y += q * momentum.y / mass;
		origin.z += q * momentum.z / mass;
	}
}