package simple.jvmdemo;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @author simple
 * @date 2021/7/2 21:04
 */
public class TestOOM {
	public static void main(String[] args) {
		List<String> list = new ArrayList<>();
		for (int i = 0; i < 1000000; i++) {
			String str = "";
			for (int j = 0; j < 1000; i++) {
				str = str+UUID.randomUUID().toString();
			}
			list.add(str);
		}

		System.out.println("执行完成");
	}
}
