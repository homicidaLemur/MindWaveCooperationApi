package com.bednorz.dawid.neurosky.mindwave.cooperationAPI;

//import java.io.IOException;

public class test {

	public static void main(String[] args) {
		int port;
		if (args.length != 0)
			try {
				port = Integer.parseInt(args[0]);
			} catch (NumberFormatException nfe) {
				System.out.println("Argument for server port is not a number.\nUsing default port: 8080");
				port = 8080;
			}
		else
			port = 8080;
		MindWaveServer server = new MindWaveServer(port);
		new Thread(server).start();
		while (server.isRunning()) {
			try {
				Thread.sleep(20 * 1000);
			} catch (InterruptedException ie) {
			}
			server.stop();
		}
	}

}
