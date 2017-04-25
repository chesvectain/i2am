/*
 ** Copyright (C) 2013 Mellanox Technologies
 **
 ** Licensed under the Apache License, Version 2.0 (the "License");
 ** you may not use this file except in compliance with the License.
 ** You may obtain a copy of the License at:
 **
 ** http://www.apache.org/licenses/LICENSE-2.0
 **
 ** Unless required by applicable law or agreed to in writing, software
 ** distributed under the License is distributed on an "AS IS" BASIS,
 ** WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 ** either express or implied. See the License for the specific language
 ** governing permissions and  limitations under the License.
 **
 */
package org.accelio.jxio.tests.benchmarks;

public class StatMain {
	public static void main(String[] args) {
		if (args.length < 3) {
			System.out.println("StatMain $IP $PORT $NUM_SESSIONS");
			return;
		}
		Runnable test = new StatTest(args[0], Integer.parseInt(args[1]), Integer.parseInt(args[2]));
		test.run();
	}

}
