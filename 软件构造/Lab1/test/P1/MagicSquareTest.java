package P1;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class MagicSquareTest {
	@Test
	public void isLegalMagicSquareTest() {
		boolean[] corrects = new boolean[] { true, true, false, false, false, true };
		for (int i = 0; i < 6; ++i) {
			String fileName = String.format("src/P1/txt/%d.txt", i + 1);
			assertEquals(corrects[i], MagicSquare.isLegalMagicSquare(fileName));
		}
	}

	@Test
	public void generateMagicSquareTest()  {
		assertEquals(false, MagicSquare.generateMagicSquare(0));
		assertEquals(false, MagicSquare.generateMagicSquare(2));
		assertEquals(true, MagicSquare.generateMagicSquare(101));
	}
}
