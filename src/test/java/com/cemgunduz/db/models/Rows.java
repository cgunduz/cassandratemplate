package com.cemgunduz.db.models;

/**
 * Created by cem.gunduz on 21.06.2015.
 */
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "rows")
@XmlAccessorType (XmlAccessType.FIELD)
public class Rows
{
    @XmlElement(name = "row")
    private List<Row> rows;

    public List<Row> getRows() {
        return rows;
    }

    public void setRows(List<Row> employees) {
        this.rows = employees;
    }
}
