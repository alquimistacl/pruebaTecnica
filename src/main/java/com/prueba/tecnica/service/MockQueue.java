package com.prueba.tecnica.service;

import java.util.LinkedList;
import java.util.Queue;

import com.prueba.tecnica.model.SaleMessage;

public class MockQueue {

	private static MockQueue mockQueue = new MockQueue();
	private static Queue<SaleMessage> queue = new LinkedList<>();

	private MockQueue() {
		// required constructor
	}

	public static MockQueue getInstance() {
		return mockQueue;
	}

	protected static Boolean putMessage(SaleMessage messasge) {
		return queue.add(messasge);
	}

	protected static SaleMessage getMessage() {
		return queue.poll();
	}

	protected static Boolean isEmpty() {
		return queue.isEmpty();
	}
}
