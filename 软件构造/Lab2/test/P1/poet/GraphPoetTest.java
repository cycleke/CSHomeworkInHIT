/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
* Redistribution of original or derived work requires permission of course staff.
*/
package P1.poet;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;

import org.junit.Test;

/**
 * Tests for GraphPoet.
 */
public class GraphPoetTest {

	/**
	 * Testing strategy
	 *
	 * For {@link poet.GraphPoet#poem(String)}
	 * cases:
	 *  the word exists/doesn't exist in the graph
 	 *  there is/isn't two-edge-long path between given words
	 */

	@Test(expected = AssertionError.class)
	public void testAssertionsEnabled() {
		assert false; // make sure assertions are enabled with VM argument: -ea
	}

	/**
	 * test for {@link poet.GraphPoet#poem(String)}
	 */
	@Test
	public void testPoem() {
		File testFile = new File("test/P1/poet/test.txt");
		GraphPoet poet = null;
		try {
			poet = new GraphPoet(testFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
		assertEquals("", poet.poem(""));
		assertEquals("I there", poet.poem("I there"));
		assertEquals("alice is here", poet.poem("alice is here"));
		assertEquals("bob is now here", poet.poem("bob now here"));
		assertEquals("he is now there", poet.poem("he is there"));
	}
}
