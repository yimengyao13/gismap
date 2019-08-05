package com.history.gismap;

import com.history.gismap.dao.MapDao;
import com.history.gismap.model.PointModel;
import com.history.gismap.service.MapService;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.Point;
import com.vividsolutions.jts.io.ParseException;
import com.vividsolutions.jts.io.WKTReader;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GismapApplicationTests {
    @Autowired
    private MapDao mapDao;
    @Autowired
    private MapService mapService;
    @Test
    public void contextLoads() {
        List<PointModel> pointModelList=mapDao.getCntyPoint(1);
        System.out.println(pointModelList.get(0));
    }
    @Test
    public void add(){
        GeometryFactory geometryFactory = new GeometryFactory();
        WKTReader reader = new WKTReader( geometryFactory );
        try {
            Geometry point = reader.read("POINT (109.013388 32.715519)");
            PointModel pointModel=new PointModel();
            pointModel.setGId(14353);
            pointModel.setNameCh("test");
            pointModel.setGeometry(point);
            mapDao.insertCntyPoint(pointModel);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void addservice(){
        PointModel pointModel=new PointModel();
        pointModel.setGId(14354);
        pointModel.setNameCh("test");
        mapService.addCntyPoint(pointModel);
    }
    @Test
    public void delete(){
//        mapDao.deleteCntyPoint(14353);
        mapService.removeCntyPoint(14354);
    }
    @Test
    public void update(){
        GeometryFactory geometryFactory = new GeometryFactory();
        WKTReader reader = new WKTReader( geometryFactory );
        try {
            Geometry point = reader.read("POINT (0 0)");
            PointModel pointModel=new PointModel();
            pointModel.setGId(14353);
            pointModel.setNameCh("test");
            pointModel.setGeometry(point);
            mapDao.updateCntyPoint(pointModel);
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }
}
