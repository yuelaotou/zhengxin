package org.xpup.hafmis.sysloan.dataready.assistantorg.business;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.xpup.common.util.Pagination;
import org.xpup.hafmis.common.util.BusiConst;
import org.xpup.hafmis.common.util.BusiTools;
import org.xpup.hafmis.common.util.bizlog.BusiLogConst;
import org.xpup.hafmis.orgstrct.dao.OrganizationUnitDAO;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysloan.common.dao.AssistantOrgDAO;
import org.xpup.hafmis.sysloan.common.dao.PlOperateLogDAO;
import org.xpup.hafmis.sysloan.common.domain.entity.AssistantOrg;
import org.xpup.hafmis.sysloan.common.domain.entity.PlOperateLog;
import org.xpup.hafmis.sysloan.dataready.assistantorg.bsinterface.IEvaluateBS;
import org.xpup.hafmis.sysloan.dataready.assistantorg.dto.EvaluateAFDTO;
import org.xpup.hafmis.sysloan.dataready.assistantorg.dto.EvaluateDTO;

public class EvaluateBS implements IEvaluateBS{
  private AssistantOrgDAO assistantOrgDAO=null; //pl007
  private OrganizationUnitDAO organizationUnitDAO=null;
  private PlOperateLogDAO plOperateLogDAO=null;
  
  /**
   * name 杨蒙
   * 评估机构维护-显示列表
   */
  public List findEvaluateList(Pagination pagination)
  {
    List list=null;
    List listAF=new ArrayList();
    try{
      String orderBy = (String) pagination.getOrderBy();
      String order = (String) pagination.getOrderother();
      int start = pagination.getFirstElementOnPage() - 1;
      int pageSize = pagination.getPageSize();
      int page=pagination.getPage();
      list=assistantOrgDAO.findEvaluate_YM(orderBy, order, start, pageSize,page);
      if(list.size()!=0||list!=null)
      {
        for(int i=0;i<list.size();i++)
        {
          EvaluateAFDTO aAF=new EvaluateAFDTO(); //创建 AF
          EvaluateDTO ao=(EvaluateDTO)list.get(i);  //DAO 返回实体list
          aAF.setAssistantOrgName(ao.getAssistantOrgName());  //单位名称
          aAF.setAssistantOrgAddr(ao.getAssistantOrgAddr());  //单位地址

          //张列修改 （头）
          if(ao.getArear() == null || ao.getArear().equals("")){
            aAF.setArear(ao.getArear());
          }else{
            aAF.setArear(BusiTools.getBusiValue(Integer
                .parseInt(ao.getArear()), BusiConst.INAREA));  //所属地区
          }
          //张列改（尾）
          
          aAF.setPaybank(ao.getPaybank());  //开户银行
          aAF.setContactPrsn(ao.getContactPrsn());//联系人
          aAF.setContactTel(ao.getContactTel());  //联系电话
          aAF.setAssistantOrgId(ao.getId().toString()); //协作单位编号
          aAF.setRegisterFund(new BigDecimal(ao.getRegisterFund()));//注册资金
          listAF.add(aAF);
         }
      }
    
    int count = assistantOrgDAO.findEvaluateTypeCountA_YM();
    pagination.setNrOfElements(count);
    }catch(Exception e)
    {
      e.printStackTrace();
    }
    return listAF;
  }

  /**
   * name 杨蒙
   * 评估公司维护 插入新记录   (**张列改**)
   */
    public void insertEvaluateList(EvaluateAFDTO evaluateAFDTO,SecurityInfo securityInfo)
    {
      try {
        AssistantOrg aog=new AssistantOrg(); //创建新实体
        aog.setAssistantOrgName(evaluateAFDTO.getAssistantOrgName().trim());//担保公司名称
        aog.setArtfclprsn(evaluateAFDTO.getArtfclprsn().trim());//法人代表
        aog.setCode(evaluateAFDTO.getCode().trim());//组织机构
        aog.setAssistantOrgAddr(evaluateAFDTO.getAssistantOrgAddr().trim());//担保公司地址
        aog.setBasedDate(evaluateAFDTO.getBasedDate().trim());//成立日期
        aog.setArtfclprsnCardKind(evaluateAFDTO.getArtfclprsnCardKind().trim());//法人证件类型
        aog.setArtfclprsnCardNum(evaluateAFDTO.getArtfclprsnCardNum().trim());//法人证件号码
        aog.setAllowDept(evaluateAFDTO.getAllowDept().trim());//批准机关
        aog.setAllowId(evaluateAFDTO.getAllowId().trim());//批准文号
        aog.setAgreementStartDate(evaluateAFDTO.getAgreementStartDate().trim());//协议签订日期
        aog.setAgreementEndDate(evaluateAFDTO.getAgreementEndDate().trim());//协议到期日期
        aog.setPaybank(evaluateAFDTO.getPaybank().trim());//开户银行
        aog.setPayacc(evaluateAFDTO.getPayacc().trim());//开户行帐号
        aog.setContactPrsn(evaluateAFDTO.getContactPrsn().trim());//联系人
        aog.setContactTel(evaluateAFDTO.getContactTel().trim());//联系电话
        aog.setArear(evaluateAFDTO.getArear().trim());//所属地区
        aog.setBusiness(evaluateAFDTO.getBusiness());//职务
        aog.setRegisterFund(evaluateAFDTO.getRegisterFund());//注册资金
        aog.setRemark(evaluateAFDTO.getRemark());//备注
        aog.setAssistantOrgType("D");
        String id=assistantOrgDAO.insert(aog).toString();
        PlOperateLog plOperateLog=new PlOperateLog();//创建日志对象     
        plOperateLog.setOpSys(new BigDecimal(BusiLogConst.OP_SYSTEM_TYPE_LOAN));//个贷系统
        plOperateLog.setOpModel(String.valueOf(BusiLogConst.PL_OP_DATAPREPARATION_ASSESSMENTAGEN));//代理机构维护
        plOperateLog.setOpButton("1");
        plOperateLog.setOpBizId(new BigDecimal(id)); 
        plOperateLog.setOpIp(securityInfo.getUserIp());
        plOperateLog.setOpTime(new Date());
        plOperateLog.setOperator(securityInfo.getUserName());
        plOperateLogDAO.insert(plOperateLog);
      } catch (RuntimeException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }

    }
  
