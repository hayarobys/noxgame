package kr.pe.hayarobys.nox.common.upload;

import org.springframework.web.multipart.MultipartFile;

public class HTML5FileVO{
	private String lastModified;
	private String name;
	private String size;
	private String type;
	private MultipartFile file;
	public String getLastModified(){
		return lastModified;
	}
	public void setLastModified(String lastModified){
		this.lastModified = lastModified;
	}
	public String getName(){
		return name;
	}
	public void setName(String name){
		this.name = name;
	}
	public String getSize(){
		return size;
	}
	public void setSize(String size){
		this.size = size;
	}
	public String getType(){
		return type;
	}
	public void setType(String type){
		this.type = type;
	}
	
	public MultipartFile getFile(){
		return file;
	}
	public void setFile(MultipartFile file){
		this.file = file;
	}
	@Override
	public String toString(){
		return "HTML5FileVO [lastModified=" + lastModified + ", name=" + name + ", size=" + size + ", type=" + type
				+ ", file=" + file + "]";
	}
	
}
