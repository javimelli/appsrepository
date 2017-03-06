package org.javimelli.model;

public class Vote_comment {
	
	private int user_id;
	private int commentary_id;
	private boolean value;
	private boolean complaint;
	
	public Vote_comment(){
		
	}

	public Vote_comment(int user_id, int commentary_id, boolean value, boolean complaint) {
		super();
		this.user_id = user_id;
		this.commentary_id = commentary_id;
		this.value = value;
		this.complaint = complaint;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public int getCommentary_id() {
		return commentary_id;
	}

	public void setCommentary_id(int commentary_id) {
		this.commentary_id = commentary_id;
	}

	public boolean getValue() {
		return value;
	}

	public void setValue(boolean value) {
		this.value = value;
	}

	public boolean isComplaint() {
		return complaint;
	}

	public void setComplaint(boolean complaint) {
		this.complaint = complaint;
	}
	
	
}
