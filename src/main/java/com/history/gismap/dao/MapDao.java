package com.history.gismap.dao;

import com.history.gismap.model.PointModel;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public interface MapDao {
    List<PointModel> getCntyPoint(@Param("gId") Integer gId);
    int insertCntyPoint(PointModel pointModel);
    int updateCntyPoint(PointModel pointModel);
    int deleteCntyPoint(@Param("gId") Integer gId);
}
