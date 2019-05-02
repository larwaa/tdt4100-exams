package kexam2014;


public class Test {

	private Account account;

	@Before
	public void setUp(){
		account = new Account("1");
	}

	@Test
	public void testDeposit(){
		int d = 100;
		account.deposit(d);
		assertEquals(d, account.getBalance());
		try{
			account.depost(-50);
			fail();
		} catch (Exception e){
			assertEquals(d, account.getBalance());
		}
	}

	@Test
	public void testWithdraw(){
		account.withdraw(150);
		assertEquals(-50, account.getBalance());
		try{
			account.withdraw(-50);
			fail();
		} catch (Exception e){
			assertEquals(-50, account.getBalance());
		}
	}

}
