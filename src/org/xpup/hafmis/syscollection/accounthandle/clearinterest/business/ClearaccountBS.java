package org.xpup.hafmis.syscollection.accounthandle.clearinterest.business;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.common.util.BusiConst;
import org.xpup.hafmis.common.util.BusiTools;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.accounthandle.clearaccount.dto.ClearAccountDTO;
import org.xpup.hafmis.syscollection.accounthandle.clearinterest.bsinterface.IClearaccountBS;
import org.xpup.hafmis.syscollection.common.dao.ChgPersonHeadDAO;
import org.xpup.hafmis.syscollection.common.dao.DocNumMaintainDAO;
import org.xpup.hafmis.syscollection.common.dao.OrgDAO;
import org.xpup.hafmis.syscollection.common.dao.OrgHAFAccountFlowDAO;
import org.xpup.hafmis.syscollection.common.dao.SettInterestHeadDAO;
import org.xpup.hafmis.syscollection.common.dao.SettInterestResultDAO;
import org.xpup.hafmis.syscollection.common.daoDW.SettInterestHeadDAODW;
import org.xpup.hafmis.syscollection.common.daoDW.SettInterestResultDAODW;
import org.xpup.hafmis.syscollection.common.domain.entity.SettInterestHead;
import org.xpup.hafmis.syscollection.common.domain.entity.SettInterestHeadNZJX;
import org.xpup.hafmis.syscollection.common.domain.entity.SettInterestHeadXHTQ;
import org.xpup.hafmis.syscollection.common.domain.entity.SettInterestHeadZY;
import org.xpup.hafmis.syscollection.common.domain.entity.SettInterestResult;

public class ClearaccountBS implements IClearaccountBS {

  private OrgDAO orgDAO = null;

  private DocNumMaintainDAO docNumMaintainDAO = null;
  
  private ChgPersonHeadDAO chgPersonHeadDAO = null;
  
  private OrgHAFAccountFlowDAO orgHAFAccountFlowDAO = null;
  
  // 单位版_修改记录：wangy 2008-02-28
  private SettInterestHeadDAO settInterestHeadDAO = null;
  
  private SettInterestResultDAO settInterestResultDAO = null;
  
  private SettInterestHeadDAODW settInterestHeadDAODW = null;
  
  private SettInterestResultDAODW settInterestResultDAODW = null;

  public void setSettInterestHeadDAO(SettInterestHeadDAO settInterestHeadDAO) {
    this.settInterestHeadDAO = settInterestHeadDAO;
  }

  public void setSettInterestHeadDAODW(SettInterestHeadDAODW settInterestHeadDAODW) {
    this.settInterestHeadDAODW = settInterestHeadDAODW;
  }

  public void setSettInterestResultDAO(SettInterestResultDAO settInterestResultDAO) {
    this.settInterestResultDAO = settInterestResultDAO;
  }

  public void setSettInterestResultDAODW(
      SettInterestResultDAODW settInterestResultDAODW) {
    this.settInterestResultDAODW = settInterestResultDAODW;
  }// 单位版_修改记录：修改结束

  public void setOrgDAO(OrgDAO orgDAO) {
    this.orgDAO = orgDAO;
  }

  public void setDocNumMaintainDAO(DocNumMaintainDAO docNumMaintainDAO) {
    this.docNumMaintainDAO = docNumMaintainDAO;
  }

  public void setChgPersonHeadDAO(ChgPersonHeadDAO chgPersonHeadDAO) {
    this.chgPersonHeadDAO = chgPersonHeadDAO;
  }

  public void setOrgHAFAccountFlowDAO(OrgHAFAccountFlowDAO orgHAFAccountFlowDAO) {
    this.orgHAFAccountFlowDAO = orgHAFAccountFlowDAO;
  }

