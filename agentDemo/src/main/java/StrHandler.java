/**
 * @author simple
 * @date 2022/3/18 23:28
 */

public class StrHandler {

	public static String stringHandler(String str, MyFunc myFunc) {
		return myFunc.getValue(str);
	}

	public static void test() {
		String aaa = stringHandler("aaa", s -> s + s);
		System.out.println(aaa);
	}

	public static void main(String[] args) {
		test();
	}
}
