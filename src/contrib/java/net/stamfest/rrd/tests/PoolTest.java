/*

 Copyright (c) 2010 by Peter Stamfest <peter@stamfest.at>

 This file is part of java-rrd.

 Permission is hereby granted, free of charge, to any person obtaining a copy
 of this software and associated documentation files (the "Software"), to deal
 in the Software without restriction, including without limitation the rights
 to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 copies of the Software, and to permit persons to whom the Software is
 furnished to do so, subject to the following conditions:

 The above copyright notice and this permission notice shall be included in
 all copies or substantial portions of the Software.

 THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 THE SOFTWARE.

 Except as contained in this notice, the name of Peter Stamfest shall not 
 be used in advertising or otherwise to promote the sale, use or other 
 dealings in this Software without prior written authorization from 
 Peter Stamfest.

*/


package net.stamfest.rrd.tests;

import java.util.ArrayList;

import net.stamfest.rrd.CommandResult;
import net.stamfest.rrd.RRDCommandPool;
import net.stamfest.rrd.RRDToolService;

public class PoolTest {
    public static void main(String argv[]) throws Exception {
    	RRDCommandPool pool = new RRDCommandPool("/", null);
	final RRDToolService rrd = new RRDToolService(pool);

	ArrayList<String> cmd = new ArrayList<String>();
	cmd.add("DEF:in=/tmp/D3.rrd:in:AVERAGE");
	cmd.add("LINE1:in#ff0000:in");

	final String cmdarray[] = cmd.toArray(new String[0]);
	
	for (int j = 0 ; j < 5 ; j++) {
	    Thread t = new Thread() {
		@Override
		public void run() {
		    for (int i = 0 ; i < 100 ; i++) {
			CommandResult r;
			try {
			    r = rrd.graphv(cmdarray);
			    System.out.println(r);
			} catch (Exception e) {
			    // TODO Auto-generated catch block
			    e.printStackTrace();
			}
		    }
		}
	    };
	    
	    t.start();
	}
		
    }
}
