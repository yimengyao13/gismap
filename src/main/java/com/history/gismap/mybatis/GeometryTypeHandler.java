package com.history.gismap.mybatis;

import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.io.ParseException;
import com.vividsolutions.jts.io.WKBReader;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;
import org.postgresql.util.PGobject;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
@MappedTypes(Geometry.class)
public class GeometryTypeHandler  extends BaseTypeHandler<Geometry> {

    public void setNonNullParameter(PreparedStatement preparedStatement, int i, Geometry geometry, JdbcType jdbcType) throws SQLException {
        PGobject pGobject=new PGobject();
        pGobject.setValue(geometry.toString());
        pGobject.setType("geometry");
        preparedStatement.setObject(i,pGobject);
    }

    public Geometry getNullableResult(ResultSet resultSet, String columnName) throws SQLException {
        PGobject pGgeometry= (PGobject) resultSet.getObject(columnName);
        if(pGgeometry==null){
            return null;
        }else{
            WKBReader wkbReader=new WKBReader();
            try {
                return wkbReader.read(WKBReader.hexToBytes(pGgeometry.getValue()));
            } catch (ParseException e) {
                e.printStackTrace();
                return null;
            }
        }
    }

    public Geometry getNullableResult(ResultSet resultSet, int columnIndex) throws SQLException {
        PGobject pGgeometry= (PGobject) resultSet.getObject(columnIndex);
        if(pGgeometry==null){
            return null;
        }else{
            WKBReader wkbReader=new WKBReader();
            try {
                return wkbReader.read(WKBReader.hexToBytes(pGgeometry.getValue()));
            } catch (ParseException e) {
                e.printStackTrace();
                return null;
            }
        }
    }

    public Geometry  getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
        PGobject pGgeometry= (PGobject) callableStatement.getObject(i);
        if(pGgeometry==null){
            return null;
        }else{
            WKBReader wkbReader=new WKBReader();
            try {
                return wkbReader.read(WKBReader.hexToBytes(pGgeometry.getValue()));
            } catch (ParseException e) {
                e.printStackTrace();
                return null;
            }
        }
    }

}
