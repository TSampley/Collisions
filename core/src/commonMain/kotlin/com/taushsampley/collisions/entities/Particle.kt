package com.taushsampley.collisions.entities

import kotlin.math.PI
import kotlin.math.atan
import kotlin.math.cos
import kotlin.math.pow
import kotlin.math.sin
import kotlin.math.sqrt

class Particle(
    origin: Point,
    radius: Float,
    color: Color,
    val momentum: Vector,
    val mass: Float,
    val charge: Float
): Ball(origin, radius, color) {

    protected var collided = mutableListOf<Particle>()

    fun calculateTrajectory(balls: List<Particle?>) {
        for (b in balls) if (b !== this && b != null) {
            // F = G(mOne*mTwo)/r^2 for attraction G = gravitational constant =
            // 6.67428x10^-11 (distance^3)/(mass * time^2)
            val dist = Vector(origin.x - b.origin.x, origin.y - b.origin.y, origin.z - b.origin.z)
            var force = -1 * (charge * b.charge) / (dist.length * dist.length)
            force += .000000006f * (mass * b.mass / (dist.length * dist.length))
            val directionOfForce = Vector(
                b.origin.x - origin.x, b.origin.y - origin.y,
                b.origin.z - origin.z
            )
            momentum.x += force * directionOfForce.x / directionOfForce.length
            momentum.y += force * directionOfForce.y / directionOfForce.length
            momentum.z += force * directionOfForce.z / directionOfForce.length
        }
    }

    fun move(num: Float) {
        origin.x += momentum.x / mass / num
        origin.y += momentum.y / mass / num
        origin.z += momentum.z / mass / num
        clear()
        // if (origin.x >= 1 || origin.x <= -1)
        // momentum.x = -momentum.x;
        // if (origin.y >= 1 || origin.y <= -1)
        // momentum.y = -momentum.y;
        // if (origin.z >= 1 || origin.z <= -1)
        // momentum.z = -momentum.z;
    }

    fun clear() {
        collided.clear()
    }


    fun checkCollisions(particles: List<Particle?>): Boolean {
        var `val` = false
        for (p in particles) if (p !== this && p != null) {
            val dist = Vector(origin.x - p.origin.x, origin.y - p.origin.y, origin.z - p.origin.z)
            if (dist.length <= radius + p.radius && (collided.size == 0 || !hasBeenHitBy(p))) {
                `val` = true
                // double q = calcBackupFactor(this, p);
                // moveBackward(q); p.moveBackward(q);
                val dir =
                    Vector(origin.x - p.origin.x, origin.y - p.origin.y, origin.z - p.origin.z)
                var thetaX: Double
                thetaX = if (dir.y != 0f) atan(dir.z.toDouble() / dir.y) else 0.0
                rotateX(2 * PI - thetaX, dir, momentum, p.momentum)
                var thetaZ: Double
                thetaZ = if (dir.x != 0f) atan(dir.y.toDouble() / dir.x) else 0.0
                rotateZ(2 * PI - thetaZ, momentum, p.momentum)
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
                val temp = momentum.x
                momentum.x = p.momentum.x
                p.momentum.x = temp
                rotateZ(thetaZ, momentum, p.momentum)
                rotateX(thetaX, momentum, p.momentum)
                // moveForward(q); p.moveForward(q);
                p.addCollision(this)
            }
        }
        return `val`
    }

    fun addCollision(b: Particle): Boolean {
        return collided.add(b)
    }

    fun hasBeenHitBy(ball: Particle): Boolean {
        for (b in collided) if (ball === b) return true
        return false
    }

    fun outOfBounds(): Boolean {
        return origin.x > 100000 || origin.x < -100000 || origin.y > 100000 || origin.y < -100000 || origin.z > 100000 || origin.z < -100000
    }

    fun keepIn(B: Double) {
        if (origin.x > B || origin.x < -B) momentum.x *= -1
        if (origin.y > B || origin.y < -B) momentum.y *= -1
        if (origin.z > B || origin.z < -B) momentum.z *= -1
    }

    private fun moveBackward(q: Float) {
        origin.x -= q * momentum.x / mass
        origin.y -= q * momentum.y / mass
        origin.z -= q * momentum.z / mass
    }

    private fun moveForward(q: Float) {
        origin.x += q * momentum.x / mass
        origin.y += q * momentum.y / mass
        origin.z += q * momentum.z / mass
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
                val newX = v.x * cos - v.y * sin
                val newY = v.x * sin + v.y * cos
                v.x = newX.toFloat()
                v.y = newY.toFloat()
            }
        }

        fun calcBackupFactor(one: Particle, two: Particle): Double {
            // test for points on 'graph'
            val qResultOne = findQResult(1.0, one, two)
            val qResultTwo = findQResult(2.0, one, two)
            val m = qResultTwo - qResultOne
            val b = qResultOne - m
            return -b / m
        }

        private fun findQResult(q: Double, one: Particle, two: Particle): Double {
            return sqrt(
                one.origin.x - q * one.momentum.x / one.mass - (two.origin.x - q * two.momentum.x / two.mass).pow(2.0) +
                        one.origin.y - q * one.momentum.y / one.mass - (two.origin.y - q * two.momentum.y / two.mass).pow(2.0) +
                        one.origin.z - q * one.momentum.z / one.mass - (two.origin.z - q * two.momentum.z / two.mass).pow(2.0)
            ) - one.radius - two.radius
        }
    }
}
