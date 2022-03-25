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
import springcloud.config.NodeRule273;

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
    private Double V1;
    private Double S1;
    private Double t;
    private String result1;
    private String result2;
    private String name = "15";
    long beginTime;
    long upTime;
    double w0 = 10.0;
    double delta = 0.01;
    int num = 0;
    public static boolean flag = false;
    public static HashMap<String, LinkedHashMap<String, Double>> serviceMap = new HashMap<>();

    @GetMapping("/getV1")
    public String getTicket(HttpServletRequest request) {
        try {
            Thread.sleep(12);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//
//        num++;
//        if (!flag && num > 600) {
//            flag = true;
//            String para = "door-speed-calculate/" + request.getLocalAddr() + ":" + request.getServerPort();
//            rabbitTemplate.convertAndSend("door-speed-calculate", "door-speed-calculate", para);
//        }
//        if (flag) {
//            try {
//                Thread.sleep(100);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
        beginTime = System.currentTimeMillis();
        result1 = restTemplate.getForObject("http://door-section-calculate" + "/getS1?name=" + name, String.class);
        upTime = System.currentTimeMillis() - beginTime;
        update(upTime, 10.0808, 17.1515);
        S1 = new Double(result1);
        beginTime = System.currentTimeMillis();
        result2 = restTemplate.getForObject("http://door-basic-parameter" + "/gett?name=" + name, String.class);
        upTime = System.currentTimeMillis() - beginTime;
        update(upTime, 6.0048, 9.3215);
        t = new Double(result2);
        V1 = S1 / (1000 * t);
        return V1 + "";
    }

    @GetMapping("/test")
    public String test() throws InterruptedException {
        return "11";
    }

    private void update(long upTime, double begin, double end) {
        LinkedHashMap<String, Double> weightMap = serviceMap.getOrDefault(NodeRule273.curName, new LinkedHashMap<>());
        Double weight = weightMap.getOrDefault(NodeRule273.select, w0);
        if (upTime < begin) weight += delta;
        else if (upTime > end) weight -= delta;
        if (weight < 0) weight = 0.0;
        weightMap.put(NodeRule273.select, weight);
        serviceMap.put(NodeRule273.curName, weightMap);
//        System.out.println("controller: serverName: "+NodeRule274.curName+" selectName: "+NodeRule274.select);
    }

    @RabbitListener(bindings = {@QueueBinding(
            value = @Queue(), //注意这里不要定义队列名称,系统会随机产生
            exchange = @Exchange(value = "door-section-calculate", type = ExchangeTypes.FANOUT)
    ), @QueueBinding(
            value = @Queue(), //注意这里不要定义队列名称,系统会随机产生
            exchange = @Exchange(value = "door-basic-parameter", type = ExchangeTypes.FANOUT)
    )
    }
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
