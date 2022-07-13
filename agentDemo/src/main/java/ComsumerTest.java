import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Consumer;
import java.util.function.Supplier;
import org.junit.Test;

/**
 * @author simple
 * @date 2022/3/18 23:28
 */

public class ComsumerTest {

	public static String stringHandler(String str, MyFunc myFunc) {
		return myFunc.getValue(str);
	}

	@Test
	public  void test() {
		 happy(10, m-> System.out.println(m+12));
	}

	public void happy(Integer n, Consumer<Integer> consumer) {
		consumer.accept(n);

	}
}