  /**
   * 根据条件查询可以办理结息的单位列表
   * 
   * @param pagination
   * @return
   * @throws BusinessException
   */
  public List findClearaccountListByCriterions(Pagination pagination,SecurityInfo securityInfo)
      throws Exception, BusinessException {
    List list = null;
    BusinessException be = null;
    try {
      list = new ArrayList();

      String officecode = (String) pagination.getQueryCriterions().get(
          "officecode");
      String bankcode = (String) pagination.getQueryCriterions()
          .get("bankcode");
      String orgcode = (String) pagination.getQueryCriterions().get("orgcode");
      if(orgcode!=null&&orgcode!=""){
        if(orgcode.length()==10){
        orgcode=orgcode.substring(1,orgcode.length());
        }
      }
      String orgname = (String) pagination.getQueryCriterions().get("orgname");
      String oper = (String) pagination.getQueryCriterions().get("oper");
      String accountantyear = (String) pagination.getQueryCriterions().get(
          "accountantyear");// 会计年
      String orderBy = (String) pagination.getOrderBy();
      String order = (String) pagination.getOrderother();
      int start = pagination.getFirstElementOnPage() - 1;
      int pageSize = pagination.getPageSize();
      int page = pagination.getPage();

      list = orgDAO.findClearaccountListByCriterions_WL(officecode, bankcode,
          orgcode, orgname, oper, accountantyear, orderBy, order, start,
          pageSize,page,securityInfo);

      int count = orgDAO.findClearaccountCountByCriterions_WL(officecode,
          bankcode, orgcode, orgname, oper, accountantyear,securityInfo);
      pagination.setNrOfElements(count);

    } catch (Exception e) {
      e.printStackTrace();
      throw be;
    }
    return list;
  }
  /**
   * 根据条件查询全部可以办理结息的单位列表
   * 
   * @param pagination
   * @return
   * @throws BusinessException
   */
  public List findClearaccountAllListByCriterions(Pagination pagination,SecurityInfo securityInfo)
      throws Exception, BusinessException {
    List list = null;
    BusinessException be = null;
    try {
      list = new ArrayList();

      String officecode = (String) pagination.getQueryCriterions().get(
          "officecode");
      String bankcode = (String) pagination.getQueryCriterions()
          .get("bankcode");
      String orgcode = (String) pagination.getQueryCriterions().get("orgcode");
      if(orgcode!=null&&orgcode!=""){
        if(orgcode.length()==10){
        orgcode=orgcode.substring(1,orgcode.length());
        }
      }
      String orgname = (String) pagination.getQueryCriterions().get("orgname");
      String oper = (String) pagination.getQueryCriterions().get("oper");
      String accountantyear = (String) pagination.getQueryCriterions().get(
          "accountantyear");// 会计年

      list = orgDAO.findClearaccountAllListByCriterions_WL(officecode, bankcode,
          orgcode, orgname, oper, accountantyear,securityInfo);

      int count = orgDAO.findClearaccountCountByCriterions_WL(officecode,
          bankcode, orgcode, orgname, oper, accountantyear,securityInfo);
      pagination.setNrOfElements(count);

    } catch (Exception e) {
      e.printStackTrace();
      throw be;
    }
    return list;
  }
  