    /**
     * name 杨蒙
     * 评估公司维护 通过id查询数据用于修改
     */
    public EvaluateAFDTO findEvaluateID(Integer id)
    {
      EvaluateAFDTO AF=new EvaluateAFDTO();
      try{
        AssistantOrg aog=assistantOrgDAO.queryById(id);
        AF.setAssistantOrgId(aog.getAssistantOrgId().toString());
        if(aog.getAssistantOrgName()!=null)
          AF.setAssistantOrgName(aog.getAssistantOrgName());//担保公司名称
        if(aog.getArtfclprsn()!=null)
          AF.setArtfclprsn(aog.getArtfclprsn());//法人代表
        if(aog.getCode()!=null)
          AF.setCode(aog.getCode());//组织机构
        if(aog.getAssistantOrgAddr()!=null)
          AF.setAssistantOrgAddr(aog.getAssistantOrgAddr());//担保公司地址
        if(aog.getBasedDate()!=null)
          AF.setBasedDate(aog.getBasedDate());//成立日期
        if(aog.getArtfclprsnCardKind()!=null)
          AF.setArtfclprsnCardKind(aog.getArtfclprsnCardKind());//法人证件类型
        if(aog.getArtfclprsnCardNum()!=null)
          AF.setArtfclprsnCardNum(aog.getArtfclprsnCardNum());//法人证件号码
        if(aog.getAllowDept()!=null)
          AF.setAllowDept(aog.getAllowDept());//批准机关
        if(aog.getAllowId()!=null)
          AF.setAllowId(aog.getAllowId());//批准文号
        if(aog.getAgreementStartDate()!=null)
          AF.setAgreementStartDate(aog.getAgreementStartDate());//协议签订日期
        if(aog.getAgreementEndDate()!=null)
          AF.setAgreementEndDate(aog.getAgreementEndDate());//协议到期日期
        if(aog.getPaybank()!=null)
          AF.setPaybank(aog.getPaybank());//开户银行
        if(aog.getPayacc()!=null)
          AF.setPayacc(aog.getPayacc());//开户行帐号
        if(aog.getContactPrsn()!=null)
          AF.setContactPrsn(aog.getContactPrsn());//联系人
        if(aog.getContactTel()!=null)
          AF.setContactTel(aog.getContactTel());//联系电话
        if(aog.getArear()!=null)
          AF.setArear(aog.getArear());//所属地区
        if(aog.getBusiness()!=null)
          AF.setBusiness(aog.getBusiness());//职务
        if(aog.getRegisterFund()!=null)
          AF.setRegisterFund(aog.getRegisterFund());//注册资金
        if(aog.getRemark()!=null)
          AF.setRemark(aog.getRemark());//备注
        if(aog.getAssistantOrgType()!=null)
          AF.setAssistantOrgType(aog.getAssistantOrgType());

      }catch(Exception e)
      {
        e.printStackTrace();
      }
      return AF;
    }
    /**
     * name 杨蒙
     * 评估公司维护,修改数据   (**张列改**)
     */
    public void updateEvaluate(EvaluateAFDTO evaluateAFDTO,SecurityInfo securityInfo)
    {
      AssistantOrg aog=null;
      try{
        aog=assistantOrgDAO.queryById(new Integer(evaluateAFDTO.getAssistantOrgId()));
        aog.setAssistantOrgName(evaluateAFDTO.getAssistantOrgName().trim());//担保公司名称
        aog.setArtfclprsn(evaluateAFDTO.getArtfclprsn().trim());//法人代表
        aog.setCode(evaluateAFDTO.getCode().trim());//组织机构
        aog.setAssistantOrgAddr(evaluateAFDTO.getAssistantOrgAddr().trim());//担保公司地址
        aog.setBasedDate(evaluateAFDTO.getBasedDate().trim());//成立日期
        aog.setArtfclprsnCardKind(evaluateAFDTO.getArtfclprsnCardKind().trim());//法人证件类型
        aog.setArtfclprsnCardNum(evaluateAFDTO.getArtfclprsnCardNum().trim());//法人证件号码
        aog.setAllowDept(evaluateAFDTO.getAllowDept().trim());//批准机关
        aog.setAllowId(evaluateAFDTO.getAllowId().trim());//批准文号
        aog.setAgreementStartDate(evaluateAFDTO.getAgreementStartDate().trim());//协议签订日期
        aog.setAgreementEndDate(evaluateAFDTO.getAgreementEndDate().trim());//协议到期日期
        aog.setPaybank(evaluateAFDTO.getPaybank().trim());//开户银行
        aog.setPayacc(evaluateAFDTO.getPayacc().trim());//开户行帐号
        aog.setContactPrsn(evaluateAFDTO.getContactPrsn().trim());//联系人
        aog.setContactTel(evaluateAFDTO.getContactTel().trim());//联系电话
        aog.setArear(evaluateAFDTO.getArear().trim());//所属地区
        aog.setBusiness(evaluateAFDTO.getBusiness());//职务
        aog.setRegisterFund(evaluateAFDTO.getRegisterFund());//注册资金
        aog.setRemark(evaluateAFDTO.getRemark());//备注
        aog.setAssistantOrgType(evaluateAFDTO.getAssistantOrgType());
        String id=aog.getAssistantOrgId().toString();
        PlOperateLog plOperateLog=new PlOperateLog();//创建日志对象     
        plOperateLog.setOpSys(new BigDecimal(BusiLogConst.OP_SYSTEM_TYPE_LOAN));//个贷系统
        plOperateLog.setOpModel(String.valueOf(BusiLogConst.PL_OP_DATAPREPARATION_ASSESSMENTAGEN));//代理机构
        plOperateLog.setOpButton("2");
        plOperateLog.setOpBizId(new BigDecimal(id)); 
        plOperateLog.setOpIp(securityInfo.getUserIp());
        plOperateLog.setOpTime(new Date());
        plOperateLog.setOperator(securityInfo.getUserName());
        plOperateLogDAO.insert(plOperateLog);
      }catch(Exception e)
      {
        e.printStackTrace();
      }
    }
  
