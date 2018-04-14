package org.spring.springboot.dao;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.spring.springboot.domain.ImageServerDTO;
import java.util.List;
/**
 * Created by IntelliJ IDEA.
 * User: zhoudapeng
 * Date: 2018/4/14
 */
@Mapper
public interface ImageSourceDao {
    List<ImageServerDTO> queryImageTypeList(@Param("imageType") Integer imageType);

}