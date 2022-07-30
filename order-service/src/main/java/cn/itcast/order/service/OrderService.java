package cn.itcast.order.service;

import cn.itcast.order.mapper.OrderMapper;
import cn.itcast.order.pojo.Order;
import cn.itcast.order.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private WebClient.Builder webClientBuilder;

    private WebClient webClient = null;

    public Order queryOrderById(Long orderId) {

        // 1.查询订单
        Order order = orderMapper.findById(orderId);

        if (webClient == null){
            webClient = webClientBuilder.build();
        }
        //调用WebClient发送http请求获取user
        User user = webClient.get().uri("http://userservice/user/" + order.getUserId())
            .retrieve().bodyToMono(User.class).block();

        order.setUser(user);
        return order;

    }
}
