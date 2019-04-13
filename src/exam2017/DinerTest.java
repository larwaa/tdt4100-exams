package exam2017;

public class DinerTest {

	private Diner diner;
	private Table t1, t2, t3;

	@Before
	public void setUp(){
		diner = new Diner();
		t1 = new Table(5);
		t2 = new Table(3);
		t3 = new Table(7);
		diner.addTable(t1);
		diner.addTable(t2);
		diner.addTable(t3);
		diner.addSeating(new Group(5));
	}

	@Test
	public void testIsOccupied(){
		assertEquals(true, diner.isOccupied(t1));
		assertEquals(false, diner.isOccupied(t2));
	}
}
