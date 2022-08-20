package cn.itcast.order.service;

import cn.itcast.order.clients.UserClient;
import cn.itcast.order.mapper.OrderMapper;
import cn.itcast.order.pojo.Order;
import cn.itcast.order.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private UserClient userClient;

/*    @Autowired
    private WebClient.Builder webClientBuilder;*/
    /*@Autowired
    private WebClient webClient;*/

    /*@Autowired
    private RestTemplate restTemplate;

    public Order queryOrderById(Long orderId) {

        // 1.查询订单
        Order order = orderMapper.findById(orderId);

        //调用WebClient发送http请求获取user
        *//*User user = webClient.get().uri("http://userservice/user/" + order.getUserId())
            .retrieve().bodyToMono(User.class).block();*//*
        User user = restTemplate.getForObject("http://userservice/user/" + order.getUserId(),User.class);

        order.setUser(user);
        return order;

    }*/
    public Order queryOrderById(Long orderId) {

        // 1.查询订单
        Order order = orderMapper.findById(orderId);
        //调用Feign发送http请求获取User
        User user = userClient.findById(order.getUserId());
        order.setUser(user);
        return order;

    }


}
