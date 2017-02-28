package com.kevin.boot.web.datasource;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * 动态数据源切面,必需保证该切面最先执行
 */
@Component
@Aspect
@Order(-999)        //务必保证最先执行
public class DataSourceAspect {
	private static final Logger LOGGER = LoggerFactory.getLogger(DataSourceAspect.class);
	/**
	 * 在进入Service方法之前执行
	 * @param point
	 * @throws NoSuchMethodException
	 */
	@Before(value = "execution(* com.kevin.boot.web.service.*.*(..))")
	public void before(JoinPoint point) throws NoSuchMethodException {
		//拦截的实体对象
		Object target = point.getTarget();
		//拦截的方法名称
		String methodName = point.getSignature().getName();
		//拦截的方法参数
		Object[] args = point.getArgs();
		//拦截的方法参数类型
		Class[] parameterTypes = ((MethodSignature)point.getSignature()).getMethod().getParameterTypes();
		//获取到的目标方法
		Method method = null;
		//通过反射获得拦截的method
		method = target.getClass().getMethod(methodName, parameterTypes);
		//如果是桥则要获得实际拦截的method
		if(method.isBridge()){
			for(int i = 0; i < args.length; i++){
				//获得泛型类型
				Class genClazz = this.getSuperClassGenricType(target.getClass());
				//根据实际参数类型替换parameterType中的类型
				if(args[i].getClass().isAssignableFrom(genClazz)){
					parameterTypes[i] = genClazz;
				}
			}
			//获得parameterType参数类型的方法
			method = target.getClass().getMethod(methodName, parameterTypes);
		}
		LOGGER.info("当前事务方法  " + methodName);
		Transactional transactional = method.getAnnotation(Transactional.class);
		if(transactional != null && transactional.readOnly()){
			//读库
			LOGGER.info("动态数据源 - 读库");
			DynamicDataSourceHolder.markSlave();
		}else {
			//写库
			LOGGER.info("动态数据源 - 写库");
			DynamicDataSourceHolder.markMaster();
		}
	}
	
	public static Class getSuperClassGenricType(Class clazz, int index) {
		Type genType = clazz.getGenericSuperclass();        // 得到泛型父类
		if (!(genType instanceof ParameterizedType)) {
			return Object.class;
		}
		Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
		if (!(params[index] instanceof Class)) {
			return Object.class;
		}
		return (Class) params[index];
	}
	
	public static Class getSuperClassGenricType(Class clazz) {
		return getSuperClassGenricType(clazz, 0);
	}
}
