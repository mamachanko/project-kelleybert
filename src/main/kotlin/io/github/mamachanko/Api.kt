package io.github.mamachanko

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class Api {

    @GetMapping("/api/shapes")
    fun shapes(@RequestParam("width", required = true) width: Double, @RequestParam("height", required = true) height: Double): ShapesResponse {
        val shape = Shape(vertices = setOf(Vertex(.0, .0), Vertex(.0, height), Vertex(width, .0), Vertex(width, height)))
        val vertices = shape.getSortedVertices().map { vertex -> VertexResource(x = vertex.x, y = vertex.y) }
        val color = ColorResource(red = shape.color.red, green = shape.color.green, blue = shape.color.blue, alpha = shape.color.alpha)
        return ShapesResponse(listOf(ShapeResource(vertices, color)))
    }

}

data class ShapesResponse(val shapes: List<ShapeResource>)

data class ShapeResource(val vertices: List<VertexResource>, val color: ColorResource)

data class VertexResource(val x: Double, val y: Double)

data class ColorResource(val red: Int, val green: Int, val blue: Int, val alpha: Double)