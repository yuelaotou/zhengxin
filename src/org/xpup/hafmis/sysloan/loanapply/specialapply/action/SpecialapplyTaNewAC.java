package org.xpup.hafmis.sysloan.loanapply.specialapply.action;

import java.math.BigDecimal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.xpup.common.util.BSUtils;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysloan.common.domain.entity.SpecialBorrower;
import org.xpup.hafmis.sysloan.loanapply.specialapply.bsinterface.ISpecialapplyBS;
import org.xpup.hafmis.sysloan.loanapply.specialapply.dto.SpecialapplyDTO;
import org.xpup.hafmis.sysloan.loanapply.specialapply.form.SpecialapplyNewAF;

/**
 * MyEclipse Struts Creation date: 09-27-2007 XDoclet definition:
 * 
 * @struts.action path="/specialapplyTaNewAC" name="SpecialapplyNewAF"
 *                scope="request" validate="true"
 */
public class SpecialapplyTaNewAC extends Action {
  /*
   * Generated Methods
   */
  /**
   * Method execute
   * 
   * @param mapping
   * @param form
   * @param request
   * @param response
   * @return ActionForward
   */
  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response) {

    SpecialapplyNewAF specialapplyNewAF = (SpecialapplyNewAF) form;
    try {
      String orgId = request.getParameter("org_Id");
      ISpecialapplyBS specialapplyBS = (ISpecialapplyBS) BSUtils
          .getBusinessService("specialapplyBS", this, mapping.getModuleConfig());

      // Session 里面拿出权限和数据
      SecurityInfo securityInfo = (SecurityInfo) request.getSession()
          .getAttribute("SecurityInfo");
      String operator = securityInfo.getUserName();// 操作员
      String userId = securityInfo.getUserIp();// Ip

      SpecialapplyDTO specialapplyDTO = null;
      ActionMessages messages = null;
      SpecialBorrower specialBorrower = null;
      BigDecimal maxMoney = securityInfo.getUserInfo().getCheckMoney();
      System.out.println("该操作员的预审金额为==========>" + maxMoney);
      if (maxMoney == null
          || Float.parseFloat(specialapplyNewAF.getLoanMoney().toString()) > maxMoney
              .floatValue()) {
        messages = new ActionMessages();
        messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
            "输入的金额不能超过预审金额! ", false));
        saveErrors(request, messages);
        return mapping.findForward("specialapplyTaShowAC");
      }
      // 点按钮带出来的值
      if (specialapplyNewAF.getPrivilegeBorrowerId().trim() != null
          && !specialapplyNewAF.getPrivilegeBorrowerId().trim().equals("")) {
        // 特殊借款人信息表PL112是否存在借款人姓名、证件号码等于录入的记录
        boolean is_temp = specialapplyBS.isCheckNameANDCardNum_SpecialBorrower(
            specialapplyNewAF.getBorrowerName().trim(),
            specialapplyNewAF.getCardNum().trim()).booleanValue();
        // PL112表里没有数据
        if (!is_temp) {
          // AA002表里查看 是否存在借款人姓名、证件号码等于录入的记录
          specialapplyDTO = specialapplyBS.findSpecialapplyInfoById(
              specialapplyNewAF.getPrivilegeBorrowerId().trim(), orgId);
          // AA002表里有记录
          if (specialapplyDTO != null) {
            specialapplyDTO.setLoanMoney(new BigDecimal(specialapplyNewAF
                .getLoanMoney().toString().trim()));
            specialapplyDTO.setLoanTimeLimit(specialapplyNewAF
                .getLoanTimeLimit().trim());
            specialapplyDTO.setStutas("0");
            specialapplyDTO.setOperator(operator);
            specialapplyDTO.setUserIp(userId);
            specialapplyDTO.setEmpId(specialapplyNewAF.getPrivilegeBorrowerId()
                .trim());
            specialapplyDTO.setPerBank(specialapplyNewAF.getPerBank().trim());
            specialapplyDTO.setPerBankAcc(specialapplyNewAF.getPerBankAcc()
                .trim());
            specialapplyDTO.setReserveaA(specialapplyNewAF.getRemark());
            specialapplyDTO.setReserveaB(specialapplyNewAF.getSfbrzh());
            specialapplyDTO.setHeadId(specialapplyNewAF.getHeadId());
            specialapplyDTO.setFloorId(specialapplyNewAF.getFloorId());
            // 插入 特殊借款人信息表PL112和 插入：操作日志PL021
            specialapplyBS
                .insertSpecialApplyInfo(specialapplyDTO, securityInfo);

          } else {// AA002表里没有记录
            messages = new ActionMessages();
            messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
                "用户表里没有用户填写的信息！", false));
            saveErrors(request, messages);
            return mapping.findForward("specialapplyTaShowAC");
          }
        } else {// PL112表里有数据
          specialBorrower = specialapplyBS.findSpecialBorrowerStutasByEmpId(
              specialapplyNewAF.getPrivilegeBorrowerId().trim(),
              specialapplyNewAF.getOrgId().toString());
          // PL112数据记录 状态不为零
          if (specialBorrower == null) {
            // 显示 状态为一 的数据记录
            specialBorrower = specialapplyBS
                .findSpecialBorrowerStutasByEmpIdTop1(specialapplyNewAF
                    .getPrivilegeBorrowerId().trim(), specialapplyNewAF
                    .getOrgId().toString());

            specialapplyDTO = new SpecialapplyDTO();
            specialapplyDTO.setPrivilegeBorrowerId(specialBorrower
                .getPrivilegeBorrowerId().toString());
            specialapplyDTO.setBorrowerName(specialBorrower.getBorrowerName());
            specialapplyDTO.setCardKind(specialBorrower.getCardKind());
            specialapplyDTO.setCardNum(specialBorrower.getCardNum());
            specialapplyDTO.setOrgId(specialBorrower.getOrgId().toString());
            specialapplyDTO.setOrgName(specialBorrower.getOrgName());
            specialapplyDTO.setLoanTimeLimit(specialapplyNewAF
                .getLoanTimeLimit().trim());
            specialapplyDTO.setLoanMoney(new BigDecimal(specialapplyNewAF
                .getLoanMoney().toString().trim()));
            specialapplyDTO.setEmpId(specialapplyNewAF.getPrivilegeBorrowerId()
                .trim());
            specialapplyDTO.setStutas("0");
            specialapplyDTO.setOperator(operator);
            specialapplyDTO.setUserIp(userId);
            // 插入银行名称、银行账号
            specialapplyDTO.setPerBank(specialapplyNewAF.getPerBank().trim());
            specialapplyDTO.setPerBankAcc(specialapplyNewAF.getPerBankAcc()
                .trim());
            specialapplyDTO.setReserveaA(specialapplyNewAF.getRemark());
            specialapplyDTO.setReserveaB(specialapplyNewAF.getSfbrzh());
            specialapplyDTO.setHeadId(specialapplyNewAF.getHeadId());
            specialapplyDTO.setFloorId(specialapplyNewAF.getFloorId());
            // 插入 特殊借款人信息表PL112和 插入：操作日志PL021
            specialapplyBS
                .insertSpecialApplyInfo(specialapplyDTO, securityInfo);
          } else {// PL112数据记录 状态等于零
            specialapplyDTO = new SpecialapplyDTO();
            specialapplyDTO.setPrivilegeBorrowerId(specialBorrower
                .getPrivilegeBorrowerId().toString());
            specialapplyDTO.setBorrowerName(specialapplyNewAF.getBorrowerName()
                .trim());
            specialapplyDTO.setCardKind(specialapplyNewAF.getCardKind().trim());
            specialapplyDTO.setCardNum(specialapplyNewAF.getCardNum().trim());
            specialapplyDTO.setOrgId(specialapplyNewAF.getOrgId().toString());
            specialapplyDTO.setOrgName(specialapplyNewAF.getOrgName().trim());
            specialapplyDTO.setLoanTimeLimit(specialapplyNewAF
                .getLoanTimeLimit().trim());
            specialapplyDTO.setLoanMoney(new BigDecimal(specialapplyNewAF
                .getLoanMoney().toString().trim()));
            specialapplyDTO.setEmpId(specialapplyNewAF.getPrivilegeBorrowerId()
                .trim());
            specialapplyDTO.setStutas("0");
            specialapplyDTO.setOperator(operator);
            specialapplyDTO.setUserIp(userId);
            // 插入银行名称、银行账号
            specialapplyDTO.setPerBank(specialapplyNewAF.getPerBank().trim());
            specialapplyDTO.setPerBankAcc(specialapplyNewAF.getPerBankAcc()
                .trim());
            specialapplyDTO.setReserveaA(specialapplyNewAF.getRemark());
            specialapplyDTO.setReserveaB(specialapplyNewAF.getSfbrzh());
            specialapplyDTO.setHeadId(specialapplyNewAF.getHeadId());
            specialapplyDTO.setFloorId(specialapplyNewAF.getFloorId());
            // 更新：特殊借款人信息表PL112根据借款人姓名、证件号码联合查询定位 插入：操作日志PL021
            specialapplyBS.updateSpecialapply(specialapplyDTO);
          }
        }
      } else {// 手输入的值
        // 特殊借款人信息表PL112是否存在借款人姓名、证件号码等于录入的记录
        boolean is_temp = specialapplyBS.isCheckNameANDCardNum_SpecialBorrower(
            specialapplyNewAF.getBorrowerName().trim(),
            specialapplyNewAF.getCardNum().trim()).booleanValue();
        // PL112表里没有数据
        if (!is_temp) {
          specialapplyDTO = new SpecialapplyDTO();
          specialapplyDTO.setBorrowerName(specialapplyNewAF.getBorrowerName()
              .trim());
          specialapplyDTO.setCardKind(specialapplyNewAF.getCardKind().trim());
          specialapplyDTO.setCardNum(specialapplyNewAF.getCardNum().trim());
          specialapplyDTO.setOrgName(specialapplyNewAF.getOrgName().trim());
          specialapplyDTO.setLoanTimeLimit(specialapplyNewAF.getLoanTimeLimit()
              .trim());
          specialapplyDTO.setLoanMoney(new BigDecimal(specialapplyNewAF
              .getLoanMoney().toString().trim()));
          specialapplyDTO.setEmpId("0");
          specialapplyDTO.setOrgId("0");
          specialapplyDTO.setStutas("0");
          specialapplyDTO.setOperator(operator);
          specialapplyDTO.setUserIp(userId);
          // 插入银行名称、银行账号
          specialapplyDTO.setPerBank(specialapplyNewAF.getPerBank().trim());
          specialapplyDTO.setPerBankAcc(specialapplyNewAF.getPerBankAcc()
              .trim());
          specialapplyDTO.setReserveaA(specialapplyNewAF.getRemark());
          specialapplyDTO.setReserveaB(specialapplyNewAF.getSfbrzh());
          specialapplyDTO.setHeadId(specialapplyNewAF.getHeadId());
          specialapplyDTO.setFloorId(specialapplyNewAF.getFloorId());
          // 插入 特殊借款人信息表PL112和 插入：操作日志PL021
          specialapplyBS.insertSpecialApplyInfo(specialapplyDTO, securityInfo);
        } else {// PL112表里有数据
          specialBorrower = specialapplyBS
              .findSpecialBorrowerStutasByNameAndNum(specialapplyNewAF
                  .getBorrowerName().trim(), specialapplyNewAF.getCardNum()
                  .trim());
          // PL112数据记录 状态不为零
          if (specialBorrower == null) {
            // 显示 状态为一 的数据记录
            specialBorrower = specialapplyBS
                .findSpecialBorrowerStutasByNameAndNumTop1(specialapplyNewAF
                    .getBorrowerName().trim(), specialapplyNewAF.getCardNum()
                    .trim());
            specialapplyDTO = new SpecialapplyDTO();
            specialapplyDTO.setPrivilegeBorrowerId(specialBorrower
                .getPrivilegeBorrowerId().toString());
            specialapplyDTO.setBorrowerName(specialBorrower.getBorrowerName());
            specialapplyDTO.setCardKind(specialBorrower.getCardKind());
            specialapplyDTO.setCardNum(specialBorrower.getCardNum());
            specialapplyDTO.setOrgId(specialBorrower.getOrgId().toString());
            specialapplyDTO.setOrgName(specialBorrower.getOrgName());
            specialapplyDTO.setLoanTimeLimit(specialapplyNewAF
                .getLoanTimeLimit().trim());
            specialapplyDTO.setLoanMoney(new BigDecimal(specialapplyNewAF
                .getLoanMoney().toString().trim()));
            specialapplyDTO.setEmpId(specialBorrower.getEmpId().toString());
            specialapplyDTO.setStutas("0");
            specialapplyDTO.setOperator(operator);
            specialapplyDTO.setUserIp(userId);
            // 插入银行名称、银行账号
            specialapplyDTO.setPerBank(specialapplyNewAF.getPerBank().trim());
            specialapplyDTO.setPerBankAcc(specialapplyNewAF.getPerBankAcc()
                .trim());
            specialapplyDTO.setReserveaA(specialapplyNewAF.getRemark());
            specialapplyDTO.setReserveaB(specialapplyNewAF.getSfbrzh());
            specialapplyDTO.setHeadId(specialapplyNewAF.getHeadId());
            specialapplyDTO.setFloorId(specialapplyNewAF.getFloorId());
            // 插入 特殊借款人信息表PL112和 插入：操作日志PL021
            specialapplyBS
                .insertSpecialApplyInfo(specialapplyDTO, securityInfo);
          } else {// PL112数据记录 状态等于零
            specialapplyDTO = new SpecialapplyDTO();
            specialapplyDTO.setPrivilegeBorrowerId(specialBorrower
                .getPrivilegeBorrowerId().toString());
            specialapplyDTO.setBorrowerName(specialBorrower.getBorrowerName());
            specialapplyDTO.setCardKind(specialBorrower.getCardKind());
            specialapplyDTO.setCardNum(specialBorrower.getCardNum());
            specialapplyDTO.setOrgId(specialBorrower.getOrgId().toString());
            specialapplyDTO.setOrgName(specialBorrower.getOrgName());
            specialapplyDTO.setLoanTimeLimit(specialapplyNewAF
                .getLoanTimeLimit().trim());
            specialapplyDTO.setLoanMoney(new BigDecimal(specialapplyNewAF
                .getLoanMoney().toString().trim()));
            specialapplyDTO.setEmpId(specialBorrower.getEmpId().toString());
            specialapplyDTO.setStutas("0");
            specialapplyDTO.setOperator(operator);
            specialapplyDTO.setUserIp(userId);
            // 插入银行名称、银行账号
            specialapplyDTO.setPerBank(specialapplyNewAF.getPerBank().trim());
            specialapplyDTO.setPerBankAcc(specialapplyNewAF.getPerBankAcc()
                .trim());
            specialapplyDTO.setReserveaA(specialapplyNewAF.getRemark());
            specialapplyDTO.setReserveaB(specialapplyNewAF.getSfbrzh());
            specialapplyDTO.setHeadId(specialapplyNewAF.getHeadId());
            specialapplyDTO.setFloorId(specialapplyNewAF.getFloorId());
            // 更新：特殊借款人信息表PL112根据借款人姓名、证件号码联合查询定位 插入：操作日志PL021
            specialapplyBS.updateSpecialapply(specialapplyDTO);
          }
        }
      }
    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return mapping.findForward("specialapplyTaShowAC");
  }
}