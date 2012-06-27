package org.xpup.hafmis.sysfinance.common.domain.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
public class ReportMng implements Serializable {

    /** identifier field */
    private Integer id;

    /** persistent field */
    private String bookId;

    /** persistent field */
    private String tailtableName;

    /** persistent field */
    private String tailtableNameChinese;

    /** persistent field */
    private String colspan;

    /** persistent field */
    private String rowspan;

    /** persistent field */
    private String querymode;

    /** persistent field */
    private String urlid;

    /** persistent field */
    private Date createtime;

    /** persistent field */
    private String createperson;

    /** nullable persistent field */
    private String reserveA;

    /** nullable persistent field */
    private String reserveB;

    /** nullable persistent field */
    private String reserveC;

    /** full constructor */
    public ReportMng(Integer id, String bookId, String tailtableName, String tailtableNameChinese, String colspan, String rowspan, String querymode, String urlid, Date createtime, String createperson, String reserveA, String reserveB, String reserveC) {
        this.id = id;
        this.bookId = bookId;
        this.tailtableName = tailtableName;
        this.tailtableNameChinese = tailtableNameChinese;
        this.colspan = colspan;
        this.rowspan = rowspan;
        this.querymode = querymode;
        this.urlid = urlid;
        this.createtime = createtime;
        this.createperson = createperson;
        this.reserveA = reserveA;
        this.reserveB = reserveB;
        this.reserveC = reserveC;
    }

    /** default constructor */
    public ReportMng() {
    }

    /** minimal constructor */
    public ReportMng(Integer id, String bookId, String tailtableName, String tailtableNameChinese, String colspan, String rowspan, String querymode, String urlid, Date createtime, String createperson) {
        this.id = id;
        this.bookId = bookId;
        this.tailtableName = tailtableName;
        this.tailtableNameChinese = tailtableNameChinese;
        this.colspan = colspan;
        this.rowspan = rowspan;
        this.querymode = querymode;
        this.urlid = urlid;
        this.createtime = createtime;
        this.createperson = createperson;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBookId() {
        return this.bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getTailtableName() {
        return this.tailtableName;
    }

    public void setTailtableName(String tailtableName) {
        this.tailtableName = tailtableName;
    }

    public String getTailtableNameChinese() {
        return this.tailtableNameChinese;
    }

    public void setTailtableNameChinese(String tailtableNameChinese) {
        this.tailtableNameChinese = tailtableNameChinese;
    }

    public String getColspan() {
        return this.colspan;
    }

    public void setColspan(String colspan) {
        this.colspan = colspan;
    }

    public String getRowspan() {
        return this.rowspan;
    }

    public void setRowspan(String rowspan) {
        this.rowspan = rowspan;
    }

    public String getQuerymode() {
        return this.querymode;
    }

    public void setQuerymode(String querymode) {
        this.querymode = querymode;
    }

    public String getUrlid() {
        return this.urlid;
    }

    public void setUrlid(String urlid) {
        this.urlid = urlid;
    }

    public Date getCreatetime() {
        return this.createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public String getCreateperson() {
        return this.createperson;
    }

    public void setCreateperson(String createperson) {
        this.createperson = createperson;
    }

    public String getReserveA() {
        return this.reserveA;
    }

    public void setReserveA(String reserveA) {
        this.reserveA = reserveA;
    }

    public String getReserveB() {
        return this.reserveB;
    }

    public void setReserveB(String reserveB) {
        this.reserveB = reserveB;
    }

    public String getReserveC() {
        return this.reserveC;
    }

    public void setReserveC(String reserveC) {
        this.reserveC = reserveC;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof ReportMng) ) return false;
        ReportMng castOther = (ReportMng) other;
        return new EqualsBuilder()
            .append(this.getId(), castOther.getId())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getId())
            .toHashCode();
    }

}
