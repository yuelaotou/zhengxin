package org.xpup.hafmis.syscollection.accountmng.accountopen.dto;

import org.xpup.common.util.exp.domn.interfaces.ExpDto;


public class EmpOpenExpContentDTO implements ExpDto{

  
  /**
  职工姓名、证件号码、证件类型,所在部门,电话,移动电话,月工资收入,工资基数、单位缴额、职工缴额、
   */
  
   private String  empname="";
   private String  cardnum="";
   private String cardkind="";
   private String dept="";
   private String tel="";
   private String mobileTle="";
   private String monthIncome="";
   private String salarybase="";
   private String orgpay="";
   private String emppay="";
   
   private String sex="";
   private String birthday="";

  public String getBirthday() {
    return birthday;
  }
  public void setBirthday(String birthday) {
    this.birthday = birthday;
  }
  public String getSex() {
    return sex;
  }
  public void setSex(String sex) {
    this.sex = sex;
  }
  /**
   * @return 返回 empname。
   */
  public String getEmpname() {
    return empname;
  }
  /**
   * @param empname 要设置的 empname。
   */
  public void setEmpname(String empname) {
    this.empname = empname;
  }


   
    public String getInfo(String info) {
      // TODO 自动生成方法存根
      
      if(info.equals("empname")){
        return this.empname;
        
      }
      
      if(info.equals("salarybase")){
        return this.salarybase;
        
      }

      if(info.equals("cardnum")){
        return this.cardnum;
        
      }

      if(info.equals("orgpay")){
        return this.orgpay;
        
      }
      
      if(info.equals("emppay")){
        return this.emppay;
        
      }
      
      if(info.equals("cardkind")){
        return this.cardkind;
        
      }

      if(info.equals("dept")){
        return this.dept;
        
      }

      if(info.equals("tel")){
        return this.tel;
        
      }
      
      if(info.equals("mobileTle")){
        return this.mobileTle;
        
      }
      if(info.equals("monthIncome")){
        return this.monthIncome;
        
      }
      return null;
    }


    public String getCardnum() {
      return cardnum;
    }
    public void setCardnum(String cardnum) {
      this.cardnum = cardnum;
    }

    public String getEmppay() {
      return emppay;
    }
    public void setEmppay(String emppay) {
      this.emppay = emppay;
    }
    public String getOrgpay() {
      return orgpay;
    }
    public void setOrgpay(String orgpay) {
      this.orgpay = orgpay;
    }

    public String getSalarybase() {
      return salarybase;
    }
    public void setSalarybase(String salarybase) {
      this.salarybase = salarybase;
    }
    public String getCardkind() {
      return cardkind;
    }
    public void setCardkind(String cardkind) {
      this.cardkind = cardkind;
    }
    public String getDept() {
      return dept;
    }
    public void setDept(String dept) {
      this.dept = dept;
    }
    public String getMobileTle() {
      return mobileTle;
    }
    public void setMobileTle(String mobileTle) {
      this.mobileTle = mobileTle;
    }
    public String getMonthIncome() {
      return monthIncome;
    }
    public void setMonthIncome(String monthIncome) {
      this.monthIncome = monthIncome;
    }
    public String getTel() {
      return tel;
    }
    public void setTel(String tel) {
      this.tel = tel;
    }


}
