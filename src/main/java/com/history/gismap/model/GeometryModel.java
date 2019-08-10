package com.history.gismap.model;

import com.vividsolutions.jts.geom.Geometry;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class GeometryModel {
    private Integer gId;
    private String namePy;
    private String nameCh;
    private String nameFt;
    private String presLoc;
    private String typePy;
    private String typeCh;
    private String levRank;
    private Integer begYr;
    private String begRule;
    private Integer endYr;
    private String endRule;
    private String geoSrc;
    private String compiler;
    private String gecomplr;
    private String checker;
    private String entDate;
    private String begChgTy;
    private String endChgTy;
    private Geometry geometry;
}
