package com.kevin.boot.web.controller;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.kevin.boot.web.entity.UserEntity;
import com.kevin.boot.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * Created by KevinBlandy on 2017/2/28 15:51
 */
@RestController
@RequestMapping(value = "user")
public class UserController {

	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "create",method = RequestMethod.GET)
	public UserEntity user(){
		UserEntity userEntity = new UserEntity();
		userEntity.setUserName("余文朋");
		userEntity.setAge(23);
		userEntity.setBirthDay(new Date());
		this.userService.create(userEntity);
		return userEntity;
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public PageList<UserEntity> users(){
		return this.userService.users(new UserEntity(),new PageBounds(1,1000));
	}
}
