package com.history.gismap.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPath;
import com.history.gismap.model.PointModel;
import com.history.gismap.service.MapService;
import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.Point;
import com.vividsolutions.jts.io.ParseException;
import com.vividsolutions.jts.io.WKTReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
@Controller
@RequestMapping(value = "/history")
public class MapController {
    @Autowired
    private MapService mapService;
    @ResponseBody
    @GetMapping("/pointmodel")
    public JSONObject getPoint(@RequestParam("gid") Integer gId){
        PointModel pointModel=mapService.getCntyPointByGid(gId).get(0);
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("gid",pointModel.getGId());
        jsonObject.put("namech",pointModel.getNameCh());
        JSONObject geometry=new JSONObject();
        geometry.put("type",pointModel.getGeometry().getGeometryType());
        JSONArray coordinateArray=new JSONArray();
        Coordinate[] coordinates=pointModel.getGeometry().getCoordinates();
        JSONObject coor=new JSONObject();
        coor.put("longitude",coordinates[0].x);
        coor.put("latitude",coordinates[0].y);
        coordinateArray.add(coor);
        geometry.put("coordinate",coordinateArray);
        jsonObject.put("geometry",geometry);
        return jsonObject;
    }
    @ResponseBody
    @PostMapping("/add")
    public int addPoint(@RequestBody JSONObject request){
        PointModel pointModel=new PointModel();
        pointModel.setGId((Integer) JSONPath.eval(request,"$.gId"));
        pointModel.setNameCh((String) JSONPath.eval(request,"$.nameCh"));
        String pointStr= (String) JSONPath.eval(request,"$.point");
        GeometryFactory geometryFactory = new GeometryFactory();
        WKTReader reader = new WKTReader( geometryFactory );
        try {
            Geometry point = (Point) reader.read(pointStr);
            pointModel.setGeometry(point);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return mapService.addCntyPoint(pointModel);
    }
    @ResponseBody
    @PostMapping("/modify")
    public int update(@RequestBody JSONObject request){
        PointModel pointModel=new PointModel();
        pointModel.setGId((Integer) JSONPath.eval(request,"$.gId"));
        pointModel.setNameCh((String) JSONPath.eval(request,"$.nameCh"));
        String pointStr= (String) JSONPath.eval(request,"$.point");
        GeometryFactory geometryFactory = new GeometryFactory();
        WKTReader reader = new WKTReader( geometryFactory );
        try {
            Geometry point = (Point) reader.read(pointStr);
            pointModel.setGeometry(point);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return mapService.modifyCntyPoint(pointModel);
    }
    @ResponseBody
    @GetMapping("/remove")
    public int removetPoint(@RequestParam("gid") Integer gId){
        return mapService.removeCntyPoint(gId);
    }
}
