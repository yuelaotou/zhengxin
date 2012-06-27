package org.xpup.hafmis.sendmail.sendmial.bsinterface;

import org.xpup.common.exception.BusinessException;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sendmail.sendmial.form.MailinfoAF;

public interface IMailinfoBS {
  /** 查找现在使用的邮件信箱信息 */
  MailinfoAF queryMailinfo()throws BusinessException;
  /**添加新的邮件基本信息，同时更新现用的邮件信息*/
   String addMailinfo(String mailId, String addresserMail, String addresserPassword,
      String addresseeA, String addresseeB, String subjectName,
      SecurityInfo securityInfo)throws BusinessException;
}
