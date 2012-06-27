package org.xpup.hafmis.syscollection.peoplebank.documents.business;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Vector;
import org.xpup.common.exception.BusinessException;
import org.xpup.hafmis.common.util.bizlog.BusiLogConst;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.common.dao.BankInfoDAO;
import org.xpup.hafmis.syscollection.common.dao.EmpDAO;
import org.xpup.hafmis.syscollection.common.dao.OfficeParaDAO;
import org.xpup.hafmis.syscollection.peoplebank.documents.bsinterface.IDocumentBS;
import org.xpup.hafmis.syscollection.peoplebank.documents.dto.BankInterfaceInfoDTO;
import org.xpup.hafmis.syscollection.peoplebank.documents.dto.DocumentstopDTO;
import org.xpup.hafmis.syscommon.dao.HafOperateLogDAO;
import org.xpup.hafmis.syscommon.domain.entity.HafOperateLog;

public class DocumentBS implements IDocumentBS {

  private HafOperateLogDAO hafOperateLogDAO = null;

  private EmpDAO empDAO = null;

  private OfficeParaDAO officeParaDAO = null;

  private BankInfoDAO bankInfoDAO = null;

  public void setHafOperateLogDAO(HafOperateLogDAO hafOperateLogDAO) {
    this.hafOperateLogDAO = hafOperateLogDAO;
  }

  public void setEmpDAO(EmpDAO empDAO) {
    this.empDAO = empDAO;
  }

  public void setOfficeParaDAO(OfficeParaDAO officeParaDAO) {
    this.officeParaDAO = officeParaDAO;
  }

  public void setBankInfoDAO(BankInfoDAO bankInfoDAO) {
    this.bankInfoDAO = bankInfoDAO;
  }

  public Vector content;
  
