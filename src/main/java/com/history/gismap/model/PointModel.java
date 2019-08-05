package com.history.gismap.model;

import com.vividsolutions.jts.geom.Geometry;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PointModel {
    private Integer gId;
    private String nameCh;
    private Geometry geometry;

}
