package com.history.gismap.service.impl;

import com.history.gismap.dao.MapDao;
import com.history.gismap.localcache.GeometryCache;
import com.history.gismap.model.GeometryModel;
import com.history.gismap.service.MapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MapServiceImpl implements MapService {
    @Autowired
    private GeometryCache geometryCache;
    @Override
    public List<GeometryModel> getDynastyGeom(String category,Integer start,Integer end){
        return geometryCache.getDynastyGeometry(category,start,end);
    }

}