  public Vector getAllInfo(String date, SecurityInfo securityInfo,DocumentstopDTO documentstopdto) throws Exception, BusinessException {
    List list =new ArrayList();
    content = new Vector();
    try{
      //查询数据
      list=empDAO.queryBankInterfaceInfo(date);
//报文头信息
      if (documentstopdto.getSettingtype().equals("A")) {// 分隔符
        //查询分隔符
        String returnchar=officeParaDAO.queryparavalueBytype_WL();
        String strmng="";
        for(int k=1;k<7;k++){
          if(k==1){
            strmng+=documentstopdto.getNum().concat(returnchar);
          }else if(k==2){
            strmng+=documentstopdto.getUporganization().concat(returnchar);
          }else if(k==3){
            strmng+=list.size()+"".concat(returnchar);
          }else if(k==4){
            strmng+=new SimpleDateFormat("yyyyMMddHHmmss").format(Calendar.getInstance().getTime()).concat(returnchar);
          }else if(k==5){
            strmng+=documentstopdto.getPeople().concat(returnchar);
          }else if(k==6){
            strmng+=documentstopdto.getTep().concat(returnchar);
          }
        }
        String[] strLinetop = new String[1];   
        strLinetop[0]=strmng;
        content.addElement(strLinetop);
      }else {// 固定长度
        //查询定义格式
        String strmng="";
        DocumentstopDTO topdto=new DocumentstopDTO();
        List returnlist=null;
        for(int w=1;w<7;w++){
          returnlist=officeParaDAO.queryparavalueBynum_WL(""+w);
          for(int j=0;j<returnlist.size();j++){
            DocumentstopDTO temptopdto=(DocumentstopDTO)returnlist.get(j);
            if(j==0){
              topdto.setParavalue_1(temptopdto.getParavalue());
            }else if(j==1){
              topdto.setParavalue_2(temptopdto.getParavalue());
            }else{
              topdto.setParavalue_3(temptopdto.getParavalue());
            }
          }
//          for(int k=1;k<7;k++){
            if(w==1){
              strmng+=convert(documentstopdto.getNum(),Integer.parseInt(topdto.getParavalue_1()),topdto.getParavalue_2(),topdto.getParavalue_3());
            }else if(w==2){
              strmng+=convert(documentstopdto.getUporganization(),Integer.parseInt(topdto.getParavalue_1()),topdto.getParavalue_2(),topdto.getParavalue_3());
            }else if(w==3){
              strmng+=convert(list.size()+"",Integer.parseInt(topdto.getParavalue_1()),topdto.getParavalue_2(),topdto.getParavalue_3());
            }else if(w==4){
              strmng+=convert(new SimpleDateFormat("yyyyMMddHHmmss").format(Calendar.getInstance().getTime()),Integer.parseInt(topdto.getParavalue_1()),topdto.getParavalue_2(),topdto.getParavalue_3());
            }else if(w==5){
              strmng+=convert(documentstopdto.getPeople(),Integer.parseInt(topdto.getParavalue_1()),topdto.getParavalue_2(),topdto.getParavalue_3());
            }else if(w==6){
              strmng+=convert(documentstopdto.getTep(),Integer.parseInt(topdto.getParavalue_1()),topdto.getParavalue_2(),topdto.getParavalue_3());
            }
//          }
       }
        String[] strLinetop = new String[1];   
        strLinetop[0]=strmng;
        content.addElement(strLinetop);
      }
//职工账户信息
      String[] strLine = new String[list.size()];
      if(list.size()!=0){
        for(int i=0;i<list.size();i++){
          String strmng="";
          BankInterfaceInfoDTO bankInterfaceInfoDTO = (BankInterfaceInfoDTO) list.get(i);
          
          // 根据参数设置进行存储
          if (documentstopdto.getSettingtype().equals("A")) {// 分隔符
            //查询分隔符
            String returnchar=officeParaDAO.queryparavalueBytype_WL();
            for(int k=7;k<21;k++){
              if(k==7){
                strmng+=bankInterfaceInfoDTO.getIdentifyCode().concat(returnchar);
              }else if(k==8){
                strmng+=bankInterfaceInfoDTO.getHappenLocus().concat(returnchar);
              }else if(k==9){
                strmng+=bankInterfaceInfoDTO.getOrganization().concat(returnchar);
              }else if(k==10){
                strmng+=bankInterfaceInfoDTO.getAccounts().concat(returnchar);
              }else if(k==11){
                strmng+=bankInterfaceInfoDTO.getEmpName().concat(returnchar);
              }else if(k==12){
                strmng+=bankInterfaceInfoDTO.getCardNum().concat(returnchar);
              }else if(k==13){
                strmng+=bankInterfaceInfoDTO.getOrgName().concat(returnchar);
              }else if(k==14){
                strmng+=bankInterfaceInfoDTO.getCharacter().concat(returnchar);
              }else if(k==15){
                strmng+=bankInterfaceInfoDTO.getOpenDate().concat(returnchar);
              }else if(k==16){
                strmng+=bankInterfaceInfoDTO.getPayMonth().concat(returnchar);
              }else if(k==17){
                strmng+=bankInterfaceInfoDTO.getPayOverMonth().concat(returnchar);
              }else if(k==18){
                strmng+=bankInterfaceInfoDTO.getPayStatus().concat(returnchar);
              }else if(k==19){
                strmng+=bankInterfaceInfoDTO.getLastPayMonth().concat(returnchar);
              }else if(k==20){
                strmng+=bankInterfaceInfoDTO.getPay().concat(returnchar);
              }
            }
          } else {// 固定长度
            //查询定义格式
            DocumentstopDTO topdto=new DocumentstopDTO();
            List returnlist=null;
            for(int w=7;w<21;w++){
              returnlist=officeParaDAO.queryparavalueBynum_WL(""+w);
              for(int j=0;j<returnlist.size();j++){
                DocumentstopDTO temptopdto=(DocumentstopDTO)returnlist.get(j);
                if(j==0){
                  topdto.setParavalue_1(temptopdto.getParavalue());
                }else if(j==1){
                  topdto.setParavalue_2(temptopdto.getParavalue());
                }else{
                  topdto.setParavalue_3(temptopdto.getParavalue());
                }
              }
//              for(int k=7;k<21;k++){
                if(w==7){
                  strmng+=convert(bankInterfaceInfoDTO.getIdentifyCode(),Integer.parseInt(topdto.getParavalue_1()),topdto.getParavalue_2(),topdto.getParavalue_3());
                }else if(w==8){
                  strmng+=convert(bankInterfaceInfoDTO.getHappenLocus(),Integer.parseInt(topdto.getParavalue_1()),topdto.getParavalue_2(),topdto.getParavalue_3());
                }else if(w==9){
                  strmng+=convert(bankInterfaceInfoDTO.getOrganization(),Integer.parseInt(topdto.getParavalue_1()),topdto.getParavalue_2(),topdto.getParavalue_3());
                }else if(w==10){
                  strmng+=convert(bankInterfaceInfoDTO.getAccounts(),Integer.parseInt(topdto.getParavalue_1()),topdto.getParavalue_2(),topdto.getParavalue_3());
                }else if(w==11){
                  strmng+=convert(bankInterfaceInfoDTO.getEmpName(),Integer.parseInt(topdto.getParavalue_1()),topdto.getParavalue_2(),topdto.getParavalue_3());
                }else if(w==12){
                  strmng+=convert(bankInterfaceInfoDTO.getCardNum(),Integer.parseInt(topdto.getParavalue_1()),topdto.getParavalue_2(),topdto.getParavalue_3());
                }else if(w==13){
                  strmng+=convert(bankInterfaceInfoDTO.getOrgName(),Integer.parseInt(topdto.getParavalue_1()),topdto.getParavalue_2(),topdto.getParavalue_3());
                }else if(w==14){
                  strmng+=convert(bankInterfaceInfoDTO.getCharacter(),Integer.parseInt(topdto.getParavalue_1()),topdto.getParavalue_2(),topdto.getParavalue_3());
                }else if(w==15){
                  strmng+=convert(bankInterfaceInfoDTO.getOpenDate(),Integer.parseInt(topdto.getParavalue_1()),topdto.getParavalue_2(),topdto.getParavalue_3());
                }else if(w==16){
                  strmng+=convert(bankInterfaceInfoDTO.getPayMonth(),Integer.parseInt(topdto.getParavalue_1()),topdto.getParavalue_2(),topdto.getParavalue_3());
                }else if(w==17){
                  strmng+=convert(bankInterfaceInfoDTO.getPayOverMonth(),Integer.parseInt(topdto.getParavalue_1()),topdto.getParavalue_2(),topdto.getParavalue_3());
                }else if(w==18){
                  strmng+=convert(bankInterfaceInfoDTO.getPayStatus(),Integer.parseInt(topdto.getParavalue_1()),topdto.getParavalue_2(),topdto.getParavalue_3());
                }else if(w==19){
                  strmng+=convert(bankInterfaceInfoDTO.getLastPayMonth(),Integer.parseInt(topdto.getParavalue_1()),topdto.getParavalue_2(),topdto.getParavalue_3());
                }else if(w==20){
                  strmng+=convert(bankInterfaceInfoDTO.getPay(),Integer.parseInt(topdto.getParavalue_1()),topdto.getParavalue_2(),topdto.getParavalue_3());
                }
//              }
           }
          }
          strLine[i] = strmng;   
          content.addElement(strLine);
        }
      }
      // 插入BA003
      HafOperateLog hafOperateLog = new HafOperateLog();
      hafOperateLog.setOpSys(new Integer(BusiLogConst.OP_SYSTEM_TYPE_COLLECTION));
      hafOperateLog.setOpModel("" + BusiLogConst.OP_MODE_BANKDATAEXP_RECORD);
      hafOperateLog.setOpButton("" + BusiLogConst.BIZLOG_ACTION_EXP);
      hafOperateLog.setOpIp(securityInfo.getUserInfo().getUserIp());
      hafOperateLog.setOpTime(new Date());
      hafOperateLog.setOperator(securityInfo.getUserInfo().getUsername());
      hafOperateLogDAO.insert(hafOperateLog);
      
    }catch(Exception e){
      e.printStackTrace();
    }
    return content;
  }

