package com.jiaxiaomei.edison;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jiaxiaomei.edison.hello.HelloSender;
import com.jiaxiaomei.edison.many.NeoSender;
import com.jiaxiaomei.edison.many.NeoSender2;

@Controller
public class RabbitController {
	private static Logger log = LoggerFactory.getLogger(RabbitController.class);
	private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();
    
	@Autowired
	private HelloSender helloSender;

	@GetMapping("/hello")
	@ResponseBody
	public String hello() throws Exception {
		helloSender.send();
		return "finished";
	}

    @GetMapping("/")
    @ResponseBody
    public String sayHello(@RequestParam(name="name", required=false, defaultValue="Stranger") String name) {
    	log.info("Access /hello-world");
    	
        return "Counter:"+counter.incrementAndGet()+","+String.format(template, name);
    }
	@Autowired
	private NeoSender neoSender;

	@Autowired
	private NeoSender2 neoSender2;

	@GetMapping("/oneToMany")
	@ResponseBody
	public String oneToMany() throws Exception {
		for (int i=0;i<100;i++){
			neoSender.send("oneToMany: sending the massges,No.:"+i);
		}
		return "finished oneToMany";
	}

	@GetMapping("/manyToMany")
	@ResponseBody
	public String manyToMany() throws Exception {
		for (int i=0;i<100;i++){
			neoSender.send("manyToMany: sending the massges,No.:"+i);
			neoSender2.send("manyToMany: sending the massges,No.:"+i);
		}
		return "finished manyToMany";
	}    
}
