package com.ftn.upib.dto;

public class FilterDTO {


	private String date;
	private String term;
	
	public FilterDTO() {
		
	}
	
	public FilterDTO(String date, String term){
		this.date = date;
		this.term = term;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getTerm() {
		return term;
	}

	public void setTerm(String term) {
		this.term = term;
	}
	

	@Override
	public String toString() {
		return "FilterDTO [date=" + date + ", term=" + term + "]";
	}
}
