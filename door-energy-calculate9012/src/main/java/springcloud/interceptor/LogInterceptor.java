package springcloud.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.NamedThreadLocal;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import springcloud.controller.GetParaController;
import springcloud.entity.MetricEntity;
import springcloud.entity.Usage;
import springcloud.mapper.MetricMapper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j

public class LogInterceptor implements HandlerInterceptor {
    private static final ThreadLocal<Long> START_TTIME_THREAD_LOCAL = new NamedThreadLocal<Long>("ThreadLocal StartTime");
    @Autowired
    MetricMapper metricMapper;
    long uptime;


    /**
     * 拦截的方法执行之前调用
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object handler) {
        //1、开始时间
        long beginTime = System.currentTimeMillis();
        //线程绑定变量（该数据只有当前请求的线程可见）
        START_TTIME_THREAD_LOCAL.set(beginTime);
        return true;
    }

    /**
     * 拦截的方法执行过程中，返回model and view之前调用
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) {
        uptime = System.currentTimeMillis() - START_TTIME_THREAD_LOCAL.get();
        Usage usage = new Usage();
        MetricEntity metricEntity = new MetricEntity();
        metricEntity.setInstanceName("18");
        metricEntity.setAcceptName(request.getParameter("name"));
        metricEntity.setUri(request.getRequestURI());
        metricEntity.setSysCpuRatio(usage.systemusage());
        metricEntity.setProCpuRatio(usage.processusage());
        metricEntity.setTotalMemory(usage.totalMemory());
        metricEntity.setCurMemory(usage.useMemory());
        metricEntity.setTime(uptime);
        metricEntity.setSystemLoadAverage(usage.getSystemLoadAverage());
        metricEntity.setCommittedVirtualMemorySize(usage.getCommittedVirtualMemorySize());
        metricEntity.setUsePhysicalMemorySize(usage.getUsePhysicalMemorySize());
        metricEntity.setTotalThread(usage.totalThreadCount());
        metricEntity.setUseSwapSpaceSize(usage.getUseSwapSpaceSize());
        metricEntity.setFlag(GetParaController.flag);
        metricMapper.insert(metricEntity);
    }


    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
                                Object handler, Exception ex) throws Exception {

        // 打印JVM信息。
        //1. 得到线程绑定的局部变量（开始时间）
      /*  long beginTime = START_TTIME_THREAD_LOCAL.get();
        //2、结束时间
        long endTime = System.currentTimeMillis();
        Map<String, String[]> pm = request.getParameterMap();
        log.info("请求次数:{}次\t耗时:{}ms\tURI:{}\t请求参数:{}\t最大内存:{}m\t已分配内存:{}m\t已分配内存中的剩余空间:{}m\t最大可用内存:{}m",
                count,(endTime - beginTime), request.getRequestURI(), JSON.toJSON(pm), Runtime.getRuntime().maxMemory() / 1024 / 1024, Runtime.getRuntime().totalMemory() / 1024 / 1024, Runtime.getRuntime().freeMemory() / 1024 / 1024,
                (Runtime.getRuntime().maxMemory() - Runtime.getRuntime().totalMemory() + Runtime.getRuntime().freeMemory()) / 1024 / 1024);
*/


    }
}
