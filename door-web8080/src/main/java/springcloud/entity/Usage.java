package springcloud.entity;

import com.sun.management.OperatingSystemMXBean;

import java.lang.management.ManagementFactory;

public class Usage {
    public OperatingSystemMXBean bean = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();

    public double getSystemLoadAverage() {
        return bean.getSystemLoadAverage();
    }
    public double getUseSwapSpaceSize() {
        return bean.getTotalSwapSpaceSize() / 1024 / 1024-bean.getFreeSwapSpaceSize() / 1024 / 1024;
    }

    public double getUsePhysicalMemorySize() {
        return bean.getTotalPhysicalMemorySize() / 1024 / 1024-bean.getFreePhysicalMemorySize() / 1024 / 1024;
    }

    public double getCommittedVirtualMemorySize() {
        return bean.getCommittedVirtualMemorySize() / 1024 / 1024;
    }

    public double processusage(){
        double processCpuLoad = bean.getProcessCpuLoad();
        return processCpuLoad*100;
    }
    public double systemusage(){
        double systemCpuLoad = bean.getSystemCpuLoad();
        return systemCpuLoad*100;
    }

    public  double totalMemory(){
        return Runtime.getRuntime().totalMemory() / 1024 / 1024;
    }

    public double useMemory(){
        return this.totalMemory()-Runtime.getRuntime().freeMemory() / 1024 / 1024;
    }
    public int totalThreadCount(){
        ThreadGroup parentThread;
        for (parentThread = Thread.currentThread().getThreadGroup(); parentThread
                .getParent() != null; parentThread = parentThread.getParent())
            ;
        int totalThread = parentThread.activeCount();

        return totalThread;
    }


}
