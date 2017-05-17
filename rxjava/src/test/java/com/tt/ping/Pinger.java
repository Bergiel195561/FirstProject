package com.tt.ping;

import static com.tt.ping.Status.DOWN;
import static com.tt.ping.Status.UP;

public class Pinger {

	public Status healthy() {
		return Math.random() < 0.9 ? UP : DOWN;
	}

}

