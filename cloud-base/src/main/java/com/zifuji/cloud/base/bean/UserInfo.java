package com.zifuji.cloud.base.bean;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

@Data
public class UserInfo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private Long id;
<<<<<<< HEAD

	private String token;

	private String userName;
=======
>>>>>>> f0666b324d0f19084264899903a51bf36f2b88df
	
	private String name;

	private String email;

	private String type;
	
	private List<String> roleList;
	
	private List<String> permissionList;
	

}
