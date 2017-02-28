package com.kevin.boot.web.service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.kevin.boot.web.entity.UserEntity;

/**
 * Created by KevinBlandy on 2017/2/28 15:10
 */
public interface UserService {
	
	PageList<UserEntity> users(UserEntity userEntity, PageBounds pageBounds);
	
	Integer create(UserEntity userEntity);
}
