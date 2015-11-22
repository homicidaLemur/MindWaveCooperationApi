package com.bednorz.dawid.neurosky.mindwave.cooperationAPI;

public class MindWaveData {
	private String deviceState;
	private int concentration;
	private int relaxation;

	public MindWaveData(String deviceState, int concentration, int relaxation) {
		this.deviceState = deviceState;
		this.concentration = concentration;
		this.relaxation = relaxation;
	}

	public MindWaveData(String deviceState) {
		this.deviceState = deviceState;
		this.concentration = 0;
		this.relaxation = 0;
	}

	public MindWaveData() {
		deviceState = null;
		concentration = 0;
		relaxation = 0;
	}

	public String getDeviceStatus() {
		return deviceState;
	}

	public int getConcentration() {
		return concentration;
	}

	public void setConcentration(int concentration) {
		this.concentration = concentration;
	}

	public int getRelaxation() {
		return relaxation;
	}

	public void setRelaxation(int relaxation) {
		this.relaxation = relaxation;
	}

}
