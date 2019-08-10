package com.history.gismap.service;
import com.history.gismap.model.GeometryModel;
import java.util.List;
public interface MapService {
    List<GeometryModel> getDynastyGeom(String category,Integer start,Integer end);
}
