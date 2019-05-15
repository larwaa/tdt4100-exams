package exam2018;

import java.util.function.Supplier;

public class Test implements Supplier<Integer> {

	public Integer get(){
		return (int) (Math.random() * 6 + 1);
	}

	public static void main(String[] args){
		Test t = new Test();
		for (int i = 0; i < 10; i++){
			System.out.println(t.get());
		}
	}
}
