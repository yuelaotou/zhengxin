package org.xpup.hafmis.sysfinance.common.biz.credencepop.business;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.xpup.hafmis.orgstrct.dao.OrganizationUnitDAO;
import org.xpup.hafmis.orgstrct.dao.SecurityDAO;
import org.xpup.hafmis.orgstrct.domain.OrganizationUnit;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysfinance.common.biz.credencepop.bsinterface.ICredencePopBS;
import org.xpup.hafmis.sysfinance.common.biz.credencepop.dto.CredencePopInfoDTO;
import org.xpup.hafmis.sysfinance.common.biz.credencepop.dto.CredencePopListDTO;
import org.xpup.hafmis.sysfinance.common.dao.AccountantCredenceDAO;
import org.xpup.hafmis.sysfinance.common.dao.BookParameterDAO;
import org.xpup.hafmis.sysfinance.common.dao.TreasurerCredenceDAO;

/**
 * Copy Right Information : 凭证弹出框BSAction Goldsoft Project : CredencePopBS
 * 
 * @Version : v1.0
 * @author : 付云峰
 * @Date : 2007.11.3
 */
public class CredencePopBS implements ICredencePopBS {

  protected AccountantCredenceDAO accountantCredenceDAO = null;

  protected OrganizationUnitDAO organizationUnitDAO = null;

  protected BookParameterDAO bookParameterDAO = null;

  protected TreasurerCredenceDAO treasurerCredenceDAO = null;

  protected SecurityDAO securityDAO = null;

  public void setTreasurerCredenceDAO(TreasurerCredenceDAO treasurerCredenceDAO) {
    this.treasurerCredenceDAO = treasurerCredenceDAO;
  }

  public void setAccountantCredenceDAO(
      AccountantCredenceDAO accountantCredenceDAO) {
    this.accountantCredenceDAO = accountantCredenceDAO;
  }

  public void setBookParameterDAO(BookParameterDAO bookParameterDAO) {
    this.bookParameterDAO = bookParameterDAO;
  }

  public void setOrganizationUnitDAO(OrganizationUnitDAO organizationUnitDAO) {
    this.organizationUnitDAO = organizationUnitDAO;
  }

  public void setSecurityDAO(SecurityDAO securityDAO) {
    this.securityDAO = securityDAO;
  }

  public Object[] findAccountantCredence(String docNum,
      SecurityInfo securityInfo, String credenceDate, String office)
      throws Exception {
    Object[] obj = new Object[4];
    String bookId = securityInfo.getBookId();
    List list = new ArrayList();
    CredencePopInfoDTO credencePopInfoDTO = new CredencePopInfoDTO();
    list = accountantCredenceDAO.queryCredencePopList(docNum, bookId,
        credenceDate, office);
    BigDecimal sumDebit = new BigDecimal(0.00);
    BigDecimal sumCredit = new BigDecimal(0.00);
    for (int i = 0; i < list.size(); i++) {
      CredencePopListDTO credencePopListDTO = (CredencePopListDTO) list.get(i);
      sumCredit = sumCredit.add(credencePopListDTO.getCredit());
      sumDebit = sumDebit.add(credencePopListDTO.getDebit());
      credencePopListDTO.setCredit(credencePopListDTO.getCredit().multiply(
          new BigDecimal(100)).setScale(0));
      credencePopListDTO.setDebit(credencePopListDTO.getDebit().multiply(
          new BigDecimal(100)).setScale(0));
      String summay = bookParameterDAO
          .queryParamExplainByParaId(credencePopListDTO.getSummay());
      credencePopListDTO.setSummay(summay);
    }
    credencePopInfoDTO = accountantCredenceDAO.queryCredencePopInfo(docNum,
        bookId, credenceDate, office);
    String settType = bookParameterDAO.queryParamExplainByParaId(bookId,
        credencePopInfoDTO.getSettType());
    // 得到结转类型
    credencePopInfoDTO.setSettType(settType);
    // 转换办事处
    OrganizationUnit organizationUnit = organizationUnitDAO
        .queryOrganizationUnitListByCriterions(credencePopInfoDTO.getOffice());
    // 转换姓名
    credencePopInfoDTO.setMakebill(securityDAO.queryByUserid(credencePopInfoDTO
        .getMakebill()));
    credencePopInfoDTO.setCheckpsn(securityDAO.queryByUserid(credencePopInfoDTO
        .getCheckpsn()));
    credencePopInfoDTO.setClearpsn(securityDAO.queryByUserid(credencePopInfoDTO
        .getClearpsn()));
    credencePopInfoDTO.setOffice(organizationUnit.getName());
    obj[0] = list;
    obj[1] = credencePopInfoDTO;
    obj[2] = sumDebit.multiply(new BigDecimal(100)).setScale(0);
    obj[3] = sumCredit.multiply(new BigDecimal(100)).setScale(0);
    return obj;
  }

  public String findParamExplain(String credenceCharacter,
      SecurityInfo securityInfo) throws Exception {
    Object obj = accountantCredenceDAO.queryParamExplain(credenceCharacter,
        securityInfo.getBookId());
    return obj.toString();
  }
}
