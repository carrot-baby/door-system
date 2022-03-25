package springcloud.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;


@Data
@TableName("door_energy_calculate")
public class MetricEntity {
    /**
     * 请求次数
     */
    @TableId(value = "id",type = IdType.AUTO)
    private Long id;
    /**
     * 分配的内存空间
     */
    private Double totalMemory;
    /**
     * 正在使用的内存空间
     */
    private Double curMemory;
    /**
     * 正在使用的进程cpu使用率
     */
    private Double proCpuRatio;
    /**
     * 正在使用的系统cpu使用率
     */
    private Double sysCpuRatio;
    /**
     * 正在使用的线程数
     */
    private Integer totalThread;
    /**
     * 系统平均负载
     */
    private Double systemLoadAverage;
    /**
     * 空闲虚拟内存
     */
    private Double useSwapSpaceSize;
    /**
     * 空闲物理内存
     */
    private Double usePhysicalMemorySize;
    /**
     *committedVirtualMemorySize
     */
    private Double committedVirtualMemorySize;
    /**
     * 当前请求耗时
     */
    private Long time;
    private String acceptName;
    /**
     * 当前访问使用实例名字
     */
    private String instanceName;
    private String uri;
    private boolean flag;
}