  /**
   * 年终结息
   */
  public String clearacount(String[] rowArray,String officecode,String bizDate,String ip,String oper, SecurityInfo securityInfo,String accountflag) throws BusinessException {

    String flag = "";
    try {
      boolean voild = false;
      String[] clearrowArray=new String[rowArray.length];
      int arrid=0;
      
      // 业务日期
      String bizYearmonth = bizDate.substring(0,6);
      
      //-----------------------------参数
      //会计年
      String busiDate=bizDate.substring(0, 4);
      //会计日期
      String busiDateTime=bizDate;
      //操作时间
      String op_time=BusiTools.dateToString(new Date(), "yyyy-MM-dd HH:mm:ss");
     
      //是否有提取、转出、转入、错帐调整录入清册的业务，如果有，不允许结息
      for(int i=0;i<rowArray.length;i++){
        String orgid=rowArray[i];
        String returnflag=orgHAFAccountFlowDAO.queryBizstatus1_WL(orgid);
        if(returnflag.equals("D")||returnflag.equals("E")||returnflag.equals("F")||returnflag.equals("G")){
          throw new BusinessException("单位编号："+orgid+"的单位存在未完成的业务！！");
        }
      }
      
      
      //用办事处进行分组年终结息
      List arrlist = orgHAFAccountFlowDAO.getrowArrayByOffice1_WL(rowArray);
      ClearAccountDTO clearAccountDTO=null;
      for(int w=0;w<arrlist.size();w++){
        clearAccountDTO=(ClearAccountDTO)arrlist.get(w);
        String returnofficeid="";
        if(returnofficeid.equals("")&&w!=arrlist.size()-1){
          returnofficeid=clearAccountDTO.getOffice();
          clearrowArray[arrid]=clearAccountDTO.getFlowid();
          arrid++;
        }else if(returnofficeid.equals(clearAccountDTO.getOffice())&&w!=arrlist.size()-1){
          clearrowArray[arrid]=clearAccountDTO.getFlowid();
          arrid++;
        }else if((returnofficeid.equals("")&&w==arrlist.size()-1)||(returnofficeid.equals(clearAccountDTO.getOffice())&&w==arrlist.size()-1)||(!returnofficeid.equals(clearAccountDTO.getOffice()))){
          if((returnofficeid.equals("")&&w==arrlist.size()-1)||(returnofficeid.equals(clearAccountDTO.getOffice())&&w==arrlist.size()-1)){
            returnofficeid=clearAccountDTO.getOffice();
            clearrowArray[arrid]=clearAccountDTO.getFlowid();
            arrid++;
          }
          String[] temp_arr=new String[arrid];
          for(int q=0;q<arrid;q++){
            temp_arr[q]=clearrowArray[q];
          }

          // 生成业务凭证号
          String docNum = null;
          docNum = docNumMaintainDAO.getDocNumdocNum(clearAccountDTO.getOffice(), bizYearmonth);
          Map office = securityInfo.getOfficeInnerCodeMap();
          String officecodes = office.get(clearAccountDTO.getOffice()).toString();
          if (officecodes.length() == 1) {
            officecodes = "0" + officecodes;
          }
          docNum=officecodes+bizYearmonth+docNum;
          //单位字符串
          String orgStr="";
          List newlist=new ArrayList();
          for(int i=0;i<temp_arr.length;i++){
            orgStr+=temp_arr[i]+";";
            newlist.add(temp_arr[i]);
          }
          orgStr.substring(0, orgStr.lastIndexOf(";"));
          if(accountflag.equals("1")){//全部结息
            voild =chgPersonHeadDAO.doClearAllAccount(newlist, busiDate,busiDateTime, docNum, ip, op_time, oper);
          }else{//非全部年结
            voild =chgPersonHeadDAO.doClearAccount(orgStr, busiDate,busiDateTime, docNum, ip, op_time, oper);
          }

          // 单位版_修改记录：wangy 2008-02-28
          // 获得版本_中心版
          int isOrgEdition = securityInfo.getIsOrgEdition();
          for(int i=0;i<temp_arr.length;i++){
            if (isOrgEdition == BusiConst.ORG_OR_CENTER_INFO_CENTER) {// 中心版
              Serializable aa316ID = null;// 单位版AA316.ID
              // 插入单位版AA316
              List settInterestHeadList = settInterestHeadDAO.querySettInterestHeadByOrgId(temp_arr[i], busiDate);// 查询条件加类型'A'
              if (!settInterestHeadList.isEmpty()) {
                for (Iterator iter = settInterestHeadList.iterator(); iter.hasNext();) {
                  SettInterestHead settInterestHead = (SettInterestHead) iter.next();
                  if (settInterestHead != null) {
                    SettInterestHead settInterestHeadDW = null;
                    if (BusiConst.CLEARINTERESTTYPE_YEARCLEAR.equals(settInterestHead.getInterestType())) {// 此处只能是A
                      settInterestHeadDW = new SettInterestHeadNZJX();
                    }
                    if (BusiConst.CLEARINTERESTTYPE_DELACCOUNT.equals(settInterestHead.getInterestType())) {// B
                      settInterestHeadDW = new SettInterestHeadXHTQ();
                    }
                    if (BusiConst.CLEARINTERESTTYPE_TRANS.equals(settInterestHead.getInterestType())) {// C
                      settInterestHeadDW = new SettInterestHeadZY();
                    }
                    settInterestHeadDW.setOrg(settInterestHead.getOrg());
                    settInterestHeadDW.setBizId(settInterestHead.getBizId());
                    settInterestHeadDW.setDocNum(settInterestHead.getDocNum());
                    settInterestHeadDW.setYear(settInterestHead.getYear());
                    settInterestHeadDW.setType(settInterestHead.getInterestType());
                    settInterestHeadDW.setReserveaA(settInterestHead.getReserveaA());
                    settInterestHeadDW.setReserveaB(settInterestHead.getReserveaB());
                    settInterestHeadDW.setReserveaC(settInterestHead.getReserveaC());
                    aa316ID = settInterestHeadDAODW.insert(settInterestHeadDW);
                  }
                }
              }
              // 插入单位版AA318
              List settInterestResultList = settInterestResultDAO.querySettInterestResultListBySettHeadId(temp_arr[i], busiDate);
              if (!settInterestResultList.isEmpty()) {
                for (Iterator iter = settInterestResultList.iterator(); iter.hasNext();) {
                  SettInterestResult settInterestResult = (SettInterestResult) iter.next();
                  SettInterestResult settInterestResultDW = new SettInterestResult();
                  settInterestResultDW.setYear(settInterestResult.getYear());
                  settInterestResultDW.setEmpId(settInterestResult.getEmpId());
                  settInterestResultDW.setBefPreBalance(settInterestResult.getBefPreBalance());
                  settInterestResultDW.setBefCurBalance(settInterestResult.getBefCurBalance());
                  settInterestResultDW.setPreInterest(settInterestResult.getPreInterest());
                  settInterestResultDW.setCurInterest(settInterestResult.getCurInterest());
                  settInterestResultDW.setEndPreBalance(settInterestResult.getEndPreBalance());
                  settInterestResultDW.setEndCurBalance(settInterestResult.getEndCurBalance());
                  settInterestResultDW.setReserveaA(settInterestResult.getReserveaA());
                  settInterestResultDW.setReserveaB(settInterestResult.getReserveaB());
                  settInterestResultDW.setReserveaC(settInterestResult.getReserveaC());
                  settInterestResultDW.setSettHeadId(new Integer(aa316ID.toString()));// 插入单位版AA316.ID
                  settInterestResultDAODW.insert(settInterestResultDW);
                }
              }
            }
          }// 单位版_修改记录：修改结束
          
          if (voild == false)
            flag = "2";
          if(!flag.equals("2")){
            throw new BusinessException("年终结息失败！！");
          }

          if(!returnofficeid.equals(clearAccountDTO.getOffice())){
            w--;
          }
          clearrowArray=new String[rowArray.length];
          arrid=0; returnofficeid=""; 
        }else{
          System.out.println("----------");
        }
      }
      
    } catch (BusinessException be) {
      throw be;
    }catch(Exception e){
      e.printStackTrace();
    }
    return flag;
  }

}
