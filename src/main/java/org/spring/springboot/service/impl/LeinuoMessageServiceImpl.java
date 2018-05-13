package org.spring.springboot.service.impl;

import org.apache.ibatis.annotations.Param;
import org.spring.springboot.dao.LeinuoMessageDao;
import org.spring.springboot.domain.LeinuoMessageDTO;
import org.spring.springboot.service.LeinuoMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
/**
 * Created by yinxiaoen on 2018/5/10.
 */
@Service
public class LeinuoMessageServiceImpl implements LeinuoMessageService {
    @Autowired
    private LeinuoMessageDao leinuoMessageDao;
    @Override
    public List<LeinuoMessageDTO> queryMessageByUserID(LeinuoMessageDTO leinuoMessageDTO) {
        return leinuoMessageDao.queryMessageByUserID(leinuoMessageDTO);
    }

    @Override
    public Integer queryMessageCount(LeinuoMessageDTO leinuoMessageDTO) {
        return leinuoMessageDao.queryMessageCount(leinuoMessageDTO);
    }

    @Override
    public void addMessageByUser(List<LeinuoMessageDTO> list) {
        leinuoMessageDao.addMessageByUser(list);
    }

    @Override
    public void deleteMessageByIDs(@Param("ids") String ids) {
        leinuoMessageDao.deleteMessageByIDs(ids);
    }

    @Override
    public LeinuoMessageDTO queryMessageByID(Long id) {
        return leinuoMessageDao.queryMessageByID(id);
    }
}