  public DocumentstopDTO gettopInfo(SecurityInfo securityInfo) throws Exception, BusinessException {
    // TODO Auto-generated method stub
    List list = bankInfoDAO.gettopInfo_WL();
    DocumentstopDTO dto=new DocumentstopDTO();
    DocumentstopDTO tempdto=new DocumentstopDTO();
    for(int i=0;i<list.size();i++){
      tempdto=(DocumentstopDTO)list.get(i);
      if(i==0){ 
        dto.setNum(tempdto.getParavalue());
      }else if(i==1){
        dto.setUporganization(tempdto.getParavalue());
      }else if(i==2){
        dto.setPeople(tempdto.getParavalue());
      }else if(i==3){
        dto.setTep(tempdto.getParavalue());
        dto.setSettingtype(tempdto.getSettingtype());
      }
    }
    return dto;
  }
  
  public static String convert(String s,int length,String sign,String code){
    String obj=s;
    int pad=0;
    int len=s.length();
    if(code.equals("2"))
    pad=length-len;
    if(code.equals("1"))
    pad=length-len*2;
    if((sign.equals("1")) && (pad>0)){
      for(int i=0;i<pad;i++){
        obj=obj+" ";
      }
    }
    if((sign.equals("2")) && (pad>0)){
      for(int i=0;i<pad;i++){
        obj="0"+obj;
      }
    }
    return obj;
  }
  
}
