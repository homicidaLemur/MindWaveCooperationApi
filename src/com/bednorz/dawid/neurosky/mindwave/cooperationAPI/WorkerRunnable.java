package com.bednorz.dawid.neurosky.mindwave.cooperationAPI;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.IOException;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

public class WorkerRunnable implements Runnable {
	protected Socket clientSocket = null;
	protected String serverText = null;

	public WorkerRunnable(Socket clientSocket, String serverText) {
		this.clientSocket = clientSocket;
		this.serverText = serverText;
	}

	public void run() {
		try {
			InputStream input = clientSocket.getInputStream();
			OutputStream output = clientSocket.getOutputStream();
			String dateTimeFormat = "dd.MM.yyyy hh:mm:ss";
			SimpleDateFormat sdf = new SimpleDateFormat(dateTimeFormat);
			output.write(("HTTP/1.1 200 OK\n\nWorkerRunnable:  " + this.serverText + " - "
					+ sdf.format(new Date()).toString() + "").getBytes());
			output.close();
			input.close();
			// System.out.println("Request processed on thread " + threadNo);
		} catch (IOException e) {
			// report exception somewhere.
			e.printStackTrace();
		}
	}
}
