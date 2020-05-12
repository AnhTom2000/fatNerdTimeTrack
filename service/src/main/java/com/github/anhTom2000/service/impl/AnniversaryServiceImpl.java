package com.github.anhTom2000.service.impl;

import com.github.anhTom2000.dao.AnniversaryMapper;
import com.github.anhTom2000.dto.AnniversaryDTO;
import com.github.anhTom2000.dto.ResultDTO;
import com.github.anhTom2000.entity.Anniversary;
import com.github.anhTom2000.service.AnniversaryService;
import com.github.anhTom2000.utils.BeanConvert.BeanConvertUtil;
import com.github.anhTom2000.utils.Generator.IdGenerator;
import com.github.anhTom2000.utils.Generator.impl.SnowflakeIdGenerator;
import com.github.anhTom2000.utils.httpcode.Httpcode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description : TODO  纪念如服务的具体实现
 * @Author : Weleness
 * @Date : 2020/05/11
 */
@Service("anniversaryService")
public class AnniversaryServiceImpl implements AnniversaryService {

    @Qualifier("anniversaryMapper")
    @Autowired
    private AnniversaryMapper anniversaryMapper;

    private final IdGenerator idGenerator = SnowflakeIdGenerator.getInstance();

    @Override
    public List<AnniversaryDTO> findAllAnniversary(Long userId) {
        List<Anniversary> anniversary = anniversaryMapper.findAllAnniversary(userId);
        return anniversary.size() > 0 ? BeanConvertUtil.convertList(anniversary, AnniversaryDTO.class) : null;
    }

    @Override
    public AnniversaryDTO insertAnniversary(Anniversary anniversary, Long userId) {
        anniversary.setAnniversaryId(idGenerator.generateId());
        return anniversaryMapper.insertAnniversary(anniversary, userId) > 0 ? BeanConvertUtil.create(anniversary, AnniversaryDTO.class) : null;
    }

    @Override
    public ResultDTO updateAnniversary(Anniversary anniversary) {
        return anniversaryMapper.updateAnniversary(anniversary) > 0 ? ResultDTO.builder().code(Httpcode.OK_CODE.getCode()).message("成功").status(true).build() : ResultDTO.builder().code(Httpcode.CLIENT_ERROR_CODE.getCode()).message("错误").status(false).build();
    }


    @Override
    public ResultDTO deleteAnniversary(Long anniversaryId) {
        anniversaryMapper.deleteAnniversary(anniversaryId);
        return ResultDTO.builder().code(Httpcode.OK_CODE.getCode()).message("成功").status(true).build();
    }
}
