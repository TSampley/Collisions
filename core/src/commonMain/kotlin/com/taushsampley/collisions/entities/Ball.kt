package com.taushsampley.collisions.entities



// region Jogl types
data class Color(
    val red: Float,
    val green: Float,
    val blue: Float
) {
    companion object {
        val Black = Color(0f, 0f, 0f)
        val Blue = Color(0f, 0f, 1f)
        val Gray = Color(.5f, .5f, .5f)
        val Magenta = Color(1f, 0f, 1f)
        val White = Color(1f, 1f, 1f)
        val Yellow = Color(1f, 1f, 0f)
    }
}
data class Appearance(
    val ambient: Color,
    val emmisive: Color,
    val diffuse: Color,
    val specular: Color,
    val shinniness: Float
) {
    companion object {
        val Default = Appearance(Color.Black, Color.Black, Color.Black, Color.Black, 10f)
    }
}
class Sphere(
    val radius: Float,
    var appearance: Appearance = Appearance.Default
) {

}
data class Point(
    var x: Float,
    var y: Float,
    var z: Float
)
// endregion


open class Ball(
    val origin: Point,
    val radius: Float,
    val color: Color
) {

    private val s: Sphere = Sphere(radius).also {
        it.appearance = Appearance(
            color,
            Color(.2f, .2f, .2f),
            color,
            Color.White,
            10f
        )
    }

    fun act() {}
}
