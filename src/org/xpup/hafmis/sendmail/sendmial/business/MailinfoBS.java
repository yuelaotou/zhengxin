package org.xpup.hafmis.sendmail.sendmial.business;
/**
 *2008/05/28 
 * shiy
 * 邮件系统
 */

import java.util.List;
import java.util.Properties;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.xpup.common.exception.BusinessException;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sendmail.common.dao.MailinfoDAO;
import org.xpup.hafmis.sendmail.sendmial.bsinterface.IMailinfoBS;
import org.xpup.hafmis.sendmail.sendmial.dto.MailinfoDTO;
import org.xpup.hafmis.sendmail.sendmial.form.MailinfoAF;

public class MailinfoBS implements IMailinfoBS {

  private MailinfoDAO mailinfoDAO = null;

  public void setMailinfoDAO(MailinfoDAO mailinfoDAO) {
    this.mailinfoDAO = mailinfoDAO;
  }
  // 查找现在使用的邮件信箱信息
  public MailinfoAF queryMailinfo()throws BusinessException {
    MailinfoAF mailinfoAF = new MailinfoAF();
    // 查找正在使用的邮箱信息。
    List list = mailinfoDAO.queryMailinfo();
    try{
    if (!list.isEmpty()) {
      for (int i = 0; i < list.size(); i++) {
        MailinfoDTO dto = (MailinfoDTO) list.get(i);
        // 数据库中存的邮件人帐户是拆分的，目的是发送邮件时候容易获取。
        String addresserMail = dto.getAddresserMail();
        mailinfoAF.setAddresserMail(addresserMail);
        String addresserPassword = dto.getAddresserPassword();// 发件人帐户密码
        mailinfoAF.setAddresserPassword(addresserPassword);
        String addresseeA = dto.getAddresseeA();// 收件人邮箱A
        mailinfoAF.setAddresseeA(addresseeA);
        String addresseeB = dto.getAddresseeB();// 收件人邮箱B
        mailinfoAF.setAddresseeB(addresseeB);
        String subjectName = dto.getSubjectName();// 发送主题
        mailinfoAF.setSubjectName(subjectName);
        String mailId=dto.getMailId();
        mailinfoAF.setMailId(mailId);
      }
    }
    }catch(Exception e){
      e.printStackTrace();
    }
    return mailinfoAF;
  }
  //添加新的邮件基本信息，同时更新现用的邮件信息
  public String addMailinfo(String mailId, String addresserMail, String addresserPassword,
      String addresseeA, String addresseeB, String subjectName,
      SecurityInfo securityInfo)throws BusinessException{
    String info="";
    try{
    //获取"@"所在的位置，便于截取。
     int findint=addresserMail.indexOf("@"); 
    //获取发件人的账号
    String addresser=addresserMail.substring(0, findint);
    //获取邮箱类型
    String tempmailbox=addresserMail.substring(findint+1);
    //匹配成系统可以识别的邮箱代码
    String mailBoxType="smtp."+tempmailbox;
    //发件人的用户和密码是否正确。
    try{
    this.checksendtomail(mailBoxType,addresserMail,addresser,addresserPassword,addresseeA,addresseeB);
    }catch(Exception e){
      throw new BusinessException("发件人邮箱或密码不正确请核实,收件邮箱是否正确！");
    }
    //封装数据，插入数据库中。
    MailinfoDTO dto=new MailinfoDTO();
    dto.setAddresser(addresser);
    dto.setAddresserMail(addresserMail);
    dto.setAddresserPassword(addresserPassword);
    dto.setMailBoxType(mailBoxType);
    dto.setAddresseeA(addresseeA);
    dto.setAddresseeB(addresseeB);
    dto.setSubjectName(subjectName);
    dto.setMailId(mailId);
    //插入新的邮件信息
    mailinfoDAO.insertMailinfo(dto, securityInfo);
     info="pass";
    }catch(BusinessException bux){
      throw bux;
    }
    catch(Exception e){
      throw new BusinessException("信息录入失败，请确认是否正确安装邮件异常跟踪系统！");
    }
    return info;
  }
  public void checksendtomail(String mailBoxType,String addresserMail,String addresser,String addresserPassword,String addresseeA,String addresseeB) throws Exception {
    try {
      //调用spring邮件类
      JavaMailSenderImpl mailSender1 = new JavaMailSenderImpl();
      //填写发送邮件箱类型，采用smtp邮件协议
      mailSender1.setHost(mailBoxType);
//      填写发送邮件箱帐户
      mailSender1.setUsername(addresser);
//      填写发送邮件箱密码
      mailSender1.setPassword(addresserPassword);
//      定义资源文件，填写属性
      Properties mailproperties = new Properties();
      mailproperties.setProperty("mail.smtp.auth", "true");
      mailproperties.setProperty("mail.smtp.timeout", "25000");
      mailSender1.setJavaMailProperties(mailproperties);
//      发送的内容部分
      SimpleMailMessage mail = new SimpleMailMessage();
      int addresseemail=1;
      if(addresseeB!=null&&!addresseeB.equals(""))
        addresseemail=addresseemail+1;
      //收件邮箱A
      String[] addressee = new String[addresseemail];
      addressee[0] = addresseeA;
      //收件邮箱B
      if(addresseeB!=null&&!addresseeB.equals(""))
      addressee[1] = addresseeB; 
      mail.setTo(addressee);
      mail.setFrom(addresserMail);
      mail.setSubject("欢迎使用邮件异常跟踪系统！");
      mail.setText("我们会在第一时间把异常信息做出反馈。");
      mailSender1.send(mail);
    } catch (Exception e) {
      throw e;
    }
  }
}
