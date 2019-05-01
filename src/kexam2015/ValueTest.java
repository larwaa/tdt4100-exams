//package kexam2015;
//
//public class ValueTest implements ValuesListener{
//
//	private Value value;
//	private Values valuesChanged;
//	private ValueSeries valueSeries = new ValueSeries(Unit.valueOf("km"));
//
//	@Before
//	public void setUp(){
//		value = Value.valueOf("2.0km");
//		valueSeries.appendValue(20);
//		valueSeries.addValuesListener(this);
//	}
//
//	@Test
//	public void testValueOf(){
//		assertEquals(new Value(Unit.valueOf("km"), 2.0).getValue(), value.getValue());
//		assertEquals(new Value(Unit.valueOf("km"), 2.0).getUnit(), value.getUnit());
//	}
//
//
//	public void valuesChanged(Values values){
//		this.valuesChanged = values;
//	}
//
//	@Test
//	public void testValuesListener(){
//		assertEquals(valueSeries, valuesChanged);
//	}
//
//}
