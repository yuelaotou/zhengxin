package org.xpup.hafmis.sysloan.common.domain.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
public class XieYiNum implements Serializable {

    /** identifier field */
    private Integer paraId;
    /** nullable persistent field */
    private String office;

    /** nullable persistent field */
    private String year;

    /** nullable persistent field */
    private String xieyiNum_id;
    
    private String officeid;
    
    private String type="";

    public String getOffice() {
      return office;
    }

    public void setOffice(String office) {
      this.office = office;
    }

    public Integer getParaId() {
      return paraId;
    }

    public void setParaId(Integer paraId) {
      this.paraId = paraId;
    }


    public String getYear() {
      return year;
    }

    public void setYear(String year) {
      this.year = year;
    }


    public String getXieyiNum_id() {
      return xieyiNum_id;
    }

    public void setXieyiNum_id(String xieyiNum_id) {
      this.xieyiNum_id = xieyiNum_id;
    }

    public String getOfficeid() {
      return officeid;
    }

    public void setOfficeid(String officeid) {
      this.officeid = officeid;
    }

    public String getType() {
      return type;
    }

    public void setType(String type) {
      this.type = type;
    }

}
