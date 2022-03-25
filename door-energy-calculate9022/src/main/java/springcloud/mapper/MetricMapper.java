package springcloud.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import springcloud.entity.MetricEntity;

@Mapper
public interface MetricMapper extends BaseMapper<MetricEntity> {

}
