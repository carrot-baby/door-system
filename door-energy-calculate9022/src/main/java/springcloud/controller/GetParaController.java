package springcloud.controller;


import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import springcloud.config.NodeRule274;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;

@RestController
public class GetParaController {
    @Autowired
    RabbitTemplate rabbitTemplate;
    @Autowired
    private RestTemplate restTemplate;
    private double Mk;
    private double V1;
    private double Ek;
    private String result1;
    private String result2;
    private String name = "19";
    long beginTime;
    long upTime;
    double w0 = 10.0;
    double delta = 0.01;
    int num = 0;
    public static boolean flag = false;
    public static HashMap<String, LinkedHashMap<String, Double>> serviceMap = new HashMap<>();
    @GetMapping("/getEk")
    public String getEk(HttpServletRequest request) {
        try {
            Thread.sleep(12);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

//        num++;
//        if(!flag&&num>700){
//            flag = true;
//            String para="door-energy-calculate/"+request.getLocalAddr()+":"+request.getServerPort();
//            rabbitTemplate.convertAndSend("door-energy-calculate","door-energy-calculate",para);        }
//        if (flag) {
//            try {
//                Thread.sleep(100);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
        beginTime = System.currentTimeMillis();
        result1 = restTemplate.getForObject("http://door-speed-calculate" + "/getV1?name=" + name, String.class);
        upTime = System.currentTimeMillis() - beginTime;
        update(upTime, 14.068, 28.1275);
        V1 = new Double(result1);
        beginTime = System.currentTimeMillis();
        result2 = restTemplate.getForObject("http://door-basic-parameter" + "/getMk?name=" + name, String.class);
        upTime = System.currentTimeMillis() - beginTime;
        update(upTime, 6.0048, 9.3215);
        Mk = new Double(result2);
        Ek = Mk * V1 * V1;
        return Ek + "";
    }

    @GetMapping("/test")
    public String test() {
        beginTime = System.currentTimeMillis();
        result1 = restTemplate.getForObject("http://door-speed-calculate" + "/test?name=" + name, String.class);
        upTime = System.currentTimeMillis() - beginTime;
        update(upTime, 2, 10);
        beginTime = System.currentTimeMillis();
        result2 = restTemplate.getForObject("http://door-basic-parameter" + "/test?name=" + name, String.class);
        upTime = System.currentTimeMillis() - beginTime;
        update(upTime, 2, 10);
        return "result1: " + result1 + " result2: " + result2;
    }

    private void update(long upTime, double begin, double end) {
        LinkedHashMap<String, Double> weightMap = serviceMap.getOrDefault(NodeRule274.curName, new LinkedHashMap<>());
        Double weight = weightMap.getOrDefault(NodeRule274.select, w0);
        if (upTime < begin) weight += delta;
        else if (upTime > end) weight -= delta;
        if (weight < 0) weight = 0.0;
        weightMap.put(NodeRule274.select, weight);
        serviceMap.put(NodeRule274.curName, weightMap);
//        System.out.println("controller: serverName: "+NodeRule274.curName+" selectName: "+NodeRule274.select);
    }

    @RabbitListener(bindings ={@QueueBinding(
            value = @Queue(), //注意这里不要定义队列名称,系统会随机产生
            exchange = @Exchange(value = "door-speed-calculate",type = ExchangeTypes.FANOUT)
    ),@QueueBinding(
            value = @Queue(), //注意这里不要定义队列名称,系统会随机产生
            exchange = @Exchange(value = "door-basic-parameter",type = ExchangeTypes.FANOUT)
    )
    }
    )
    public void  get(String para, Channel channel) throws IOException {
//        String[] split = para.split("/");
//        channel.basicQos(1);
//        LinkedHashMap<String, Double> weightMap  = serviceMap.getOrDefault(split[0], new LinkedHashMap<>());
//        weightMap.put(split[1],0.0);
//        serviceMap.put(split[0],weightMap);
//        System.out.println(split[0]+"重置 "+split[1]+" 权重成功");
//        System.out.println(para);

    }
}
