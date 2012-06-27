package org.xpup.hafmis.syscollection.accountmng.officeparam.business;


import java.util.ArrayList;
import java.util.List;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.accountmng.officeparam.bsinterface.IOfficeParaBS;
import org.xpup.hafmis.syscollection.accountmng.officeparam.dto.OfficeParaDTO;
import org.xpup.hafmis.syscollection.common.dao.OfficeParaDAO;
import org.xpup.hafmis.syscollection.common.domain.entity.OfficePara;

public class OfficeParaBS implements IOfficeParaBS{
 
 
  
  
  private OfficeParaDAO officeParaDAO=null;
  /**
   * 办事处银行修改参数设置
   * @author 王硕
   * 2007-01-21
   * 根据办事处查pl009表内容
   * 查询条件：office
   */
  public OfficeParaDTO findOfficeParaInfo(String office) throws Exception{
    // TODO Auto-generated method stub
    OfficeParaDTO officeParaDTO=new OfficeParaDTO();
    List list=new ArrayList();
    try{
      OfficePara officePara=null;
      list=officeParaDAO.queryOfficeParaByOffice(office);
      if(list.size()!=0){
        officePara=(OfficePara)officeParaDAO.queryOfficeParaByOffice(office).get(0);
        officeParaDTO.setIsBankModify(officePara.getParamValue());
        officeParaDTO.setOffice(officePara.getOffice());
      }
    }catch(Exception e){
      e.printStackTrace();
    }
    return officeParaDTO;
  }
  /**
   * 办事处银行修改参数设置
   * @author 郭婧平
   * 2007-12-17
   * 确定
   */
  public void saveCollLoanbackParaInfo(OfficeParaDTO officeParaDTO,SecurityInfo securityInfo) throws Exception{
    try{
      String office=officeParaDTO.getOffice();
      //删除aa412
      officeParaDAO.deleteOfficePara(office);
      //插入aa412
      OfficePara officePara=null;
      officePara=new OfficePara();
      officePara.setOffice(office);
      officePara.setParamNum("1");
      officePara.setParamDescrip("是否可以修改存款银行；0.可以修改； 1.不可以修改；");
      officePara.setParamValue(officeParaDTO.getIsBankModify());
      
      officeParaDAO.insert(officePara);
     
//      HafOperateLog hafOperateLog = new HafOperateLog();
//      // 插入日志BA003
//      hafOperateLog.setOpSys(new Integer(BusiLogConst.OP_SYSTEM_TYPE_COLLECTION));
//      hafOperateLog.setOpModel("" + BusiLogConst.OP_MODE_OPEN_ORGOPEN_DO);
//      hafOperateLog.setOpButton("" + BusiLogConst.BIZLOG_ACTION_ADD);
//      hafOperateLog.setOpBizId(new Integer(iid.toString()));
//      hafOperateLog.setOpIp(securityInfo.getUserIp());
//      hafOperateLog.setOrgId(new Integer(iid.toString()));
//      hafOperateLog.setOpTime(new Date());
//      hafOperateLog.setOperator(securityInfo.getUserName());
//      hafOperateLogDAO.insert(hafOperateLog);
    }catch(Exception e){
      e.printStackTrace();
    }
  }


  public void setOfficeParaDAO(OfficeParaDAO officeParaDAO) {
    this.officeParaDAO = officeParaDAO;
  }

}
