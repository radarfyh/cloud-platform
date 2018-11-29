package com.jiaxiaomei.edison.rabbitmq;

import com.jiaxiaomei.edison.many.NeoSender;
import com.jiaxiaomei.edison.many.NeoSender2;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ManyTest {
	@Autowired
	private NeoSender neoSender;

	@Autowired
	private NeoSender2 neoSender2;

	@Test
	public void oneToMany() throws Exception {
		for (int i=0;i<100;i++){
			neoSender.send("oneToMany: sending the massges,No.:"+i);
		}
	}

	@Test
	public void manyToMany() throws Exception {
		for (int i=0;i<100;i++){
			neoSender.send("manyToMany: sending the massges,No.:"+i);
			neoSender2.send("manyToMany: sending the massges,No.:"+i);
		}
	}

}