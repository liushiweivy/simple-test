import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Supplier;
import org.junit.Test;

/**
 * @author simple
 * @date 2022/3/18 23:28
 */

public class SupplierTest {

	public static String stringHandler(String str, MyFunc myFunc) {
		return myFunc.getValue(str);
	}

	@Test
	public  void test() {
		List<Integer> numList = getNumList(10, () -> new Random().nextInt(100));
		numList.forEach(System.out::println);

	}

	public List<Integer> getNumList(Integer n, Supplier<Integer> supplier) {

		ArrayList<Integer> integers = new ArrayList<>();

		for (int i = 0; i < n; i++) {
			Integer num = supplier.get();
			integers.add(num);
		}
		return integers;

	}
}
