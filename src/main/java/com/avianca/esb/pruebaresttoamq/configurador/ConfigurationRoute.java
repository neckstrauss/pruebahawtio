/*
 * Copyright 2005-2016 Red Hat, Inc.
 *
 * Red Hat licenses this file to you under the Apache License, version
 * 2.0 (the "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or
 * implied.  See the License for the specific language governing
 * permissions and limitations under the License.
 */
package com.avianca.esb.pruebaresttoamq.configurador;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.metrics.MetricsComponent;
import org.apache.camel.component.metrics.messagehistory.MetricsMessageHistoryFactory;
import org.apache.camel.component.metrics.routepolicy.MetricsRoutePolicyFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.codahale.metrics.MetricRegistry;

@Component
public class ConfigurationRoute extends RouteBuilder {

	@Value("${track}")
	private Boolean track;
	
	@Value("${maximumRedeliveries}")
	private int maximumRedeliveries;
	
	@Value("${redeliveryDelay}")
	private int redeliveryDelay;
	
	@Value("${uriEndPointDLQ}")
	private String uriEndPointDLQ;
	
	@Value("${errorHandle}")
	private Boolean errorHandle;
	
	
	@Bean(name = MetricsComponent.METRIC_REGISTRY_NAME)
    public MetricRegistry getMetricRegistry() {
        MetricRegistry registry = new MetricRegistry();
        return registry;
    }

    
	
	
	@Override
	public void configure() throws Exception {
		// TODO Auto-generated method stub
		getContext().setTracing(track);
		MetricsMessageHistoryFactory mmhf =new MetricsMessageHistoryFactory();
		mmhf.setUseJmx(true);
		mmhf.setMetricsRegistry(getMetricRegistry());
//		
		getContext().setMessageHistoryFactory(mmhf);
//		//get
//		
		MetricsRoutePolicyFactory mrpf =new MetricsRoutePolicyFactory();
		mrpf.setUseJmx(true);
		
		getContext().addRoutePolicyFactory(mrpf);
		
		
//		onException(Exception.class)
//		.maximumRedeliveries(maximumRedeliveries)
//		.redeliveryDelay(redeliveryDelay)
//		.handled(errorHandle) 
//		.process(new FailureErrorProcessor())
//	    .to(uriEndPointDLQ)
//	    ;
	}

}
