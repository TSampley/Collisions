package com.taushsampley.collisions.entities



// region Jogl types
data class Color(
    val red: Float,
    val green: Float,
    val blue: Float
) {
    companion object {
        val Black = Color(0f, 0f, 0f)
        val White = Color(1f, 1f, 1f)
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
interface Point {}
// endregion


class Ball(
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
