package com.history.gismap.localcache;

import com.history.gismap.dao.MapDao;
import com.history.gismap.model.GeometryModel;
import lombok.extern.java.Log;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class GeometryCache implements InitializingBean {
    Map<String, List<GeometryModel>> historyGis =new HashMap<>();
    @Autowired
    private MapDao mapDao;
    @Override
    public void afterPropertiesSet() throws Exception {
        List<GeometryModel> cntyGeometry=new ArrayList<>();
        int i=1;
        while (mapDao.getCntyPoint(i)!=null){
            cntyGeometry.add(mapDao.getCntyPoint(i));
            i++;
        }
        historyGis.put("cntypts",cntyGeometry);
        List<GeometryModel>  prefPtsGeometry=new ArrayList<>();
        i=1;
        while (mapDao.getPrefPoint(i)!=null){
            prefPtsGeometry.add(mapDao.getCntyPoint(i));
            i++;
        }
        historyGis.put("prefpts",prefPtsGeometry);
        List<GeometryModel> prefPgnGeometry=new ArrayList<>();
        i=1;
        while (mapDao.getprefPolygon(i)!=null){
            prefPgnGeometry.add(mapDao.getprefPolygon(i));
            i++;
        }
        historyGis.put("prefpgn",prefPgnGeometry);
    }
    public List<GeometryModel> getDynastyGeometry(String category,Integer start,Integer end){
        List<GeometryModel> result=new ArrayList<>();
        for (GeometryModel g:historyGis.get(category)) {
            if((g.getBegYr()>=start && g.getBegYr()<=end)||(g.getEndYr()>=start&&g.getEndYr()<=end)){
                result.add(g);
            }
        }
        return result;
    }
}
