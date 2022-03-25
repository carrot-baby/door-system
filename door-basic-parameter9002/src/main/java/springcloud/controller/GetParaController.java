package springcloud.controller;


import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope
public class GetParaController {

    private long time = 6;
    public static boolean flag1 = false;
    public static boolean flag2 = false;
    public static boolean flag3 = false;

    @GetMapping("/getJJ")
    public String getJJ() {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Double JJ = 2.0;
        return JJ + "";
    }

    @GetMapping("/gett")
    public String gett() {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Double t = 10.0;
        return t + "";
    }

    @GetMapping("/getMk")
    public String getMk() {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Double Mk = 20.0;
        return Mk + "";
    }

    @GetMapping("/test")
    public String get() {


        return "2";
    }


}
