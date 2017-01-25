package io.github.mamachanko

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class BlackPaletteTests {

    @Test
    fun `should return solid black`() {
        assertThat(BlackPalette().color).isEqualTo(Color(0, 0, 0, 1.0))
    }

}

class GrayPaletteTests {

    @Test
    fun `should return solid gray`() {
        val gray = GrayPalette().color
        assertThat(gray.red).isEqualTo(gray.green)
        assertThat(gray.red).isEqualTo(gray.blue)
        assertThat(gray.alpha).isEqualTo(1.0)
    }

    @Test
    fun `should return random solid gray`() {
        assertThat(GrayPalette().color).isNotEqualTo(GrayPalette().color)
    }

}

class ColorPaletteTests {

    @Test
    fun `should always return the same color if given one`() {
        val green = Color(120, 240, 120, .5)
        assertThat(ColorPalette(green).color).isEqualTo(ColorPalette(green).color)
    }

    @Test
    fun `should return any of the given colors`() {
        val green = Color(120, 240, 120, .5)
        val red = Color(180, 45, 45, 1.0)
        val blue = Color(0, 0, 190, 1.0)

        assertThat(ColorPalette(red, blue).color).isNotEqualTo(ColorPalette(green).color)
    }
}

class RandomPaletteTests {
    @Test
    fun `should return random color`() {
        assertThat(RandomPalette().color).isNotEqualTo(RandomPalette().color)
    }
}
