package com.history.gismap.service.impl;

import com.history.gismap.dao.MapDao;
import com.history.gismap.model.PointModel;
import com.history.gismap.service.MapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class MapServiceImpl implements MapService {
    @Autowired
    private MapDao mapDao;
    @Override
    public List<PointModel> getCntyPointByGid(Integer gId){
        return mapDao.getCntyPoint(gId);
    }
    @Override
    public int addCntyPoint(PointModel pointModel){
        return mapDao.insertCntyPoint(pointModel);
    }
    @Override
    public int modifyCntyPoint(PointModel pointModel){
        return mapDao.updateCntyPoint(pointModel);
    }
    @Override
    public int removeCntyPoint(Integer gId){
        return mapDao.deleteCntyPoint(gId);
    }
}
