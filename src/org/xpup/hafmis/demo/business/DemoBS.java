package org.xpup.hafmis.demo.business;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.beanutils.BeanUtils;
import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.Pagination;
import org.xpup.common.util.imp.rule.UtilRule;
import org.xpup.hafmis.common.util.BusiConst;
import org.xpup.hafmis.common.util.BusiTools;
import org.xpup.hafmis.demo.bsinterface.IDemoBS;
import org.xpup.hafmis.demo.dao.DemoDAO;
import org.xpup.hafmis.demo.dao.DemoDwDAO;
import org.xpup.hafmis.demo.domain.entity.Demo;
import org.xpup.hafmis.demo.dto.DemoImportDTO;

/**
 * 
 * @author 刘洋
 *2007-5-31
 */
public class DemoBS implements IDemoBS{

  private DemoDAO demoDAO = null;
  private DemoDwDAO demoDwDAO = null;
  
  public void setDemoDwDAO(DemoDwDAO demoDwDAO) {
    this.demoDwDAO = demoDwDAO;
  }

  public void setDemoDAO(DemoDAO demoDAO) {
    this.demoDAO = demoDAO;
  }
  
  /**
   * 根据条件查询demo记录（导入）
   * @param pagination
   * @return
   * @throws BusinessException 
   */    
  public List modifyDemoBatch(List demoExportList) throws Exception,BusinessException
  {
    List demoList=new ArrayList();
    try{
      for(int i=0;i<demoExportList.size();i++){
        DemoImportDTO demoImportDto=(DemoImportDTO)demoExportList.get(i);
        Demo demo=new Demo();//update的时候可以注释掉
//      Demo demo=demoDAO.queryById(new Integer(demoImportDto.getId()));
        UtilRule utilRule=new UtilRule();
        if(utilRule.moneyRule(demoImportDto.getSalary(), 2, 2)==false){
//          BeanUtils.copyProperties(demo, demoImportDto);
//          demoList.add(demo);
//          continue;
        }else if(BusiTools.getBusiKey(demoImportDto.getSex(),BusiConst.SEX)==999){
          BeanUtils.copyProperties(demo, demoImportDto);
          demoList.add(demo);
          continue;
        }
        demo.setId(new Integer(demoImportDto.getId()));//update的时候可以注释掉
        demo.setName(demoImportDto.getName());
        demo.setIdcard(demoImportDto.getIdcard());
        demo.setBirthday(demoImportDto.getBirthday());
        demo.setSalary(new BigDecimal(demoImportDto.getSalary()));
        demo.setSex(""+BusiTools.getBusiKey(demoImportDto.getSex(),BusiConst.SEX));
        demoDAO.insert(demo);//update的时候可以注释掉
      }
    }catch(Exception e){
      e.printStackTrace();
      throw new BusinessException("修改失败!");
    }
    return demoList;
  }
  /**
   * 根据条件查询demo记录
   * @param pagination
   * @return
   * @throws BusinessException 
   */  
  public List findDemoListByCriterions(Pagination pagination) throws Exception,BusinessException{
    List list=new ArrayList();
    
    String id=(String) pagination.getQueryCriterions().get("demo.id");
    String name=(String) pagination.getQueryCriterions().get("demo.name");
    
    String orderBy=(String) pagination.getOrderBy();
    String order = (String) pagination.getOrderother(); 
    int start = pagination.getFirstElementOnPage() - 1;
    int pageSize = pagination.getPageSize(); 
    int page = pagination.getPage(); 
    
    list=demoDAO.queryDemoListByCriterions(id, name, orderBy, order, start, pageSize,page);
    
    //转换男女
    List returnlist=new ArrayList();   
    if(list!=null){
    for(int i=0;i<list.size();i++){
        Demo demo=(Demo)list.get(i);  
        demo.setSex(BusiTools.getBusiValue(Integer.parseInt(demo.getSex()), BusiConst.SEX));
        returnlist.add(demo);
      }
    }
    int count = demoDAO.queryDemoCountByCriterions(id, name);
    pagination.setNrOfElements(count);
//    if(list.size()==0){
//      throw new BusinessException("不存在记录");
//    }
    return returnlist;
    
   }
  
