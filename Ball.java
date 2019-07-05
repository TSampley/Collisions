
import javax.media.j3d.*;
import javax.vecmath.*;
import com.sun.j3d.utils.geometry.Sphere;

import java.awt.Color;

public class Ball extends TransformGroup {
	private Sphere s;
	protected Point3d origin;
	protected double radius;
	private Color3f color;

	public Ball(Point3d p, double r, Color c) {
		super();
		setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);

		radius = r;
		s = new Sphere((float) r);
		origin = p;

		// setup light properties of the particles

		color = new Color3f();
		color.set(c);
		s.setAppearance(createAppearance(c, new Color(20, 20, 20), c, Color.white, 10f));

		addChild(s);

		Transform3D temp = new Transform3D();
		temp.setTranslation(new Vector3d(p.x, p.y, p.z));
		setTransform(temp);
	}

	private static Appearance createAppearance(Color ambient, Color emmisive, Color diffuse, Color specular,
			float shinniness) {
		Appearance ap = new Appearance();

		Color3f ambient3f = new Color3f();
		ambient3f.set(ambient);
		Color3f emmisive3f = new Color3f();
		emmisive3f.set(emmisive);
		Color3f diffuse3f = new Color3f();
		diffuse3f.set(diffuse);
		Color3f specular3f = new Color3f();
		specular3f.set(specular);

		ap.setMaterial(new Material(ambient3f, emmisive3f, diffuse3f, specular3f, shinniness));
		//
		// TransparencyAttributes ta = new TransparencyAttributes();
		// ta.setTransparencyMode(ta.NICEST);
		// ta.setTransparency(0.5f);
		//
		// ap.setTransparencyAttributes(ta);

		return ap;
	}

	public Point3d getOrigin() {
		return origin;
	}

	public void act() {
	}
}