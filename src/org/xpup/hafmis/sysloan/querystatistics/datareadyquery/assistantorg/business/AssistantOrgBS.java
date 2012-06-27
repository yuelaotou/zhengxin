package org.xpup.hafmis.sysloan.querystatistics.datareadyquery.assistantorg.business;

import java.util.ArrayList;
import java.util.List;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.common.util.BusiConst;
import org.xpup.hafmis.common.util.BusiTools;
import org.xpup.hafmis.orgstrct.dao.SecurityDAO;
import org.xpup.hafmis.sysloan.common.dao.AssistantOrgDAO;
import org.xpup.hafmis.sysloan.querystatistics.datareadyquery.assistantorg.bsinterface.IAssistantOrgBS;
import org.xpup.hafmis.sysloan.querystatistics.datareadyquery.assistantorg.dto.AssistantorgQueryTbDTO;

public class AssistantOrgBS implements IAssistantOrgBS {
  private AssistantOrgDAO assistantOrgDAO = null;
  private SecurityDAO securityDAO=null;

  /**
   * 统计查询_代理机构查询
   * 
   * @param pagination
   * @author wsh 2007-10-03 查询列表信息
   */
  public List findAssistantOrgList(Pagination pagination) throws Exception {
    List list = null;
    List countlist = null;
    String assistantOrgName = "";
    String artfclprsn = "";
    String assistantType = "";
    String inArea = "";
    try {
      if (pagination.getQueryCriterions().get("assistantOrgName") != null) {
        assistantOrgName = (String) pagination.getQueryCriterions().get(
            "assistantOrgName");
      }
      if (pagination.getQueryCriterions().get("artfclprsn") != null) {
        artfclprsn = (String) pagination.getQueryCriterions().get("artfclprsn");
      }
      if (pagination.getQueryCriterions().get("assistantType") != null) {
        assistantType = (String) pagination.getQueryCriterions().get(
            "assistantType");
      }
      if (pagination.getQueryCriterions().get("inArea") != null) {
        inArea = (String) pagination.getQueryCriterions().get("inArea");
      }
      String orderBy = (String) pagination.getOrderBy();
      String order = (String) pagination.getOrderother();
      int start = pagination.getFirstElementOnPage() - 1;
      int pageSize = pagination.getPageSize();
      list = assistantOrgDAO.queryAssistantOrgList_wsh(assistantOrgName,
          artfclprsn, assistantType, inArea, orderBy, order, start, pageSize);
      countlist = assistantOrgDAO.queryAssistantOrgListCount_wsh(
          assistantOrgName, artfclprsn, assistantType, inArea);
      if (countlist.size() != 0) {
        int count = countlist.size();
        pagination.setNrOfElements(count);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }

  /**
   * 统计查询_代理机构查询
   * 
   * @param id
   * @author wsh 2007-10-03 查询列表信息
   */
  public AssistantorgQueryTbDTO findAssistantOrgInfo(String id)
      throws Exception {
    // TODO Auto-generated method stub
    AssistantorgQueryTbDTO assistantorgQueryTbDTO = new AssistantorgQueryTbDTO();
    try {
      assistantorgQueryTbDTO = (AssistantorgQueryTbDTO) assistantOrgDAO
          .findAssistantOrgInfo_wsh(id).get(0);
      if( assistantorgQueryTbDTO.getArtfclprsnCardKind()!= null&&!"".equals(assistantorgQueryTbDTO.getArtfclprsnCardKind().trim())){
        assistantorgQueryTbDTO.setArtfclprsnCardKind(BusiTools.getBusiValue(Integer
            .parseInt(assistantorgQueryTbDTO.getArtfclprsnCardKind().toString()),
            BusiConst.DOCUMENTSSTATE));
      }          
    } catch (Exception e) {
      // TODO: handle exception
      e.printStackTrace();
    }
    return assistantorgQueryTbDTO;
  }

  /**
   * 代理机构查询-列表打印 author wsh
   * 
   * @param assistantOrgName
   * @param artfclprsn
   * @param assistantType
   * @return
   */
  public List queryAssistantOrgListCount_wsh(String assistantOrgName,
      String artfclprsn, String assistantType, String inArea) throws Exception {
    // TODO Auto-generated method stub
    List cellList = new ArrayList();
    try {
      cellList = assistantOrgDAO.queryAssistantOrgListCount_wsh(
          assistantOrgName, artfclprsn, assistantType, inArea);
    } catch (Exception e) {
      // TODO: handle exception
    }
    return cellList;
  }
  /*获取真实姓名
   * (non-Javadoc)
   * @see org.xpup.hafmis.sysloan.loanapply.issuenotice.bsinterface.IIssuenoticeBS#getUserRealName(java.lang.String)
   */
    public String getUserRealName(String name) throws Exception {
      // TODO Auto-generated method stub
      String realName="";
      try {
       
        realName=securityDAO.queryByUserid(name);
      } catch (Exception e) {
        // TODO: handle exception
        e.printStackTrace();
      }
      return realName;
    }
  public void setAssistantOrgDAO(AssistantOrgDAO assistantOrgDAO) {
    this.assistantOrgDAO = assistantOrgDAO;
  }

  public void setSecurityDAO(SecurityDAO securityDAO) {
    this.securityDAO = securityDAO;
  }
}
