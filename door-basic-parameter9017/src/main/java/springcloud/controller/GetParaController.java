package springcloud.controller;


import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RefreshScope
public class GetParaController {
    @Autowired
    RabbitTemplate rabbitTemplate;
    private long time = 9;
    public static boolean flag1 = false;
    public static boolean flag2 = false;
    public static boolean flag3 = false;
    int num1 = 0;
    int num2 = 0;
    int num3 = 0;

    @GetMapping("/getJJ")
    public String getJJ(HttpServletRequest request) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//        num1++;
//        if (!flag1 && num1 > 600) {
//            flag1 = true;
//            String para = "door-basic-parameter/" + request.getLocalAddr() + ":" + request.getServerPort();
//            rabbitTemplate.convertAndSend("door-basic-parameter", "door-basic-parameter", para);
//        }
//        if (flag1) {
//            try {
//                Thread.sleep(30);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
        Double JJ = 2.0;
        return JJ + "";
    }

    @GetMapping("/gett")
    public String gett(HttpServletRequest request) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//        num2++;
//        if (!flag2 && num2 > 600) {
//            flag2 = true;
//            String para = "door-basic-parameter/" + request.getLocalAddr() + ":" + request.getServerPort();
//            rabbitTemplate.convertAndSend("door-basic-parameter", "door-basic-parameter", para);
//        }
//        if (flag2) {
//            try {
//                Thread.sleep(30);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
        Double t = 10.0;
        return t + "";
    }

    @GetMapping("/getMk")
    public String getMk(HttpServletRequest request) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//        num3++;
//        if (!flag3 && num3 > 600) {
//            flag3 = true;
//            String para = "door-basic-parameter/" + request.getLocalAddr() + ":" + request.getServerPort();
//            rabbitTemplate.convertAndSend("door-basic-parameter", "door-basic-parameter", para);
//        }
//        if (flag3) {
//            try {
//                Thread.sleep(30);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
        Double Mk = 20.0;
        return Mk + "";
    }

    @GetMapping("/test")
    public String get() {


        return "1";
    }


}
