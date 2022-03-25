package common;

import com.netflix.loadbalancer.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Component
public class MySelfRule extends RoundRobinRule {

    private volatile List<Double> accumulatedWeights = new ArrayList();
    String name = "";
    volatile int num=0;
    MySelfRule.ServerWeight sw=new MySelfRule.ServerWeight();
    private static final Logger logger = LoggerFactory.getLogger(LBRule.class);
    public MySelfRule() {
    }
    public MySelfRule(ILoadBalancer lb) {
        super(lb);
    }
    public void setLoadBalancer(ILoadBalancer lb) {
        super.setLoadBalancer(lb);
        if (lb instanceof BaseLoadBalancer) {
            this.name = ((BaseLoadBalancer) lb).getName();
        }

    }
    public Server choose(ILoadBalancer lb, Object key) {

            sw.maintainWeights();

        if (lb == null) {
            return null;
        } else {
            Server server = null;
            while(server == null) {
                System.out.println(this.name);
                List<Double> currentWeights = this.accumulatedWeights;
                List<Server> allList = lb.getAllServers();
                int serverCount = allList.size();
                if (serverCount == 0) {
                    return null;
                }

                    server = super.choose(this.getLoadBalancer(), key);
                    if (server == null) {
                        return server;
                    }

            }
            return server;
        }
    }

    class ServerWeight {
        ServerWeight() {}
        List<Double> total=new ArrayList();
        int instanceCount;
        int index=0;
        public void maintainWeights() {
            ILoadBalancer lb = MySelfRule.this.getLoadBalancer();
            AbstractLoadBalancer nlb = (AbstractLoadBalancer)lb;
            LoadBalancerStats stats = nlb.getLoadBalancerStats();
            if (stats != null) {
                List<Double> finalWeights = new ArrayList();
                Iterator var20 = nlb.getAllServers().iterator();
                while(var20.hasNext()) {
                    Server serverx = (Server)var20.next();
                    ServerStats ssx = stats.getSingleServerStat(serverx);
                    long failureCount = ssx.getFailureCount();
                    int successiveConnectionFailureCount = ssx.getSuccessiveConnectionFailureCount();
                    System.out.println(serverx.getHostPort()+" : " +failureCount+"  "+successiveConnectionFailureCount);
                    double weight =1;finalWeights.add(weight);
                }
                MySelfRule.this.setWeights(finalWeights);
                return;
            }
        }
    }
    void setWeights(List<Double> weights) {
        this.accumulatedWeights = weights;
    }
}