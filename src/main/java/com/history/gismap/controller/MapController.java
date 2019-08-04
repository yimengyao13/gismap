package com.history.gismap.controller;

import com.history.gismap.model.PointModel;
import com.history.gismap.service.MapService;
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
    public PointModel getPoint(@RequestParam("gid") Integer gId){
        return mapService.getCntyPointByGid(gId).get(0);
    }
    @ResponseBody
    @PostMapping("/add")
    public int addPoint(PointModel pointModel){
        return mapService.addCntyPoint(pointModel);
    }
    @ResponseBody
    @PostMapping("/modify")
    public int update(PointModel pointModel){
        return mapService.modifyCntyPoint(pointModel);
    }
    @ResponseBody
    @GetMapping("/remove")
    public int removetPoint(@RequestParam("gid") Integer gId){
        return mapService.removeCntyPoint(gId);
    }
}
