package com.kevin.boot.web.service.impl;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.kevin.boot.web.entity.UserEntity;
import com.kevin.boot.web.mapper.UserMapper;
import com.kevin.boot.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by KevinBlandy on 2017/2/28 15:10
 */
@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserMapper userMapper;
	
	@Transactional(readOnly = true)
	public PageList<UserEntity> users(UserEntity userEntity, PageBounds pageBounds){
		return this.userMapper.users(userEntity,pageBounds);
	}
	
	@Transactional
	public Integer create(UserEntity userEntity){
		return this.userMapper.create(userEntity);
	}
}
