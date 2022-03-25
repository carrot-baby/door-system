package springcloud.config;

import com.netflix.loadbalancer.BaseLoadBalancer;
import com.netflix.loadbalancer.ILoadBalancer;
import com.netflix.loadbalancer.RoundRobinRule;
import com.netflix.loadbalancer.Server;
import org.springframework.stereotype.Component;
import springcloud.controller.GetParaController;

import java.util.*;

@Component
public class NodeRule272 extends RoundRobinRule {
    public static String curName = "";
    volatile int num = 0;
    private final Random random = new Random();
    public static String select = "";

    public static int serverCount;

    public NodeRule272() {
    }

    public NodeRule272(ILoadBalancer lb) {
        super(lb);
    }

    public void setLoadBalancer(ILoadBalancer lb) {
        super.setLoadBalancer(lb);
    }

    public Server choose(ILoadBalancer lb, Object key) {
        curName = ((BaseLoadBalancer) lb).getName();
        System.out.println("curName: " + curName);
        num++;
        Server server = null;
        List<Server> allList = lb.getAllServers();
        serverCount = allList.size();
        if (serverCount == 0) {
            return null;
        }
        int serverIndex = 0;
        if (num > 50) {
            List<Double> sectionList = new ArrayList<>();
            HashMap<String, LinkedHashMap<String, Double>> serviceMap = GetParaController.serviceMap;
            LinkedHashMap<String, Double> map = serviceMap.get(curName);
            List<Double> currentWeights = new ArrayList<>();
            int temp = 1;
            for (Map.Entry<String, Double> entry : map.entrySet()) {
                double weight = entry.getValue();
                if (temp == serverCount) {
                    currentWeights.add(0, weight);
                } else {
                    currentWeights.add(weight);
                }
                temp++;
                System.out.println("Instance = " + entry.getKey() + ", Weight = " + entry.getValue());
            }
            double weightSoFar = 0.0;
            for (double d : currentWeights) {
                weightSoFar += d;
                sectionList.add(weightSoFar);
            }
            double maxTotalWeight = sectionList.size() == 0 ? 0.0D : sectionList.get(sectionList.size() - 1);
            double randomWeight = this.random.nextDouble() * maxTotalWeight;
            int n = 0;
//            System.out.println("randomWeight = "+randomWeight);
            for (Iterator var13 = sectionList.iterator(); var13.hasNext(); ++n) {
                Double d = (Double) var13.next();

                if (d >= randomWeight) {
                    serverIndex = n;
                    break;
                }
            }
            server = allList.get(serverIndex);
            select = server.getHostPort();
        } else {
            server = super.choose(this.getLoadBalancer(), key);
            select = server.getHostPort();
            System.out.println("curName: " + curName + " select: " + select);
            if (server == null) {
                return server;
            }
        }
        return server;
    }
}
