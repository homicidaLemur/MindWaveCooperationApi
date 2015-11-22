package com.bednorz.dawid.neurosky.mindwave.cooperationAPI;

public class MindWaveDevice {
	private String deviceName;
	private MindWaveData data;

	public MindWaveDevice(String deviceName, MindWaveData data) {
		this.deviceName = deviceName;
		this.data = data;
	}

	public MindWaveDevice(String deviceName) {
		this.deviceName = deviceName;
		this.data = new MindWaveData();
	}

	public MindWaveDevice(String deviceName, String deviceState) {
		this.deviceName = deviceName;
		this.data = new MindWaveData(deviceState);
	}

	public MindWaveDevice() {
		this.deviceName = null;
		this.data = new MindWaveData();
	}

	public String getDeviceName() {
		return this.deviceName;
	}

	public MindWaveData getData() {
		return this.data;
	}

}
