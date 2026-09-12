package com.blog.app.SrpingBoot.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NotFoundException extends RuntimeException{
	String resourcename;
	String resource;
	long resourceId;
	public NotFoundException(String resourcename,String resource,long resourceId){
		super(String.format("%s not found with %s :  %s",resourcename,resource,resourceId));
		this.resourcename=resourcename;
		this.resource=resource;
		this.resourceId=resourceId;
	}
	
	
}