    /**
     * name 杨蒙
     * 根据id删除PL007数据
     */
    public String deleteEvaluate(Integer id,SecurityInfo securityInfo)
    {
      String error="删除成功!";
      try{
        assistantOrgDAO.delete_YM(id);
        PlOperateLog plOperateLog=new PlOperateLog();//创建日志对象     
        plOperateLog.setOpSys(new BigDecimal(BusiLogConst.OP_SYSTEM_TYPE_LOAN));//个贷系统
        plOperateLog.setOpModel(String.valueOf(BusiLogConst.PL_OP_DATAPREPARATION_ASSESSMENTAGEN));//代理机构
        plOperateLog.setOpButton("3");
        plOperateLog.setOpBizId(new BigDecimal(id.toString())); 
        plOperateLog.setOpIp(securityInfo.getUserIp());
        plOperateLog.setOpTime(new Date());
        plOperateLog.setOperator(securityInfo.getUserName());
        plOperateLogDAO.insert(plOperateLog);
      }catch(Exception e)
      {
        error="删除失败!";
        e.printStackTrace();
      }
      return error;
    }
    /**
     * name 杨蒙
     *根据id 判断是否有记录
     *true 有此记录
     *false 无此记录
     */
    public boolean is_Evaluate_YM(Integer id)
    {
      boolean is_bank=false;
      try {
        AssistantOrg AssistantOrg=assistantOrgDAO.queryById(id);
        if(AssistantOrg==null)
          is_bank=true;
      } catch (RuntimeException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
      return is_bank;
    }
  public AssistantOrgDAO getAssistantOrgDAO() {
    return assistantOrgDAO;
  }
  public void setAssistantOrgDAO(AssistantOrgDAO assistantOrgDAO) {
    this.assistantOrgDAO = assistantOrgDAO;
  }
  public OrganizationUnitDAO getOrganizationUnitDAO() {
    return organizationUnitDAO;
  }
  public void setOrganizationUnitDAO(OrganizationUnitDAO organizationUnitDAO) {
    this.organizationUnitDAO = organizationUnitDAO;
  }
  public PlOperateLogDAO getPlOperateLogDAO() {
    return plOperateLogDAO;
  }
  public void setPlOperateLogDAO(PlOperateLogDAO plOperateLogDAO) {
    this.plOperateLogDAO = plOperateLogDAO;
  }


  
  
}
