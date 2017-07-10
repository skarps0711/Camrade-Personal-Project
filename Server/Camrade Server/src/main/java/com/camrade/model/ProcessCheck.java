package com.camrade.model;

public class ProcessCheck {
	private Integer id;
	private Boolean status;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	
	public Boolean getStatus() {
		return status;
	}
	public void setStatus(Boolean status) {
		this.status = status;
	}
	
	public ProcessCheck(){}
	
	public ProcessCheck(Integer id,Boolean status){
		this.id=id;
		this.status=status;
	}
}
