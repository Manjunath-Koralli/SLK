package com.slk.model;

public class Branch {
	private int br_id;
	private String br_name;
	private String br_ifsc;
	private String br_addr;
	private long br_contact;
	public int getBr_id() {
		return br_id;
	}
	public void setBr_id(int br_id) {
		this.br_id = br_id;
	}
	public String getBr_name() {
		return br_name;
	}
	public void setBr_name(String br_name) {
		this.br_name = br_name;
	}
	public String getBr_ifsc() {
		return br_ifsc;
	}
	public void setBr_ifsc(String br_ifsc) {
		this.br_ifsc = br_ifsc;
	}
	public String getBr_addr() {
		return br_addr;
	}
	public void setBr_addr(String br_addr) {
		this.br_addr = br_addr;
	}
	public long getBr_contact() {
		return br_contact;
	}
	public void setBr_contact(long br_contact) {
		this.br_contact = br_contact;
	}
}
