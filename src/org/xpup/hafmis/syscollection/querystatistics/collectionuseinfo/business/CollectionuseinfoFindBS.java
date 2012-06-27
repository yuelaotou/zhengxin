package org.xpup.hafmis.syscollection.querystatistics.collectionuseinfo.business;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.xpup.common.exception.BusinessException;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.common.dao.IncrementInAndOutDAO;
import org.xpup.hafmis.syscollection.common.domain.entity.IncrementInAndOut;
import org.xpup.hafmis.syscollection.querystatistics.collectionuseinfo.bsinterface.ICollectionuseinfoFindBS;
import org.xpup.hafmis.syscollection.querystatistics.collectionuseinfo.dto.DefineFormulaDto;

public class CollectionuseinfoFindBS implements ICollectionuseinfoFindBS{
  
  private IncrementInAndOutDAO incrementInAndOutDAO=null;
  public IncrementInAndOutDAO getIncrementInAndOutDAO() {
    return incrementInAndOutDAO;
  }

  public void setIncrementInAndOutDAO(IncrementInAndOutDAO incrementInAndOutDAO) {
    this.incrementInAndOutDAO = incrementInAndOutDAO;
  }
/**
 * 归集情况使用查询
 * @author 杨光
 * @return List
 * 
 */
  
  public List findCollectionuseinfo(SecurityInfo securityInfo,String officeCode,String collectionBankId,String queryTime) throws Exception, BusinessException {
    List returnList=new ArrayList();
    try {
      String year=queryTime.substring(0, 4);
      String month=queryTime.substring(4, 6);
      String lastTime="";
      if((Integer.parseInt(month)-1)<10){
        lastTime=year+"0"+(Integer.parseInt(month)-1)+"31";//上期末
      }else{
        lastTime=year+(Integer.parseInt(month)-1)+"31";//上期末
      }
      String curTime1=queryTime+"01";//本期1
      String curTime2=queryTime+"31";//本期2
      
      returnList=incrementInAndOutDAO.findCollectionuseinfo(securityInfo,officeCode,collectionBankId,curTime1,curTime2,lastTime);
      
      /* 106本期实缴存额 */ 
      returnList.add(((BigDecimal)returnList.get(5)).add((BigDecimal)returnList.get(6)).add((BigDecimal)returnList.get(7)));//21
      /*107本期末累计个人提取额*/
      returnList.add(((BigDecimal)returnList.get(8)).add((BigDecimal)returnList.get(9)));//22
      /*109本期末缴存总额*/
      returnList.add(((BigDecimal)returnList.get(4)).add((BigDecimal)returnList.get(21)).add((BigDecimal)returnList.get(9)));//23
      /*110本期末缴存余额*/
      returnList.add(((BigDecimal)returnList.get(4)).add((BigDecimal)returnList.get(21)).subtract((BigDecimal)returnList.get(22)));//24
      /*305本期末贷款余额*/
      returnList.add(((BigDecimal)returnList.get(12)).add((BigDecimal)returnList.get(13)).subtract((BigDecimal)returnList.get(14)));//25
      returnList.add(new BigDecimal(53.04));//26
      returnList.add(new BigDecimal(0));//27
      if(((BigDecimal)returnList.get(20)).compareTo(new BigDecimal(0))==0){
        returnList.add(new BigDecimal(0).multiply(new BigDecimal(100)));
        returnList.add(new BigDecimal(0).multiply(new BigDecimal(100)));
      }else{
        returnList.add(((BigDecimal)returnList.get(0)).divide((BigDecimal)returnList.get(20),4, BigDecimal.ROUND_CEILING).multiply(new BigDecimal(100)));//28
        returnList.add(((BigDecimal)returnList.get(1)).divide((BigDecimal)returnList.get(20),4, BigDecimal.ROUND_CEILING).multiply(new BigDecimal(100)));//29
      }
      List aa400List=new ArrayList();
      List addList=new ArrayList();
      List subList=new ArrayList();
      //本年业务收入---开始
      aa400List=incrementInAndOutDAO.findAA400List();
      if(aa400List.size()==0){
        throw new BusinessException(" 未进行参数设置不可查询数据！！");
      }
      String code_201=aa400List.get(0).toString().trim();
      String Ncode_201[]=code_201.split("\\+");
      for(int i=0;i<Ncode_201.length;i++){
        if(Ncode_201[i].indexOf("-")>0){
          String Mcode_201[]=Ncode_201[i].split("\\-");
          addList.add(Mcode_201[0]);
          for(int j=1;j<Mcode_201.length;j++){
            subList.add(Mcode_201[j]);
          }
        }else{
          addList.add(Ncode_201[i]);
        }
      }
      BigDecimal shouru=new BigDecimal(0);
      for(int k=0;k<addList.size();k++){
        shouru=shouru.add(new BigDecimal(incrementInAndOutDAO.findYeWuShouRu(curTime1, curTime2, "201", addList.get(k).toString()).get(0).toString()));
      }
      for(int k=0;k<subList.size();k++){
        shouru=shouru.add(new BigDecimal(incrementInAndOutDAO.findYeWuShouRu(curTime1, curTime2, "201", subList.get(k).toString()).get(0).toString()));
      }
      returnList.add(shouru);
      //本年业务收入---完成
//    本年业务支出---开始
      aa400List=new ArrayList();
      addList=new ArrayList();
      subList=new ArrayList();
      aa400List=incrementInAndOutDAO.findAA400List();
      if(aa400List.size()==0){
        throw new BusinessException("未进行参数设置不可查询数据！！");
      }
      String code_202=aa400List.get(1).toString().trim();
      String Ncode_202[]=code_202.split("\\+");
      for(int i=0;i<Ncode_202.length;i++){
        if(Ncode_202[i].indexOf("-")>0){
          String Mcode_202[]=Ncode_202[i].split("\\-");
          addList.add(Mcode_202[0]);
          for(int j=1;j<Mcode_202.length;j++){
            subList.add(Mcode_202[j]);
          }
        }else{
          addList.add(Ncode_202[i]);
        }
      }
      BigDecimal zhichu=new BigDecimal(0);
      for(int k=0;k<addList.size();k++){
        zhichu=zhichu.add(new BigDecimal(incrementInAndOutDAO.findYeWuZhiChu(curTime1, curTime2, "202", addList.get(k).toString()).get(0).toString()));
      }
      for(int k=0;k<subList.size();k++){
        zhichu=zhichu.subtract(new BigDecimal(incrementInAndOutDAO.findYeWuZhiChu(curTime1, curTime2, "202", subList.get(k).toString()).get(0).toString()));
      }
      returnList.add(zhichu);
      //本年业务支出---完成
      //本年增值收益
      returnList.add(shouru.subtract(zhichu));
      //本年提取管理费用---开始
      aa400List=new ArrayList();
      addList=new ArrayList();
      subList=new ArrayList();
      aa400List=incrementInAndOutDAO.findAA400List();
      String code_204=aa400List.get(3).toString().trim();
      String Ncode_204[]=code_204.split("\\+");
      for(int i=0;i<Ncode_204.length;i++){
        if(Ncode_204[i].indexOf("-")>0){
          String Mcode_204[]=Ncode_204[i].split("\\-");
          addList.add(Mcode_204[0]);
          for(int j=1;j<Mcode_204.length;j++){
            subList.add(Mcode_204[j]);
          }
        }else{
          addList.add(Ncode_204[i]);
        }
      }
      BigDecimal feiyong=new BigDecimal(0);
      for(int k=0;k<addList.size();k++){
        feiyong=feiyong.add(new BigDecimal(incrementInAndOutDAO.findFeiYong(curTime1, curTime2, "204", addList.get(k).toString()).get(0).toString()));
      }
      for(int k=0;k<subList.size();k++){
        feiyong=feiyong.subtract(new BigDecimal(incrementInAndOutDAO.findFeiYong(curTime1, curTime2, "204", subList.get(k).toString()).get(0).toString()));
      }
      returnList.add(feiyong);
      //本年提取管理费用---完成
      
      //本年末风险准备金总额---开始
      aa400List=new ArrayList();
      addList=new ArrayList();
      subList=new ArrayList();
      aa400List=incrementInAndOutDAO.findAA400List();
      String code_205=aa400List.get(4).toString().trim();
      String Ncode_205[]=code_205.split("\\+");
      for(int i=0;i<Ncode_205.length;i++){
        if(Ncode_205[i].indexOf("-")>0){
          String Mcode_205[]=Ncode_205[i].split("\\-");
          addList.add(Mcode_205[0]);
          for(int j=1;j<Mcode_205.length;j++){
            subList.add(Mcode_205[j]);
          }
        }else{
          addList.add(Ncode_205[i]);
        }
      }
      BigDecimal fengxianAll=new BigDecimal(0);
      for(int k=0;k<addList.size();k++){
        fengxianAll=fengxianAll.add(new BigDecimal(incrementInAndOutDAO.findFengXianAll(curTime1, curTime2, "205", addList.get(k).toString()).get(0).toString()));
      }
      for(int k=0;k<subList.size();k++){
        fengxianAll=fengxianAll.subtract(new BigDecimal(incrementInAndOutDAO.findFengXianAll(curTime1, curTime2, "205", subList.get(k).toString()).get(0).toString()));
      }
      returnList.add(fengxianAll);
      //本年末风险准备金总额---完成
      //本年末风险准备金余额---开始
      aa400List=new ArrayList();
      addList=new ArrayList();
      subList=new ArrayList();
      aa400List=incrementInAndOutDAO.findAA400List();
      String code_206=aa400List.get(5).toString().trim();
      String Ncode_206[]=code_206.split("\\+");
      for(int i=0;i<Ncode_206.length;i++){
        if(Ncode_206[i].indexOf("-")>0){
          String Mcode_206[]=Ncode_206[i].split("\\-");
          addList.add(Mcode_206[0]);
          for(int j=1;j<Mcode_206.length;j++){
            subList.add(Mcode_206[j]);
          }
        }else{
          addList.add(Ncode_206[i]);
        }
      }
      BigDecimal fengxianYu=new BigDecimal(0);
      for(int k=0;k<addList.size();k++){
        fengxianYu=fengxianYu.add(new BigDecimal(incrementInAndOutDAO.findFengXianYU(curTime1, curTime2, "206", addList.get(k).toString()).get(0).toString()));
      }
      for(int k=0;k<subList.size();k++){
        fengxianYu=fengxianYu.subtract(new BigDecimal(incrementInAndOutDAO.findFengXianYU(curTime1, curTime2, "206", subList.get(k).toString()).get(0).toString()));
      }
      returnList.add(fengxianYu);
      //本年末风险准备金余额---完成
      
      //本年末划转廉租住房补充资金---开始
      aa400List=new ArrayList();
      addList=new ArrayList();
      subList=new ArrayList();
      aa400List=incrementInAndOutDAO.findAA400List();
      String code_207=aa400List.get(6).toString().trim();
      String Ncode_207[]=code_207.split("\\+");
      for(int i=0;i<Ncode_207.length;i++){
        if(Ncode_207[i].indexOf("-")>0){
          String Mcode_207[]=Ncode_207[i].split("\\-");
          addList.add(Mcode_207[0]);
          for(int j=1;j<Mcode_207.length;j++){
            subList.add(Mcode_207[j]);
          }
        }else{
          addList.add(Ncode_207[i]);
        }
      }
      BigDecimal lianzufang=new BigDecimal(0);
      for(int k=0;k<addList.size();k++){
        lianzufang=lianzufang.add(new BigDecimal(incrementInAndOutDAO.findFeiYong(curTime1, curTime2, "207", addList.get(k).toString()).get(0).toString()));
      }
      for(int k=0;k<subList.size();k++){
        lianzufang=lianzufang.subtract(new BigDecimal(incrementInAndOutDAO.findFeiYong(curTime1, curTime2, "207", subList.get(k).toString()).get(0).toString()));
      }
      returnList.add(lianzufang);
      //本年末划转廉租住房补充资金---完成
      
      //本年末廉租住房补充资金总额---开始
      aa400List=new ArrayList();
      addList=new ArrayList();
      subList=new ArrayList();
      aa400List=incrementInAndOutDAO.findAA400List();
      String code_208=aa400List.get(7).toString().trim();
      String Ncode_208[]=code_208.split("\\+");
      for(int i=0;i<Ncode_208.length;i++){
        if(Ncode_208[i].indexOf("-")>0){
          String Mcode_208[]=Ncode_208[i].split("\\-");
          addList.add(Mcode_208[0]);
          for(int j=1;j<Mcode_208.length;j++){
            subList.add(Mcode_208[j]);
          }
        }else{
          addList.add(Ncode_208[i]);
        }
      }
      BigDecimal buchong=new BigDecimal(0);
      for(int k=0;k<addList.size();k++){
        buchong=buchong.add(new BigDecimal(incrementInAndOutDAO.findFengXianAll(curTime1, curTime2, "208", addList.get(k).toString()).get(0).toString()));
      }
      for(int k=0;k<subList.size();k++){
        buchong=buchong.subtract(new BigDecimal(incrementInAndOutDAO.findFengXianAll(curTime1, curTime2, "208", subList.get(k).toString()).get(0).toString()));
      }
      returnList.add(buchong);
      //本年末廉租住房补充资金总额---完成
      
      //本年末廉租住房补充资金余额---开始
      aa400List=new ArrayList();
      addList=new ArrayList();
      subList=new ArrayList();
      aa400List=incrementInAndOutDAO.findAA400List();
      String code_209=aa400List.get(8).toString().trim();
      String Ncode_209[]=code_209.split("\\+");
      for(int i=0;i<Ncode_209.length;i++){
        if(Ncode_209[i].indexOf("-")>0){
          String Mcode_209[]=Ncode_209[i].split("\\-");
          addList.add(Mcode_209[0]);
          for(int j=1;j<Mcode_209.length;j++){
            subList.add(Mcode_209[j]);
          }
        }else{
          addList.add(Ncode_209[i]);
        }
      }
      BigDecimal lianzuyu=new BigDecimal(0);
      for(int k=0;k<addList.size();k++){
        lianzuyu=lianzuyu.add(new BigDecimal(incrementInAndOutDAO.findFengXianYU(curTime1, curTime2, "209", addList.get(k).toString()).get(0).toString()));
      }
      for(int k=0;k<subList.size();k++){
        lianzuyu=lianzuyu.subtract(new BigDecimal(incrementInAndOutDAO.findFengXianYU(curTime1, curTime2, "209", subList.get(k).toString()).get(0).toString()));
      }
      returnList.add(lianzuyu);
      //本年末廉租住房补充资金余额---完成
      //加时间
      returnList.add(year);
      returnList.add(month);
      
      SimpleDateFormat s=new SimpleDateFormat("yyyyMMdd");
      String date=s.format(new Date());
      String date1=date.substring(0,4) + "年" +date.substring(4,6)+"月"+date.substring(6,8)+"日";
      returnList.add(date1);
      
     
    } catch (BusinessException bex) {
      bex.printStackTrace();
      throw bex;
    } catch (Exception e) {
      e.printStackTrace();
    }
    return returnList;
  }

