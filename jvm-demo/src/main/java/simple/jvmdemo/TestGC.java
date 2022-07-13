package simple.jvmdemo;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Random;

/**
 * @author simple
 * @date 2021/10/2 11:54
 */
public class TestGC {

	public static void main(String[] args) throws InterruptedException {
		ArrayList<Object> list = new ArrayList<>();

		while (true) {
			int sleep=new Random().nextInt(100);
			if (System.currentTimeMillis() % 2 == 1) {
				list.clear();
			}else {
				for (int i = 0; i < 10000; i++) {
					Properties properties = new Properties();
					properties.put("key_" + i, "name_" + i);
					list.add(properties);
				}
			}
			Thread.sleep(sleep);
		}
	}
}
