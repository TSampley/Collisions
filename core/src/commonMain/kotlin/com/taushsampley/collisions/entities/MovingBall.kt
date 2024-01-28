package com.taushsampley.collisions.entities

import kotlin.math.PI
import kotlin.math.atan
import kotlin.math.cos
import kotlin.math.sin
import kotlin.math.sqrt

// region Jogl types
data class Vector(
    var x: Float,
    var y: Float,
    var z: Float
) {
    val length: Float
        get() = sqrt(x*x + y*y + z*z)
}
// endregion

class MovingBall(
    point: Point,
    radius: Float,
    color: Color,
    val momentum: Vector,
    val mass: Float
): Ball(origin = point, radius = radius, color = color) {

    private var collided = mutableListOf<MovingBall>()

    fun calculateTrajectory(balls: List<MovingBall?>) {
        var i = 0
        for (b in balls) if (b !== this && b != null) {
            // F = G(mOne*mTwo)/r^2 for attraction G = gravitational constant =
            // 6.67428x10^-11 (distance^3)/(mass * time^2)
            val dist = Vector(origin.x - b.origin.x, origin.y - b.origin.y, origin.z - b.origin.z)
            val force: Float = .000000006f * (mass * b.mass / (dist.length * dist.length))
            val directionOfForce = Vector(
                b.origin.x - origin.x, b.origin.y - origin.y,
                b.origin.z - origin.z
            )
            momentum.x += force * directionOfForce.x / directionOfForce.length
            momentum.y += force * directionOfForce.y / directionOfForce.length
            momentum.z += force * directionOfForce.z / directionOfForce.length
            // System.out.println(directionOfForce + " " + i);
            i++
        }
        // System.out.println(momentum);
    }

    fun move(num: Float) {
        origin.x += momentum.x / mass / num
        origin.y += momentum.y / mass / num
        origin.z += momentum.z / mass / num
        collided = mutableListOf()
        // if (origin.x >= 1 || origin.x <= -1)
        // momentum.x = -momentum.x;
        // if (origin.y >= 1 || origin.y <= -1)
        // momentum.y = -momentum.y;
        // if (origin.z >= 1 || origin.z <= -1)
        // momentum.z = -momentum.z;
    }

    fun checkCollisions(balls: List<MovingBall?>) {
        for (b in balls) if (b !== this && b != null) {
            val dist = Vector(origin.x - b.origin.x, origin.y - b.origin.y, origin.z - b.origin.z)
            if (dist.length <= radius + b.radius && (collided.size == 0 || !hasBeenHitBy(b))) {
                val dir =
                    Vector(origin.x - b.origin.x, origin.y - b.origin.y, origin.z - b.origin.z)
                var thetaX: Double
                thetaX = if (dir.y != 0f) atan(dir.z.toDouble() / dir.y) else 0.0
                MovingBall.Companion.rotateX(
                    2 * PI - thetaX,
                    dir,
                    momentum,
                    b.momentum
                )
                var thetaZ: Double
                thetaZ = if (dir.x != 0f) atan(dir.y.toDouble() / dir.x) else 0.0
                MovingBall.Companion.rotateZ(
                    2 * PI - thetaZ,
                    dir,
                    momentum,
                    b.momentum
                )
                val temp = momentum.x
                momentum.x = b.momentum.x
                b.momentum.x = temp
                MovingBall.Companion.rotateZ(thetaZ, momentum, b.momentum)
                MovingBall.Companion.rotateX(thetaX, momentum, b.momentum)
                b.addCollision(this)
            }
            // System.out.println(dist.length);
        }
    }

    fun addCollision(b: MovingBall): Boolean {
        return collided.add(b)
    }

    fun hasBeenHitBy(ball: MovingBall): Boolean {
        for (b in collided) if (ball === b) return true
        return false
    }

    fun outOfBounds(): Boolean {
        return origin.x > 100000 || origin.x < -100000 || origin.y > 100000 || origin.y < -100000 || origin.z > 100000 || origin.z < -100000
    }

    fun keepIn() {
        if (origin.x > 1 || origin.x < -1) momentum.x *= -1
        if (origin.y > 1 || origin.y < -1) momentum.y *= -1
        if (origin.z > 1 || origin.z < -1) momentum.z *= -1
    }

    companion object {
        fun rotateX(theta: Double, vararg vecs: Vector) {
            val sin = sin(theta)
            val cos = cos(theta)
            for (v in vecs) {
                val newY = v.y * cos - v.z * sin
                val newZ = v.y * sin + v.z * cos
                v.y = newY.toFloat()
                v.z = newZ.toFloat()
            }
        }

        fun rotateZ(theta: Double, vararg vecs: Vector) {
            val sin = sin(theta)
            val cos = cos(theta)
            for (v in vecs) {
                val newX: Double = v.x * cos - v.y * sin
                val newY: Double = v.x * sin + v.y * cos
                v.x = newX.toFloat()
                v.y = newY.toFloat()
            }
        }
    }
}
