package uebung3;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TaschenrechnerTest
{

	//Test für Klasse Addition
	//Test für ignoreZero gibt es nicht, man kann nur selbst im Code nachschauen
	@Test
	public void testAddition() {
		assertEquals(5, Taschenrechner.add(2,3));
	}
	@Test
	public void testAdditionExceptionOverflow() {
		assertThrows(ArithmeticException.class,()
				-> {Taschenrechner.add(Integer.MAX_VALUE, 1);});
	}
	@Test
	public void testIgnoreNullAdd() {
		assertEquals(4, Taschenrechner.add(0, 4));
	}
	
	@Test
	public void groupedAssertions() 
	{
		/*
		 * In a grouped assertion all assertions are executed, and all
		 * failures will be reported together
		 */
		assertAll(
				() -> assertEquals(1, Taschenrechner.add(1, 0)),
				() -> assertEquals(5, Taschenrechner.add(1, 4))
				);
			
	}
	
	@Test
	public void depentAssertions() {
		/*
		 * Within a code block, if an assertion fails the subsequent code
		 * in the same block will be skipped
		 */
		assertAll("add, mult",
				() ->{
					assertEquals(5, Taschenrechner.add(2, 3));
					
					// Executed only if the previous assertion is valid.
					assertAll("mult",
							() -> assertEquals(0, Taschenrechner.mul(2, 0)),
							() -> assertEquals(8, Taschenrechner.mul(2, 4))
							);
					}
				);
	}
	
	@Test
	public void testSubtraktion() {
		assertEquals(6, Taschenrechner.sub(12, 6));
	}
	@Test
	public void testSubtractionExceptionOverflow() {
		assertThrows(ArithmeticException.class,()
				->	{
					Taschenrechner.sub(Integer.MIN_VALUE, 1);
					}
					);
	}
	
	
	@Test
	public void testMultiplication() {
		assertEquals(6, Taschenrechner.mul(2,3));
	}
	@Test
	public void testMultiplicationExceptionOverflow() {
		assertThrows(ArithmeticException.class,()
				-> {Taschenrechner.mul(Integer.MAX_VALUE, 2);});
	}
	@Test
	public void testIgnoreOne() {
		assertEquals(3, Taschenrechner.mul(1,3));
	}
	
	
	
	@Test
	public void testDivision() {
		assertEquals(1, Taschenrechner.div(5,3));
	}
	@Test
	public void testDivisionExceptionDivisionByZero() {
		assertThrows(ArithmeticException.class,()
				-> {Taschenrechner.div(44, 0);});
	}
	
	@Test
    public void testPercent() {
        assertEquals(20, Taschenrechner.percent(100, 20));
    }

    @Test
    public void testCalculateVAT() {
        assertEquals(19, Taschenrechner.calculateVAT(100, 19));
    }
	
}
