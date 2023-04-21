package com.cognixia;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class DemoTest {

//	@Test
//	void test() {
//		fail("Not yet implemented");
//	}
	
	@Test
	void testSorted() {
		int[] inAr = {1,2,3,4,5};
		for(int i = 1; i < inAr.length; i++) {
			assertTrue(inAr[i] > inAr[i-1]);
		}
	}
	
	@Test
	void oneLine(int[] inAr) {
		assertTrue(inAr.equals(inAr.sort()))
	}

}
