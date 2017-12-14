package com.hayarobys.noxgame.main;

public class MainVO{
	private String message;
	private String result;
	
	public String getResult(){
		return result;
	}

	public void setResult(String result){
		this.result = result;
	}

	public String getMessage(){
		return message;
	}

	public void setMessage(String message){
		this.message = message;
	}

	@Override
	public String toString(){
		return "MainVO [message=" + message + ", result=" + result + "]";
	}
}
