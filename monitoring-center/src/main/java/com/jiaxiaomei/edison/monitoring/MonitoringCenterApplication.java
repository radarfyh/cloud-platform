package com.jiaxiaomei.edison.monitoring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import org.springframework.cloud.netflix.turbine.stream.EnableTurbineStream;

@SpringBootApplication
@EnableTurbineStream
@EnableDiscoveryClient
public class MonitoringCenterApplication {

	public static void main(String[] args) {
		SpringApplication.run(MonitoringCenterApplication.class, args);
	}
	
//	@Bean(name = IntegrationContextUtils.ARGUMENT_RESOLVER_MESSAGE_CONVERTER_BEAN_NAME)
//	public ConfigurableCompositeMessageConverter configurableCompositeMessageConverter(CompositeMessageConverterFactory factory){
//	    return new ConfigurableCompositeMessageConverter(factory.getMessageConverterForAllRegistered().getConverters());
//	}
}
