//package kexam2017;
//
//import java.util.Iterator;
//
//public class MealTest extends TestCase {
//
//	private Course c1, c2;
//	private Meal meal;
//
//	@Override
//	protected void setUp() throws Exception {
//		c1 = new Course("Bread", "Good");
//		c2 = new Course("Lobster", "Bad");
//		meal = new Meal("Expensive", "Delicious", new Course[] { c1, c2 });
//	}
//
//	/**
//	 * Tests Meal's support for foreach loop (iteration)
//	 */
//	@Test
//	public void testIteration() {
//		Iterator<Course> iterator = meal.iterator();
//		assertTrue(iterator.hasNext());
//		assertEquals(c1, iterator.next());
//		assertTrue(iterator.hasNext());
//		assertEquals(c2, iterator.next());
//		assertFalse(iterator.hasNext());
//	}
//
//	/**
//	 * Tests Meal's findCourse(Prediate) method
//	 */
//	@Test
//	public void testFindCourse() {
//        assertEquals(c2, meal.findCourse((course) -> course.getDescription().equals("Bad")));
//		assertEquals(c2, meal.findCourse((course) -> course.getName().equals("Bread")));
//		assertNull(meal.findCourse((course) -> course.getName().equals("HAHAHA")));
//	}
//}