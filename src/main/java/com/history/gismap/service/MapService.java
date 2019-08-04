package com.history.gismap.service;

import com.history.gismap.model.PointModel;
import org.springframework.stereotype.Service;

import java.util.List;
public interface MapService {
    List<PointModel> getCntyPointByGid(Integer gId);
    int addCntyPoint(PointModel pointModel);
    int modifyCntyPoint(PointModel pointModel);
    int removeCntyPoint(Integer gId);

}
