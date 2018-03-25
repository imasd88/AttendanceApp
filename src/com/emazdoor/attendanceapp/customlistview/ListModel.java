package com.emazdoor.attendanceapp.customlistview;

public class ListModel {

	private String name = "";
	private String date = "";
	private int position = 0;
	private String attenStatus = "";
	int current = -1;
	public static final int PRESENT = 0;
	public static final int ABSENT = 1;
	public static final int LEAVE = 2;

	public ListModel() {

	}

	public ListModel(int position) {
		this.position = position;
	}

	public ListModel(String name, String date) {
		super();
		this.name = name;
		this.date = date;
	}

	public ListModel(String name, int position, String attenStatus) {
		super();
		this.name = name;
		this.position = position;
		this.attenStatus = attenStatus;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	public String getAttenStatus() {
		return attenStatus;
	}

	public void setAttenStatus(String attenStatus) {
		this.attenStatus = attenStatus;
	}

}
