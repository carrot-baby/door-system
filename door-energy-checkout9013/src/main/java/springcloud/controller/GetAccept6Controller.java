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
import springcloud.config.NodeRule275;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;

@RestController
public class GetAccept6Controller {


    @Autowired
    private RestTemplate restTemplate;
    private String result;
    private Double Ek;
    private boolean res;
    private String name = "21";
    long beginTime;
    long upTime;
    double w0 = 10.0;
    double delta = 0.01;
    public static boolean flag = false;
    public static HashMap<String, LinkedHashMap<String, Double>> serviceMap = new HashMap<>();

    @GetMapping("/Accept26")
    public String getAccept() {
        beginTime = System.currentTimeMillis();
        result = restTemplate.getForObject("http://door-energy-calculate" + "/getEk?name=" + name, String.class);
        upTime = System.currentTimeMillis() - beginTime;
        update(upTime, 30.1744, 48.077);
        Ek = new Double(result);
        res = Ek <= 10;
        return res + "";
    }

    @GetMapping("/test")
    public String test() throws InterruptedException {
        String result1 = restTemplate.getForObject("http://door-energy-calculate" + "/test?name=" + name, String.class);
        return result1;
    }

    private void update(long upTime, double begin, double end) {
        LinkedHashMap<String, Double> weightMap = serviceMap.getOrDefault(NodeRule275.curName, new LinkedHashMap<>());
        Double weight = weightMap.getOrDefault(NodeRule275.select, w0);
        if (upTime < begin) weight += delta;
        else if (upTime > end) weight -= delta;
        if (weight < 0) weight = 0.0;
        weightMap.put(NodeRule275.select, weight);
        serviceMap.put(NodeRule275.curName, weightMap);
//        System.out.println("controller: serverName: "+NodeRule274.curName+" selectName: "+NodeRule274.select);
    }

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(), //注意这里不要定义队列名称,系统会随机产生
            exchange = @Exchange(value = "door-energy-calculate", type = ExchangeTypes.FANOUT)
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
