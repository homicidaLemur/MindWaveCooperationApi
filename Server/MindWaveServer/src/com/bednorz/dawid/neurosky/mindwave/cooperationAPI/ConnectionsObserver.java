package com.bednorz.dawid.neurosky.mindwave.cooperationAPI;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConnectionsObserver {
	Map<Integer, MindWaveDevice> devices;
	List<MindWaveWorker> workers;
	int maxId;

	public ConnectionsObserver() {
		devices = new HashMap<Integer, MindWaveDevice>();
		workers = new ArrayList<MindWaveWorker>();
		maxId = 0;
	}

	public void update() {

	}

	public void addDevice(MindWaveDevice connection) {
		devices.put(new Integer(maxId), connection);
		this.maxId++;
	}

	public void addWorker(MindWaveWorker worker) {
		this.workers.add(worker);
	}

}
