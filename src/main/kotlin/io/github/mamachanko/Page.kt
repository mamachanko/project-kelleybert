package io.github.mamachanko

data class Grid(val numberOfColumns: Int, val numberOfRows: Int)

data class Layout(val horizontalMargin: Int, val verticalMargin: Int, val tileMargin: Int)

class Page(val width: Int, val height: Int, val layout: Layout = Layout(0, 0, 0), val grid: Grid = Grid(1, 1), val palette: Palette = BlackPalette()) {

    var tiles: List<Tile> = generateTiles()

    private fun generateTiles(): List<Tile> {
        var generatedTiles = emptyList<Tile>().toMutableList()
        (0..grid.numberOfColumns - 1).forEach { column ->
            (0..grid.numberOfRows - 1).forEach { row ->
                generatedTiles.add(Tile(
                        x = getXForColumn(column),
                        y = getYForRow(row),
                        width = getTileWidth(),
                        height = getTileHeight(),
                        palette = palette))
            }
        }
        return generatedTiles.toList()
    }

    private fun getYForRow(row: Int) = layout.verticalMargin + row * getTileHeight() + layout.tileMargin * row

    private fun getXForColumn(column: Int) = layout.horizontalMargin + column * getTileWidth() + layout.tileMargin * column

    private fun getTileHeight() = (height - layout.verticalMargin * 2 - layout.tileMargin * (grid.numberOfRows - 1)) / grid.numberOfRows

    private fun getTileWidth() = (width - layout.horizontalMargin * 2 - layout.tileMargin * (grid.numberOfColumns - 1)) / grid.numberOfColumns

    val shapes: List<Shape>
        get() {
            var shapes = emptyList<Shape>().toMutableList()
            tiles.forEach { tile ->
                tile.shapes.forEach { shape ->
                    shapes.add(shape)
                }
            }
            return shapes.toList()
        }
}

data class Tile(val x: Int, val y: Int, val width: Int, val height: Int, val palette: Palette) {
    val shapes: List<Shape> = listOf(
            Shape(vertices = setOf(
                    Vertex(x.toDouble(), y.toDouble()),
                    Vertex(x.toDouble(), y.toDouble() + height.toDouble()),
                    Vertex(x.toDouble() + width.toDouble(), y.toDouble()),
                    Vertex(x.toDouble() + width.toDouble(), y.toDouble() + height.toDouble())),
                    color = palette.color)
    )
}