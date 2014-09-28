package net.stamfest.rrd.tests;

import java.util.HashMap;
import java.util.Map.Entry;

import net.stamfest.rrd.CommandResult;
import net.stamfest.rrd.RRDCommandPool;
import net.stamfest.rrd.TCPRRDCommandFactory;

public class HelloPool {
	public static void main(String[] args) throws Exception {
		String rrdFileName = args[2];
		RRDCommandPool pool = new RRDCommandPool(10, new TCPRRDCommandFactory(Integer.parseInt(args[0]), args[1]));

		for (int i = 0 ; i < 60 ; i++) {
			System.out.println("start " + i);

			CommandResult result = pool.command(new String[] { "info", rrdFileName  });
			System.out.println(result);

			HashMap<String, String> hm = result.getInfo();

			if (hm != null) {
				for (Entry<String,String> e : hm.entrySet()) {
					System.out.printf("%s:\t%s\n", e.getKey(), e.getValue());
				}
			}
			System.out.println("done " + i);

			Thread.sleep(10000);
		}
	}
}
