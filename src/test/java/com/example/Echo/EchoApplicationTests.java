package com.example.Echo;

import com.example.Echo.dao.AlphaDao;
import com.example.Echo.service.AlphaService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.SimpleDateFormat;
import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = EchoApplication.class)
public class EchoApplicationTests implements ApplicationContextAware {

	private ApplicationContext applicationContext;

	// 得到bean的container
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}

//	@Test
//	public void contextLoads() {
//		System.out.println(applicationContext);
//		// 调用方和实现类没有了直接关系
//		AlphaDao alphaDao = applicationContext.getBean(AlphaDao.class);
//		System.out.println(alphaDao.select());
//
//		alphaDao = applicationContext.getBean("alphaHibernate", AlphaDao.class);
//		System.out.println(alphaDao.select());
//
//
//	}
//
//	@Test
//	public void testBeanManagement() {
//		AlphaService alphaService = applicationContext.getBean(AlphaService.class);
//		System.out.println(alphaService);
//
//		alphaService = applicationContext.getBean(AlphaService.class);
//		System.out.println(alphaService);
//	}

	@Test
	public void testBeanConfig() {
		SimpleDateFormat simpleDateFormat =
				applicationContext.getBean(SimpleDateFormat.class);
		System.out.println(simpleDateFormat.format(new Date()));
	}

	// 注入给属性
	@Autowired
	@Qualifier("alphaHibernate")
	private AlphaDao alphaDao;

	@Test
	public void testDI() {
		System.out.println(alphaDao);
	}
}