  public List findAccountList()  throws Exception{
    List accountList=new ArrayList();
    accountList=incrementInAndOutDAO.findAccountList();
    return accountList;
  }

  public List findAA400List()  throws Exception{
    List aa400List=new ArrayList();
    aa400List=incrementInAndOutDAO.findAA400List();
    return aa400List;
  }

  
  public void saveCollectionuseParamInfo(DefineFormulaDto defineFormulaDto,SecurityInfo securityInfo) throws Exception {
    //删除 AA004 表
    incrementInAndOutDAO.deleteAA004Param();
    //插入 AA004 表
    IncrementInAndOut incrementInAndOut=new IncrementInAndOut();
    incrementInAndOut.setSubjectCode("201");
    incrementInAndOut.setBookId(defineFormulaDto.getBOOK_ID());
    incrementInAndOut.setCode(defineFormulaDto.getCode_201());
    incrementInAndOutDAO.insert(incrementInAndOut);
    
    incrementInAndOut=new IncrementInAndOut();
    incrementInAndOut.setSubjectCode("202");
    incrementInAndOut.setBookId(defineFormulaDto.getBOOK_ID());
    incrementInAndOut.setCode(defineFormulaDto.getCode_202());
    incrementInAndOutDAO.insert(incrementInAndOut);
    
    incrementInAndOut=new IncrementInAndOut();
    incrementInAndOut.setSubjectCode("203");
    incrementInAndOut.setBookId(defineFormulaDto.getBOOK_ID());
    incrementInAndOut.setCode(defineFormulaDto.getCode_203());
    incrementInAndOutDAO.insert(incrementInAndOut);
    
    incrementInAndOut=new IncrementInAndOut();
    incrementInAndOut.setSubjectCode("204");
    incrementInAndOut.setBookId(defineFormulaDto.getBOOK_ID());
    incrementInAndOut.setCode(defineFormulaDto.getCode_204());
    incrementInAndOutDAO.insert(incrementInAndOut);
    
    incrementInAndOut=new IncrementInAndOut();
    incrementInAndOut.setSubjectCode("205");
    incrementInAndOut.setBookId(defineFormulaDto.getBOOK_ID());
    incrementInAndOut.setCode(defineFormulaDto.getCode_205());
    incrementInAndOutDAO.insert(incrementInAndOut);
    
    incrementInAndOut=new IncrementInAndOut();
    incrementInAndOut.setSubjectCode("206");
    incrementInAndOut.setBookId(defineFormulaDto.getBOOK_ID());
    incrementInAndOut.setCode(defineFormulaDto.getCode_206());
    incrementInAndOutDAO.insert(incrementInAndOut);
    
    incrementInAndOut=new IncrementInAndOut();
    incrementInAndOut.setSubjectCode("207");
    incrementInAndOut.setBookId(defineFormulaDto.getBOOK_ID());
    incrementInAndOut.setCode(defineFormulaDto.getCode_207());
    incrementInAndOutDAO.insert(incrementInAndOut);
    
    incrementInAndOut=new IncrementInAndOut();
    incrementInAndOut.setSubjectCode("208");
    incrementInAndOut.setBookId(defineFormulaDto.getBOOK_ID());
    incrementInAndOut.setCode(defineFormulaDto.getCode_208());
    incrementInAndOutDAO.insert(incrementInAndOut);
    
    incrementInAndOut=new IncrementInAndOut();
    incrementInAndOut.setSubjectCode("209");
    incrementInAndOut.setBookId(defineFormulaDto.getBOOK_ID());
    incrementInAndOut.setCode(defineFormulaDto.getCode_209());
    incrementInAndOutDAO.insert(incrementInAndOut);
  }

  public String is_CodeIn_WL(String code, String states, SecurityInfo securityInfo) throws BusinessException {
    String code1="";
    code1=incrementInAndOutDAO.is_CodeIn_WL(code, states, securityInfo);
    return code1;
  }

}

