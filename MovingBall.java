
import javax.media.j3d.*;
import javax.vecmath.*;
import java.awt.Color;
import java.util.LinkedList;

public class MovingBall extends Ball
{
	/* inherited from Ball
	private Sphere s;
	protected Point3f origin;
	protected float radius;
	private Color3f color; */
	
	protected Vector3d momentum;
	protected double mass;
	protected LinkedList<MovingBall> collided;
	
    public MovingBall(Point3d p, double r, Color c, Vector3d s, double m)
    {
    	super(p, r, c);
    	momentum = s;
    	mass = m;
    	collided = new LinkedList<MovingBall>();
    }
    
    public void calculateTrajectory(LinkedList<MovingBall> balls)
    {
    	int i = 0;
    	for (MovingBall b : balls)
    		if (b != this)
    		{
    			//F = G(mOne*mTwo)/r^2  for attraction   G = gravitational constant = 6.67428x10^-11 (distance^3)/(mass * time^2)
    			Vector3d dist = new Vector3d(origin.x - b.origin.x, origin.y - b.origin.y, origin.z - b.origin.z);
    			double force = .000000006 * ((mass * b.mass)/(dist.length() * dist.length()));
    			Vector3d directionOfForce = new Vector3d(b.origin.x - origin.x, b.origin.y - origin.y, b.origin.z - origin.z);
    			momentum.x += force * directionOfForce.x / directionOfForce.length();
    			momentum.y += force * directionOfForce.y / directionOfForce.length();
    			momentum.z += force * directionOfForce.z / directionOfForce.length();
 //   			System.out.println(directionOfForce + "   " + i);
    			i++;
    		}
//    		System.out.println(momentum);
    }
    
    public void move(float num)
    {
		origin.x += ((momentum.x)/mass)/num;
    	origin.y += ((momentum.y)/mass)/num;
    	origin.z += ((momentum.z)/mass)/num;
    	collided = new LinkedList<MovingBall>();
//    	if (origin.x >= 1 || origin.x <= -1)
//    		momentum.x = -momentum.x;
//    	if (origin.y >= 1 || origin.y <= -1)
//    		momentum.y = -momentum.y;
//    	if (origin.z >= 1 || origin.z <= -1)
//    		momentum.z = -momentum.z;
    }
    
    public void show()
    {
		Transform3D temp = new Transform3D();
    	temp.setTranslation(new Vector3d(origin.x, origin.y, origin.z));
//	   	System.out.println(temp);
    	setTransform(temp);
    }
    
    public void checkCollisions(LinkedList<MovingBall> balls)
    {
    	for (MovingBall b : balls)
    		if (b != this)
    		{
    			Vector3d dist = new Vector3d(origin.x - b.origin.x, origin.y - b.origin.y, origin.z - b.origin.z);
    			if (dist.length() <= radius + b.radius && (collided.size() == 0 || !hasBeenHitBy(b)))
    			{
    				Vector3d dir = new Vector3d(origin.x - b.origin.x, origin.y - b.origin.y, origin.z - b.origin.z);
    				double thetaX;
    				if (dir.y != 0)
    					thetaX = Math.atan(dir.z / dir.y);
    				else
    					thetaX = 0;
    				rotateX(2*Math.PI - thetaX, dir, momentum, b.momentum);
    				double thetaZ;
    				if (dir.x != 0)
    					thetaZ = Math.atan(dir.y / dir.x);
    				else
    					thetaZ = 0;
    				rotateZ(2*Math.PI - thetaZ, dir, momentum, b.momentum);
    				double temp = momentum.x;
    				momentum.x = b.momentum.x;
    				b.momentum.x = temp;
    				rotateZ(thetaZ, momentum, b.momentum);
    				rotateX(thetaX, momentum, b.momentum);
    				    				
    				b.addCollision(this);
    			}
 //   			System.out.println(dist.length());
    		}
    }
    
    public static void rotateX(double theta, Vector3d ... vecs)
    {
    	double sin = Math.sin(theta), cos = Math.cos(theta);
    	
    	for (Vector3d v : vecs)
    	{
    		double newY = v.y * cos - v.z * sin;
    		double newZ = v.y * sin + v.z * cos;
    		v.y = newY;
    		v.z = newZ;
    	}
    }
    
    public static void rotateZ(double theta, Vector3d ... vecs)
    {
    	double sin = Math.sin(theta), cos = Math.cos(theta);
    	
    	for (Vector3d v : vecs)
    	{
    		double newX = v.x * cos - v.y * sin;
    		double newY = v.x * sin + v.y * cos;
    		v.x = newX;
    		v.y = newY;
    	}
    }
    
    public boolean addCollision(MovingBall b)
    {
    	return collided.add(b);
    }
    
    public boolean hasBeenHitBy(MovingBall ball)
    {
    	for (MovingBall b : collided)
    		if (ball == b)
    			return true;
    			
    	return false;
    }
    
    public boolean outOfBounds()
    {
    	return origin.x > 100000 || origin.x < -100000 || origin.y > 100000 || origin.y < -100000 || origin.z > 100000 || origin.z < -100000;
    }
    
    public void keepIn()
    {
    	if (origin.x > 1 || origin.x < -1)
    		momentum.x *= -1;
    	if (origin.y > 1 || origin.y < -1)
    		momentum.y *= -1;
    	if (origin.z > 1 || origin.z < -1)
    		momentum.z *= -1;
    }
}