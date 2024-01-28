package com.taushsampley.collisions.ui

import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.taushsampley.collisions.entities.Color
import com.taushsampley.collisions.entities.Particle
import com.taushsampley.collisions.entities.Point
import com.taushsampley.collisions.entities.Vector

// region Javax types
interface Timer {

}
// endregion
// region Java3D types
interface BranchGroup {}
interface DirectionalLight {}
// endregion

data class CollisionState(
    val particles: List<Particle>,

)


@Composable
fun rememberCollisionState(
    particles: List<Particle> = defaultParticles()
) = remember(particles) {
    CollisionState(
        particles = particles
    )
}


fun defaultParticles(): List<Particle> {
    return listOf(

        Particle(
            Point(-.3f, 0f, .3f),
            .05f,
            Color.Blue,
            Vector(20f, 0f, -20f),
            1000f,
            0f
        ),

        Particle(
            Point(0f, 0f, 0f),
            .05f,
            Color.Yellow,
            Vector(0f, 0f, 0f),
            1000f,
            0f
        ),

        Particle(
            Point(.3f, 0f, -.3f),
            .05f,
            Color.Magenta,
            Vector(0f, 0f, 0f),
            1000f,
            0f
        )
        ,
        Particle(
            Point(0f, 0f, 0f),
            .05f,
            Color.Gray,
            Vector(0f, 0f, 0f),
            2000f,
            0f
        )
    )
}

@Composable
fun CollisionView(
    state: CollisionState = rememberCollisionState(),
    modifier: Modifier = Modifier,
) {
    Canvas(
        modifier = modifier
    ) {

    }
}

class CollisionGrapher /* :javax.swing.JPanel() TODO(create compose CollisionView): */ {

//    var tmr: Timer
//    var group: BranchGroup
//    var VpTG: TransformGroup
//    var activeUniverse: SimpleUniverse
//    var light: DirectionalLight
    var zoom: Float
    var yes = false

