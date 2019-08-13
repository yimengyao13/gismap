package com.history.gismap;

import com.history.gismap.dao.MapDao;
import com.history.gismap.localcache.GeometryCache;
import com.history.gismap.model.GeometryModel;
import com.history.gismap.service.MapService;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.LineString;
import com.vividsolutions.jts.io.ParseException;
import com.vividsolutions.jts.io.WKTReader;
import org.geotools.geojson.geom.GeometryJSON;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.io.StringWriter;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GismapApplicationTests {
    @Autowired
    private MapDao mapDao;
    @Autowired
    private MapService mapService;
    @Autowired
    private GeometryCache geometryCache;
    @Test
    public void contextLoads() {
        GeometryModel geometryModel=mapDao.getCntyPoint(1);
        System.out.println(geometryModel.getGeometry());
        GeometryModel geometryModel1=mapDao.getPrefPoint(1);
        System.out.println(geometryModel1.getGeometry());
        GeometryModel geometryModel2=mapDao.getprefPolygon(1);
        System.out.println(geometryModel2.getGeometry());
    }
    @Test
    public void getCache(){
        List<GeometryModel> resultcnty=geometryCache.getDynastyGeometry("cntypts",1376,1911);
        List<GeometryModel> resultprefpts=geometryCache.getDynastyGeometry("prefpts",1376,1911);
        List<GeometryModel> resultprefpgn=geometryCache.getDynastyGeometry("prefpgn",1376,1911);

        System.out.println(resultcnty.get(0).getBegYr());
        System.out.println(resultcnty.get(0).getGeometry());
        System.out.println(resultprefpgn.get(0).getGeometry());
        System.out.println(resultprefpts.get(0).getGeometry());
    }

    @Test
    public void tojson() throws ParseException {
        GeometryFactory geometryFactory=new GeometryFactory();
        WKTReader reader = new WKTReader( geometryFactory );
        LineString lineString = (LineString)reader.read("LINESTRING (254058.76074485347 475001.2186020431, 255351.04293761664 474966.9279243938)");
        GeometryJSON geometryJSON=new GeometryJSON();
        StringWriter writer = new StringWriter();
        try {
            geometryJSON.write(lineString,writer);

        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(writer.toString());

    }


}
