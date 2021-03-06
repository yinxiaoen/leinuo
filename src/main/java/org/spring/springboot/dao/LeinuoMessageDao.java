package org.spring.springboot.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.spring.springboot.domain.LeinuoMessageDTO;

import java.util.List;

/**
 * Created by yinxiaoen on 2018/5/10.
 */
@Mapper
public interface LeinuoMessageDao {
    List<LeinuoMessageDTO> queryMessageByUserID(LeinuoMessageDTO leinuoMessageDTO);

    Integer queryMessageCount(LeinuoMessageDTO leinuoMessageDTO);

    void addMessageByUser(List<LeinuoMessageDTO> list);

    void deleteMessageByIDs(@Param("ids") String ids);

    LeinuoMessageDTO  queryMessageByID(Long id);


    void updateMessage(LeinuoMessageDTO leinuoMessageDTO);

}