    init {
        // region Legacy setups

        // balls.add(new MovingBall(new Point(0, 0, 0), .07, Color.yellow, new
        // Vector(0, 0, 0), 986700000)); //sun
        // balls.add(new MovingBall(new Point(0.73, 0, 0), .0063765, new Color(0, 100,
        // 255), new Vector(0, 900000, 0), 299)); //earth
        // int num = 180;
        // balls.add(new MovingBall(new Point(1, 0, 0), .05, Color.yellow, new
        // Vector(0, -1000000000000d, 0), 986700000));
        // balls.add(new MovingBall(new Point(-1, 0, 0), .05, Color.yellow, new
        // Vector(0, 1000000000000d, 0), 986700000));
        // for (int i = 0; i < num; i++)
        // balls.add(new MovingBall(new Point(Math.cos(Math.toRadians(i * 360/num)) *
        // 0.73 + .74, Math.sin(Math.toRadians(i * 360/num)) * 0.73, 0), .01, new
        // Color(0, 100, 255), new Vector(Math.cos(Math.toRadians(i * 360/num + 90)) *
        // 3600000d, Math.sin(Math.toRadians(i * 360/num + 90)) * 3600000d, 0), 29900));
        // for (int i = 0; i < num; i++)
        // balls.add(new MovingBall(new Point(Math.cos(Math.toRadians(i * 360/num)) *
        // 0.73 - .74, Math.sin(Math.toRadians(i * 360/num)) * 0.73, 0), .01, new
        // Color(0, 100, 255), new Vector(Math.cos(Math.toRadians(i * 360/num + 90)) *
        // 3600000d, Math.sin(Math.toRadians(i * 360/num + 90)) * 3600000d, 0), 29900));

        // Creates a ring of balls-----------
        // double momentum = 0;
        // double r = 4;
        // int num = 90;
        // for (int i = 1; i < num; i++)
        // balls.add(new Particle(new Point(Math.cos(Math.toRadians(i * 360/num)) * r,
        // Math.sin(Math.toRadians(i * 360/num)) * r, 0), .1, Color.yellow, new
        // Vector(Math.cos(Math.toRadians(i * 360/num + 90)) * momentum,
        // Math.sin(Math.toRadians(i * 360/num + 90)) * momentum, 0), .01, 0));
        //// for (int i = 0; i < num; i++)
        //// balls.add(new Particle(new Point(0, Math.cos(Math.toRadians(i * 360/num))
        // * 1.5 + .75, Math.sin(Math.toRadians(i * 360/num)) * 1.5), .1, Color.blue,
        // new Vector(Math.cos(Math.toRadians(i * 360/num + 90)) * momentum,
        // Math.sin(Math.toRadians(i * 360/num + 90)) * momentum, 0), 1000, -1));
        // balls.add(new Particle(new Point(r, 0, 0), .1, Color.yellow, new
        // Vector(0, .0005, 0), .01, 0));
        // r = 2.6;
        // num = 60;
        // for (int i = 1; i < num; i++)
        // balls.add(new Particle(new Point(Math.cos(Math.toRadians(i * 360/num)) * r,
        // Math.sin(Math.toRadians(i * 360/num)) * r, 0), .1, Color.green, new
        // Vector(Math.cos(Math.toRadians(i * 360/num + 90)) * momentum,
        // Math.sin(Math.toRadians(i * 360/num + 90)) * momentum, 0), .01, 0));
        // balls.add(new Particle(new Point(r, 0, 0), .1, Color.green, new Vector(0,
        // .0005, 0), .01, 0));
        // r = 1.2;
        // num = 30;
        // for (int i = 1; i < num; i++)
        // balls.add(new Particle(new Point(Math.cos(Math.toRadians(i * 360/num)) * r,
        // Math.sin(Math.toRadians(i * 360/num)) * r, 0), .1, Color.blue, new
        // Vector(Math.cos(Math.toRadians(i * 360/num + 90)) * momentum,
        // Math.sin(Math.toRadians(i * 360/num + 90)) * momentum, 0), .01, 0));
        // balls.add(new Particle(new Point(r, 0, 0), .1, Color.blue, new Vector(0,
        // .0005, 0), .1, 0));
        // balls.add(new Particle(new Point(0, 0, 0), .5, Color.gray, new Vector(0,
        // 0, 0), 10000, 0));

        // Creates a Cube of balls-----------
        // for (double x = -3; x <= 3; x++)
        // for (double y = -3; y <= 3; y++)
        // for (double z = -3; z <= 3; z++)
        // balls.add(new Particle(new Point(x/2, y/2, z/2), .15, Color.green, new
        // Vector(0, 0, 0), 100, 0));

        // Creates a Sphere of balls---------
        // Vector vec = new Vector(0, 0, 3);
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
        // balls.add(new Particle(new Point(vec.x, vec.y, vec.z), .1, Color.green, new
        // Vector(0, 0, 0), 1000, .00));
        // }
        // }
        //
        // balls.add(new Particle(new Point(0, 0, 0), .1, Color.green, new Vector(0,
        // 0, 0), 1, balls.size() * -.005));

        // for (int i = 0; i < num; i++)
        // balls.add(new Particle(new Point(Math.cos(Math.toRadians(i * 360/num)) *
        // 1.5, Math.sin(Math.toRadians(i * 360/num)) * 1.5, 0), .1, Color.yellow, new
        // Vector(Math.cos(Math.toRadians(i * 360/num + 90)) * momentum,
        // Math.sin(Math.toRadians(i * 360/num + 90)) * momentum, 0), 600, 0));
        //
        // double num = 6.6;
        // double theta = 360 / num;
        // for (int i = 0; i < num; i++)
        // balls.add(new Particle(new Point(Math.cos(Math.toRadians(i * theta + .5 *
        // theta)) * 1.5, Math.sin(Math.toRadians(i * theta + .5 * theta)) * 1.5, 0),
        // .1, Color.yellow, new Vector(Math.cos(Math.toRadians(i * theta + 90)) *
        // momentum, Math.sin(Math.toRadians(i * theta + 90)) * momentum, 0), 600, 2));
        //
        // for (int i = 0; i < num; i++)
        // balls.add(new Particle(new Point(Math.cos(Math.toRadians(i * theta)) * 1.5,
        // Math.sin(Math.toRadians(i * theta)) * 1.5, 0), .1, Color.blue, new
        // Vector(Math.cos(Math.toRadians(i * theta + 90)) * momentum,
        // Math.sin(Math.toRadians(i * theta + 90)) * momentum, 0), 600, -2));

        // for (int i = 0; i < 50; i++)
        // {
        // balls.add(new Particle(new Point(Math.random() * 2 - 1, Math.random() * 2 -
        // 1, Math.random() * 2 - 1), .05, new Color(0, 100, 255), new
        // Vector(Math.random()*8 - 4, Math.random()*8 - 4, Math.random()*8 - 4), 200,
        // 0));
        // }
        // for (int i = 0; i < 50; i++)
        // {
        // balls.add(new Particle(new Point(Math.random() * 2 - 1, Math.random() * 2 -
        // 1, Math.random() * 2 - 1), .07, new Color(255, 100, 0), new
        // Vector(Math.random()*8 - 4, Math.random()*8 - 4, Math.random()*8 - 4), 400,
        // 0));
        // }
        // for (int i = 0; i < 50; i++)
        // {
        // balls.add(new Particle(new Point(Math.random() * 2 - 1, Math.random() * 2 -
        // 1, Math.random() * 2 - 1), .1, new Color(255, 0, 255), new
        // Vector(Math.random()*8 - 4, Math.random()*8 - 4, Math.random()*8 - 4), 800,
        // 0));
        // }
        // balls.add(new Particle(new Point(0, -.5, 0), 1, new Color(0, 100, 255), new
        // Vector(0, -.01, 0), .5, 0));
        // balls.add(new Particle(new Point(0, .5, 0), 1, new Color(0, 100, 255), new
        // Vector(0, .01, 0), .5, 0));
        // balls.add(new Particle(new Point(-.1, 0, 0), .1, new Color(0, 100, 255),
        // new Vector(0, 0, 0), .1, 0));
        // balls.add(new Particle(new Point(.3, 0, 0), .1, new Color(0, 100, 255), new
        // Vector(-15, -15, 0), 5000, 0));
        // balls.add(new Particle(new Point(0, -1, 0), .05, new Color(0, 100, 255),
        // new Vector(2200, 0, 0), 90000, 0));
        // balls.add(new Particle(new Point(0, 1, 0), .05, new Color(0, 100, 255), new
        // Vector(-2200, 0, 0), 250000, 0));
        //
        // for (int i = -4; i < 5; i++)
        // for (int j = -4; j < 5; j++)
        // balls.add(new MovingBall(new Point(i/2.0, j/2.0, 0), .1, new Color(100,
        // 100, 100), new Vector(0, 0, 0), 1000000d));

        // endregion

        // -----------------------------------------------------------

        // region Java3D rendering setup
//        val canvas = Canvas3D(SimpleUniverse.getPreferredConfiguration())
//        val universe = SimpleUniverse(canvas)
//        activeUniverse = universe
//        group = BranchGroup()
//        group.setCapability(BranchGroup.ALLOW_DETACH)
//        group.setCapability(BranchGroup.ALLOW_CHILDREN_WRITE)
//        for (b in balls) group.addChild(b)

        // setup lighting for the world
//        val light1Color = Color3f(1f, 1f, 1f)
//        val bounds = BoundingSphere(Point(0.0, 0.0, 0.0), 100.0) // standard bounds
//        val light1Direction = Vector3f(4.0f, -7.0f, -12.0f)
//        val light1 = DirectionalLight(light1Color, light1Direction)
//        light1.setInfluencingBounds(bounds)
        // --------------
//        VpTG = universe.getViewingPlatform().getViewPlatformTransform()
        zoom = 10.0f // put the camera 4 meters back
//        val Trfcamera = Transform3D()
//        Trfcamera.setTranslation(Vector(0.0f, 0.0f, zoom))
//        VpTG.setTransform(Trfcamera)
//        light = light1
//        group.addChild(light1)
//        universe.addBranchGraph(group)

        // -----------------------------------------------------------
//        setLayout(java.awt.BorderLayout())
//        add(canvas)
//        val b: javax.swing.JButton = javax.swing.JButton("Start")
//        b.addActionListener(ButtonListener())
//        b.addKeyListener(PauseListener())
//        b.setFocusable(true)
//        add("South", b)
//        setPreferredSize(java.awt.Dimension(700, 800))
//        tmr = javax.swing.Timer(1000 / 60, MoveListener())

        // endregion
    }

//    private inner class ControlPanel : javax.swing.JPanel(), KeyListener {
//        override fun keyReleased(e: KeyEvent) {}
//        override fun keyPressed(e: KeyEvent) {}
//        override fun keyTyped(e: KeyEvent) {}
//    }

//    private inner class ButtonListener : ActionListener {
//        override fun actionPerformed(e: ActionEvent) {
//            if (tmr.isRunning()) {
//                (e.getSource() as javax.swing.JButton).setText("Start")
//                tmr.stop()
//            } else {
//                (e.getSource() as javax.swing.JButton).setText("Stop")
//                tmr.start()
//            }
//        }
//    }

//    private inner class MoveListener : ActionListener {
//        override fun actionPerformed(e: ActionEvent) {
//            for (i in balls.indices.reversed()) {
//                balls.get(i).calculateTrajectory(balls)
//                balls.get(i).move(1)
//                // balls.get(i).keepIn(2);
//                if (balls.get(i).outOfBounds()) {
//                    balls.removeAt(i)
//                    println("out")
//                } else balls.get(i).show()
//            }
//            // for (Particle b : balls)
//            // {
//            //// b.keepIn();
//            // b.calculateTrajectory(balls);
//            // while(b.checkCollisions(balls));
//            // }
//            // boolean repeat = false;
//            // do
//            // {
//            // repeat = false;
//            // for (Particle b : balls)
//            // {
//            // repeat = b.checkCollisions(balls) || repeat;
//            // }
//            // if (repeat)
//            // for (Particle b : balls)
//            // b.clear();
//            // } while (repeat);
//            for (b in balls) b.checkCollisions(balls)
//        }
//    }

//    private inner class PauseListener : KeyListener {
//        override fun keyReleased(e: KeyEvent) {}
//        override fun keyPressed(e: KeyEvent) {}
//        override fun keyTyped(e: KeyEvent) {
//            val Trfcamera = Transform3D()
//            when (e.getKeyCode()) {
//                KeyEvent.VK_UP -> {
//                    println(e.getKeyCode())
//                    zoom--
//                    Trfcamera.setTranslation(Vector(0.0f, 0.0f, zoom))
//                    VpTG.setTransform(Trfcamera)
//                }
//
//                KeyEvent.VK_DOWN -> {
//                    println(e.getKeyCode())
//                    zoom++
//                    Trfcamera.setTranslation(Vector(0.0f, 0.0f, zoom))
//                    VpTG.setTransform(Trfcamera)
//                }
//
//                KeyEvent.VK_SPACE -> {
//                    println(e.getKeyCode())
//                    tmr.stop()
//                }
//            }
//        }
//    }

//    fun addObject(p: Particle?) {
//        group.detach()
//        for (i in group.numChildren() downTo 1) group.removeChild(0)
//        balls.add(p)
//        for (particle in balls) {
//            group.addChild(particle)
//        }
//        group.addChild(light)
//        activeUniverse.addBranchGraph(group)
//    }
}
