package springcloud.controller;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import springcloud.config.NodeRule8080;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;


@RestController
public class WebController {
    @Autowired
    private RestTemplate restTemplate;
    private String result;
    private String name = "26";
    long beginTime;
    long upTime;
    double w0 = 10.0;
    double delta = 0.01;
    public static HashMap<String, LinkedHashMap<String, Double>> serviceMap = new HashMap<>();

    @GetMapping("/go")
    public String get() {
        beginTime = System.currentTimeMillis();
        result = restTemplate.getForObject("http://door-energy-checkout" + "/Accept26?name=" + name, String.class);
//        result=restTemplate.getForObject("http://door-energy-checkout/test",String.class);
        upTime = System.currentTimeMillis() - beginTime;
        update(upTime, 55.328, 68.99);
        return "4-21测试";
    }


    @GetMapping("/test")
    public String test() {
        result = restTemplate.getForObject("http://door-energy-checkout" + "/test?name=" + name, String.class);
        System.out.println(result);
        return result;
    }

    private void update(long upTime, double begin, double end) {
        LinkedHashMap<String, Double> weightMap = serviceMap.getOrDefault(NodeRule8080.curName, new LinkedHashMap<>());
        Double weight = weightMap.getOrDefault(NodeRule8080.select, w0);
        if (upTime < begin) weight += delta;
        else if (upTime > end) weight -= delta;
        if (weight < 0) weight = 0.0;
        weightMap.put(NodeRule8080.select, weight);
        serviceMap.put(NodeRule8080.curName, weightMap);
//        System.out.println("controller: serverName: "+NodeRule274.curName+" selectName: "+NodeRule274.select);
    }


    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(), //注意这里不要定义队列名称,系统会随机产生
            exchange = @Exchange(value = "door-energy-checkout", type = ExchangeTypes.FANOUT)
    )
    )
    public void get(String para, Channel channel) throws IOException {
//        String[] split = para.split("/");
//        channel.basicQos(1);
//        LinkedHashMap<String, Double> weightMap = serviceMap.getOrDefault(split[0], new LinkedHashMap<>());
//        weightMap.put(split[1], 0.0);
//        serviceMap.put(split[0], weightMap);
//        System.out.println(split[0]+"重置 "+split[1]+" 权重成功");
//        System.out.println(para);
    }
}
