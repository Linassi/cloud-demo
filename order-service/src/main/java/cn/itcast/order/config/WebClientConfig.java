package cn.itcast.order.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.cloud.client.loadbalancer.reactive.LoadBalancerExchangeFilterFunction;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;

//@Configuration
public class WebClientConfig {
    @Bean
    @LoadBalanced
    public WebClient.Builder loadBalancedWebClientBuilder() {
        return WebClient.builder();
    }
    @Autowired
    private LoadBalancerClient loadBalancerClient;
    @Bean
    public WebClient loadBalancedWebClient() {
        return WebClient.builder().filter(new LoadBalancerExchangeFilterFunction(loadBalancerClient)).build();
    }

}
