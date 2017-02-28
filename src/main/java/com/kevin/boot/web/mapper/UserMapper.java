package com.kevin.boot.web.mapper;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.kevin.boot.web.entity.UserEntity;
import org.springframework.stereotype.Repository;

/**
 * Created by KevinBlandy on 2017/2/28 15:10
 */
@Repository
public interface UserMapper {
	
	PageList<UserEntity> users(UserEntity userEntity, PageBounds pageBounds);
	
	Integer create(UserEntity userEntity);
}
