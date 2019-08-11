package com.history.gismap.controller;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.history.gismap.model.GeometryModel;
import com.history.gismap.service.MapService;
import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.Geometry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
@RequestMapping(value = "/history")
@Slf4j
public class MapController {
    @Autowired
    private MapService mapService;
    /**
     * 根据表名和时间区间输出对象
     * @param category 表名简写
     * @param start 起始时间
     * @param end 终止时间
     * @return
     */
    @ResponseBody
    @GetMapping("/geometry")
    public JSONObject getPoint(@RequestParam("category") String category,@RequestParam("start") Integer start,@RequestParam("end") Integer end){
        try {
            List<GeometryModel> result=mapService.getDynastyGeom(category,start,end);
            JSONObject jsonObject=new JSONObject();
            jsonObject.put("number",result.size());
            JSONArray jsonArray=new JSONArray();
            for (GeometryModel g:result) {
                JSONObject geom=new JSONObject();
                geom.put("gid",g.getGId());
                geom.put("namepy",g.getNamePy());
                geom.put("namech",g.getNameCh());
                geom.put("nameft",g.getNameFt());
                geom.put("presloc",g.getPresLoc());
                geom.put("typepy",g.getTypePy());
                geom.put("typech",g.getTypeCh());
                geom.put("levrank",g.getLevRank());
                geom.put("begyr",g.getBegYr());
                geom.put("begrule",g.getBegRule());
                geom.put("endyr",g.getEndYr());
                geom.put("endrule",g.getEndRule());
                geom.put("geosrc",g.getGeoSrc());
                geom.put("compiler",g.getCompiler());
                geom.put("gecomplr",g.getGecomplr());
                geom.put("checker",g.getChecker());
                geom.put("entdate",g.getEntDate());
                geom.put("begchgty",g.getBegChgTy());
                geom.put("endchgty",g.getEndChgTy());
                geom.put("geometry",g.getGeometry().toString());
                jsonArray.add(geom);
            }
            jsonObject.put("list",jsonArray);
            log.info("入参类别：{}，起始时间：{}，截至时间：{}",category,start,end);
            return jsonObject;
        }catch (Exception e){
            log.error("程序错误类型：",e);
            return null;
        }
    }
    private JSONObject geometryToJson(Geometry geometry){
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("type",geometry.getGeometryType());
        JSONArray coorList=new JSONArray();
        for (int i=0;i<geometry.getNumGeometries();i++){
            JSONArray coors=new JSONArray();
            Coordinate[] coordinates=geometry.getGeometryN(i).getCoordinates();
            for (Coordinate c:coordinates) {
                JSONObject jsonObjectCoor=new JSONObject();
                jsonObjectCoor.put("lng",c.x);
                jsonObjectCoor.put("lat",c.y);
                coors.add(jsonObjectCoor);
            }
            coorList.add(coors);
        }
        jsonObject.put("coordinates",coorList);
        return jsonObject;
    }
}
