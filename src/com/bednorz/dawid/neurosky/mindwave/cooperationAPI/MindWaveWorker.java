package com.bednorz.dawid.neurosky.mindwave.cooperationAPI;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
//import java.util.Observable;
//import java.util.Observer;

/**
 * Worker class for the NeuroSky MindWave cooperation server
 * 
 * @author Dawid Bednorz
 * @version 1.0
 */
public class MindWaveWorker implements Runnable {
	private Socket brainWaveClientSocket;
	private ConnectionsObserver observer;
	private String name;
	private String deviceName;

	/**
	 * Socket for connection with the device and the Server
	 * 
	 * @param socket
	 *            the server accepted socket connection
	 */
	public MindWaveWorker(Socket socket, String name, ConnectionsObserver observer) {
		this.brainWaveClientSocket = socket;
		this.name = name;
		this.observer = observer;
		this.observer.addWorker(this);
	}

	public void run() {
		try (BufferedReader input = new BufferedReader(new InputStreamReader(brainWaveClientSocket.getInputStream()))) {
			int receivedValue = Integer.parseInt(input.readLine());
			if (receivedValue == 0) {

			}
		} catch (IOException | NumberFormatException e) {
			e.printStackTrace();
			throw new RuntimeException("Client-side error", e);
		}
	}

	public Socket getClientSocket() {
		return this.brainWaveClientSocket;
	}

	public synchronized void stop() {
		try {
			this.observer.workers.remove(this);
			this.observer.devices.remove(deviceName);
			this.brainWaveClientSocket.close();
		} catch (IOException ioe) {
			System.out.println(ioe);
			ioe.printStackTrace();
		}
	}

	public void update(int data) {
		// TODO write this
	}

	public String getName() {
		return this.name;
	}
}
