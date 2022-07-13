

import java.lang.instrument.Instrumentation;

public class FirstAgent {
	public static void premain(String agentOps, Instrumentation inst) {
		System.out.println(agentOps);
	}
}
