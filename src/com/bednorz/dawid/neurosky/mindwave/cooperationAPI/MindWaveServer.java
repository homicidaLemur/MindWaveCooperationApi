package com.bednorz.dawid.neurosky.mindwave.cooperationAPI;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Cooperation Server for the NeuroSky MindWave device
 * 
 * @author Dawid Bednorz
 * @version 1.0
 */
@SuppressWarnings("unused")
public class MindWaveServer implements Runnable {
	private ServerSocket serverSocket;
	private Socket clientSocket;
	private Thread runningThread;
	private int portNumber;
	private boolean running;
	private ConnectionsObserver observer;

	/**
	 * Parametric constructor for the server
	 * 
	 * @param portNumber
	 *            Port Number, on which the server listens
	 */
	public MindWaveServer(int portNumber) {
		this.portNumber = portNumber;
		serverSocket = null;
		running = false;
		runningThread = null;
	}

	/**
	 * Parameterless constructor for the server
	 */
	public MindWaveServer() {
		portNumber = 8080;
		serverSocket = null;
		running = false;
		runningThread = null;
	}

	private void openServerSocket() {
		System.out.println("Starting server");
		try {
			serverSocket = new ServerSocket(portNumber);
		} catch (IOException ioe) {
			ioe.printStackTrace();
			throw new RuntimeException("Cannot open server on port " + this.portNumber, ioe);
		}
		running = true;
		System.out.println("Server up on port " + this.portNumber);
	}

	public ServerSocket getServerSocket() {
		return this.serverSocket;
	}

	public void run() {
		synchronized (this) {
			this.runningThread = Thread.currentThread();
		}
		openServerSocket();
		clientSocket = null;
		while (running) {
			try {
				clientSocket = serverSocket.accept();
			} catch (IOException ioe) {
				if (ioe.getClass().toString().contains("java.net.SocketException")
						&& ioe.getMessage().toString().contains("socket closed"))
					System.out.println("Handled");
				else
					throw new RuntimeException("Server-client acceptance error", ioe);
			}
			new Thread(new WorkerRunnable(clientSocket, "Multithreaded Server")).start();
			// new Thread(new MindWaveWorker(clientSocket, "MindWave cooperation
			// server", observer)).start();
		}
	}

	/**
	 * Stops server's execution
	 */
	public synchronized void stop() {
		System.out.println("Stopping Server");
		this.running = false;
		try {
			this.serverSocket.close();
			System.out.println("Server closed");
		} catch (IOException ioe) {
			throw new RuntimeException("Error closing server", ioe);
		}
	}

	/**
	 * Returns the state of the server
	 * 
	 * @return Server's state
	 */
	public synchronized boolean isRunning() {
		return this.running;
	}

	/**
	 * Returns the thread on which the server is running
	 * 
	 * @return Running thread
	 */
	public Thread getRunningThread() {
		return this.runningThread;
	}
}