  /**
   * 根据条件查询demo记录返回所有记录(导出使用)
   * @param pagination
   * @return
   * @throws BusinessException 
   */  
  public List findDemoListAll(Pagination pagination) throws Exception,BusinessException{
    List list=new ArrayList();
    String id=(String) pagination.getQueryCriterions().get("demo.id");
    String name=(String) pagination.getQueryCriterions().get("demo.name");
    String orderBy=(String) pagination.getOrderBy();;
    String order = (String) pagination.getOrderother(); 
    int start = pagination.getFirstElementOnPage() - 1;
    int pageSize = pagination.getPageSize(); 
    list=demoDAO.queryDemoList(id, name, orderBy, order, start, pageSize);
    //转换男女
    List returnlist=new ArrayList();   
    if(list!=null){
    for(int i=0;i<list.size();i++){
      Demo demo=(Demo)list.get(i);  
      demo.setSex(BusiTools.getBusiValue(Integer.parseInt(demo.getSex()), BusiConst.SEX));
      returnlist.add(demo);
    }
    }
    int count = demoDAO.queryDemoCountByCriterions(id, name);
    pagination.setNrOfElements(count);
//    if(list.size()==0){
//      throw new BusinessException("不存在记录");
//    }
    return returnlist;
    
   }
  
 /**
  * 根据id查询demo记录 
  */
  public Demo findDemoById(Integer id){
    return demoDAO.queryById(id);
  }
  
  /**
   * @throws BusinessException 
   * 添加
   */
  public Integer addDemo(Demo demo) throws BusinessException{
    Integer id=null;
    try{
    demoDAO.insert(demo);
    id=demo.getId();
    }catch(Exception e){
      throw new BusinessException("添加失败!");
      
    }
    return id;
  }
  /**
   * 修改
   * @param demo
   * @throws BusinessException
   */
  public void updateDemo(Demo demo) throws BusinessException{
    try{
      Demo modifyDemo = findDemoById(demo.getId());
      modifyDemo.setBalance(demo.getBalance());
      modifyDemo.setBirthday(demo.getBirthday());
      modifyDemo.setIdcard(demo.getIdcard());
      modifyDemo.setName(demo.getName());
      modifyDemo.setSalary(demo.getSalary());
      modifyDemo.setSex(demo.getSex());
      modifyDemo.setPhotoUrl(demo.getPhotoUrl());
    }catch(Exception e){
      e.printStackTrace();
      throw new BusinessException("修改失败!");
    }
    
  }
  /**
   * 单选删除
   */
  public void deleteDemo(Integer id) throws BusinessException{
    try{
      Demo demo=demoDAO.queryById(id);
      demoDAO.delete(demo);
    }catch(Exception e){
      throw new BusinessException("删除失败!");
    }
  }
  /**
   * 复选删除
   */
  public void deleteDemoMultibox(String[] rowArray) throws BusinessException{
    try{
      for(int i=0;i<rowArray.length;i++){
        Integer id=new Integer(rowArray[i]);
        Demo demo=demoDAO.queryById(id);
        demoDAO.delete(demo);
      }
     
    }catch(Exception e){
      throw new BusinessException("删除失败!");
    }
  }
  
  /**
   * 删除当前页
   */
  public void deletePageList(List list) throws BusinessException{
    try{
//      List deleteList=new ArrayList();
//      for(int i=0;i<list.size();i++){
//        DemoDTO demoDTO=(DemoDTO)list.get(i);
//        Demo demo=new Demo();
//        BeanUtils.copyProperties(demo, demoDTO);   
//        deleteList.add(demo);
//      }
      demoDAO.deleteList(list);
    }catch(Exception e){
      e.printStackTrace();
      throw new BusinessException("删除失败!");
    }
  }
//  public static void main(String[] args) {
//    try{
//      System.out.println("sss");
//      System.out.println("-->"+test.getDay("20070730"));
//    }catch(Exception s){
//      System.out.println("date appear error:"+s.getMessage());
//      s.printStackTrace();
//    }
//  }
}

//计算结息日
//class test{
//  public static int getDay(String moneyDate){
//    java.sql.Date date = new java.sql.Date(new java.util.Date().getTime());
//    String year = moneyDate.substring(0,4);
//    String month = moneyDate.substring(4,6);
//    String day = moneyDate.substring(6,8);
//    Calendar convert = Calendar.getInstance();
//    Calendar result = Calendar.getInstance();
//    convert.set(Integer.parseInt(year),Integer.parseInt(month)-1,Integer.parseInt(day));
//    //设置为本年的6月30日
//    result.set(Integer.parseInt(date.toString().substring(0,4)), 05,30);
//    if(convert.getTime().getTime()> result.getTime().getTime()){//设置为明年的
//      result.set(Integer.parseInt(date.toString().substring(0,4))+1, 05,30);
//    }
//    Timestamp one = new Timestamp(result.getTime().getTime());
//    Timestamp two = new Timestamp(convert.getTime().getTime());
//    int number = BusiTools.minusDate(one.toString().substring(0,10),two.toString().substring(0,10));
//    return number;
//  }
//  
//}
