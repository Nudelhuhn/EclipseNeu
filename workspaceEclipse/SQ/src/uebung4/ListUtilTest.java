package uebung4;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import uebung3.Taschenrechner;

class ListUtilTest
{

	@Test
	public void testGetLength()
	{
		int[] a = {1,2,3,4,5,6};
		assertEquals(6, ListUtil.getLength(a));
		int[] b = {0};
		assertEquals(1, ListUtil.getLength(b));
		int[] c = {};
		assertEquals(0, ListUtil.getLength(c));
		//Aufgabenblatt 5 Aufgabe 1 d) anhand von c)
		int[] d = null;
		assertThrows(RuntimeException.class, () ->
					{
						ListUtil.getLength(d);
					}
				);
	}
	
	@Test
	public void testIsEmpty() {
		int[] a = {1,2,3,4,5,6};
		assertEquals(false, ListUtil.isEmpty(a));
		int[] b = {0};
		assertEquals(false, ListUtil.isEmpty(b));
		int[] c = {};
		assertEquals(true, ListUtil.isEmpty(c));
		//Aufgabenblatt 5 Aufgabe 1 d) anhand von c)
				int[] d = null;
				assertThrows(RuntimeException.class, () ->
							{
								ListUtil.isEmpty(d);
							}
						);
	}
	
	@Test
	public void testGetLargest() {
		int[] a = {1,2,3,4,5,6};
		assertEquals(6, ListUtil.getLargest(a));
		int[] b = {600,4,22};
		assertEquals(600, ListUtil.getLargest(b));
		int[] c = {-33,-520,0,-1};
		assertEquals(0, ListUtil.getLargest(c));
		int[] d = {};
		assertThrows(RuntimeException.class,
				() ->	{
							ListUtil.getLargest(d);
						}
					);
		//Aufgabenblatt 5 Aufgabe 1 d) anhand von c)
				int[] e = null;
				assertThrows(RuntimeException.class, () ->
							{
								ListUtil.getLargest(e);
							}
						);
	}
	
	@Test
	public void testGetSmallest() {
		int[] a = {1,2,3,4,5,6,1};
		assertEquals(1, ListUtil.getSmallest(a));
		int[] b = {600,4,22};
		assertEquals(4, ListUtil.getSmallest(b));
		int[] c = {-33,-520,0,-1};
		assertEquals(-520, ListUtil.getSmallest(c));
		//Aufgabenblatt 5 Aufgabe 1 d) anhand von c)
				int[] d = null;
				assertThrows(RuntimeException.class, () ->
							{
								ListUtil.getSmallest(d);
							}
						);
	}
	
	@Test
	public void testGetSmallestRuntimeException() {
		int[] a = {};
		assertThrows(RuntimeException.class,()
				-> {ListUtil.getSmallest(a);});
	}
}
