package org.xpup.hafmis.sendmail.common.util;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.SQLException;
import java.util.Properties;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.xpup.common.exception.BusinessException;
import org.xpup.hafmis.orgstrct.dto.MailMessageDTO;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;

public class Sendexceptionmail {
	public static void sendtomail(Object emassage,SecurityInfo securityInfo) {
		try {
      //查看是否使用邮件系统1是使用0是不使用
      if(securityInfo.getMailFunction()==1){
        //获得发件人的邮箱信息，收件人的邮箱信息
       MailMessageDTO dto= securityInfo.getMaildto();
			Sendexceptionmail sendtomail = new Sendexceptionmail();
			//调用spring邮件类
			JavaMailSenderImpl senderImpl = new JavaMailSenderImpl();
			//填写发送邮件箱类型，采用smtp邮件协议
			senderImpl.setHost(dto.getMailboxtype());
//			填写发送邮件箱帐户
			senderImpl.setUsername(dto.getAddresser());
//			填写发送邮件箱密码
			senderImpl.setPassword(dto.getAddersserpassword());
//			定义资源文件，填写属性
			Properties mailproperties = new Properties();
      mailproperties.setProperty("mail.smtp.auth", "true");
      mailproperties.setProperty("mail.smtp.timeout", "25000");
			senderImpl.setJavaMailProperties(mailproperties);
//			发送的内容部分
			sendtomail.sendMimeMessage(senderImpl,emassage,dto);
      }
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
//保存发送的内容
	private void sendMimeMessage(JavaMailSenderImpl sender,final Object emassage,MailMessageDTO dto) throws Exception {
      SimpleMailMessage mail = new SimpleMailMessage();
      int addresseemail=1;
      if(dto.getAddresseeb()!=null&&!dto.getAddresseeb().equals(""))
        addresseemail=addresseemail+1;
      //收件邮箱A
      String[] addressee = new String[addresseemail];
      addressee[0] = dto.getAddresseea();
      //收件邮箱B
      if(dto.getAddresseeb()!=null&&!dto.getAddresseeb().equals(""))
      addressee[1] = dto.getAddresseeb(); 
      mail.setTo(addressee);
      //发件人邮箱
      mail.setFrom(dto.getAddressermail());
      //发送的题目
      mail.setSubject(dto.getSubjectname());
      ExceptionDTO dtoe=(ExceptionDTO)emassage;
      if(dtoe!=null){
        //exception异常
        if(dtoe.getExcep()!=null){
    	  Exception e=(Exception)dtoe.getExcep();
    	  StringWriter   swe   =   new   StringWriter();   
    	  PrintWriter   w   =   new   PrintWriter(swe);   
    	  e.printStackTrace(w); 
    	  mail.setText("异常信息为Exception："+swe.getBuffer());
        }
        //BusinessException
        if(dtoe.getBx()!=null){
          BusinessException bx=(BusinessException)dtoe.getBx();
          mail.setText("异常信息为BusinessException："+bx.getMessage());
        }
//      SQLException
        if(dtoe.getSqe()!=null){
          SQLException sqle=(SQLException)dtoe.getSqe();
          StringWriter   sws   =   new   StringWriter();   
          PrintWriter   w   =   new   PrintWriter(sws);   
          sqle.printStackTrace(w); 
          mail.setText("异常信息为SQLException："+sws.getBuffer());
        }
//      IOException
        if(dtoe.getIoe()!=null){
          IOException ioe=(IOException)dtoe.getIoe();
          StringWriter   swi   =   new   StringWriter();   
          PrintWriter   w   =   new   PrintWriter(swi);   
          ioe.printStackTrace(w); 
          mail.setText("异常信息为IOException："+swi.getBuffer());
        }
      }
      sender.send(mail);
	}
}
