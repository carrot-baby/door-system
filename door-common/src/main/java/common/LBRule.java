package common;

import com.netflix.loadbalancer.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class LBRule extends RoundRobinRule {
    private volatile List<Double> accumulatedWeights = new ArrayList();
    String name = "";
    volatile int num=0;
    ServerWeight sw=new ServerWeight();
    private static final Logger logger = LoggerFactory.getLogger(LBRule.class);
    public LBRule() {
    }
    public LBRule(ILoadBalancer lb) {
        super(lb);
    }
    public void setLoadBalancer(ILoadBalancer lb) {
        super.setLoadBalancer(lb);
        if (lb instanceof BaseLoadBalancer) {
            this.name = ((BaseLoadBalancer) lb).getName();
        }

    }
    public Server choose(ILoadBalancer lb, Object key) {
        if(num==30) sw.remove();
            if(num%10==0&&num>=50) {
                sw.maintainWeights();
            }
        System.out.println(++num);
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
                int serverIndex = 0;
                if (serverCount == currentWeights.size()) {
                    serverIndex=currentWeights.indexOf(Collections.min(currentWeights));
                    server =allList.get(serverIndex);
                } else {
                    server = super.choose(this.getLoadBalancer(), key);
                    if (server == null) {
                        return server;
                    }
                }
            }
            return server;
        }
    }

    class ServerWeight {
        ServerWeight() {}
        List<Double> total=new ArrayList();
        List<Double> count=new ArrayList();
        int instanceCount;
        int index=0;
        public void maintainWeights() {
            ILoadBalancer lb = LBRule.this.getLoadBalancer();
            AbstractLoadBalancer nlb = (AbstractLoadBalancer)lb;
            LoadBalancerStats stats = nlb.getLoadBalancerStats();
            if (stats != null) {
                List<Double> finalWeights = new ArrayList();
                Iterator var20 = nlb.getAllServers().iterator();
                while(var20.hasNext()) {
                    int temp=index++%+instanceCount;
                    Server serverx = (Server)var20.next();
                    ServerStats ssx = stats.getSingleServerStat(serverx);
                    double weight =(ssx.getTotalRequestsCount()*ssx.getResponseTimeAvg()-total.get(temp))/(ssx.getTotalRequestsCount()-count.get(temp));finalWeights.add(weight);

                    System.out.println("port: "+serverx.getHostPort()+" "+weight+" totalTime: "+total.get(temp)+" index: "+temp+" count"+count.get(temp));
                }
                LBRule.this.setWeights(finalWeights);
                return;
            }
        }

        public void remove(){
            ILoadBalancer lb = LBRule.this.getLoadBalancer();
            AbstractLoadBalancer nlb = (AbstractLoadBalancer)lb;
            LoadBalancerStats stats = nlb.getLoadBalancerStats();
            Set<String> availableZones = stats.getAvailableZones();
            for(String s:availableZones)  instanceCount = stats.getInstanceCount(s);
            double totalTime;
            double countTemp;
            if (stats != null) {
                Iterator var20 = nlb.getAllServers().iterator();
                while(var20.hasNext()) {
                    Server serverx = (Server)var20.next();
                    ServerStats ssx = stats.getSingleServerStat(serverx);
                        countTemp=ssx.getTotalRequestsCount();
                          count.add(countTemp);
                        totalTime=countTemp*ssx.getResponseTimeAvg();total.add(totalTime);
                }
            }
        }
    }
    void setWeights(List<Double> weights) {
        this.accumulatedWeights = weights;
    }
}
