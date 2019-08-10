package com.history.gismap.dao;
import com.history.gismap.model.GeometryModel;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;
@Service
public interface MapDao {
    GeometryModel getCntyPoint(@Param("gId") Integer gId);
    GeometryModel getPrefPoint(@Param("gId") Integer gId);
    GeometryModel getprefPolygon(@Param("gId") Integer gId);
}
