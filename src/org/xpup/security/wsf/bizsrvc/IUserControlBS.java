package org.xpup.security.wsf.bizsrvc;

import java.io.Serializable;
import java.util.List;

import net.sf.acegisecurity.UserDetails;

import org.xpup.common.exception.BusinessException;
import org.xpup.hafmis.orgstrct.domain.HafEmployee;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.security.common.domain.User;

public interface IUserControlBS {

  public String encodedPassword(String password, UserDetails user);

  public void removeUserFromCache(String username);

  public void assignRolesToUser(Serializable userId, List roleIds)
      throws BusinessException;

  public void changePassword(String username, String oldPassword,
      String newPassword) throws BusinessException;

  public void changePassword(String username, String newPassword)
      throws BusinessException;

  public User findUserbyUsername(String username) throws BusinessException;
  
  public boolean checkLogin(String userName,String userPassword);
  
  public HafEmployee getHafEmployee(final String username, final String userIp) throws Exception;
  
  public SecurityInfo getSecurityInfo(final String username,final String userIp) throws Exception;
  
  public void updateBizDate(final String username,final String opSystemType,final String bizDate) throws BusinessException;

  public User outUser(String username,String password)throws BusinessException;

  public String getUserBookName(final String bookId);
  
  public void changMonth(final String username,final String opSystemType,final String bizDate,SecurityInfo securityInfo) throws BusinessException;
  public void updateBizDate_jj(String[] rowArray,SecurityInfo securityInfo)throws Exception,BusinessException;
  public void updateBizDateMonth_jj(String[] rowArray)throws Exception,BusinessException;
  public String checkClear(String[] rowArray,SecurityInfo securityInfo)throws Exception,BusinessException;
  public void updatePLBizDate_jj(String[] rowArray)throws Exception,BusinessException;
  public void updatePLBizDateMonth_jj(String[] rowArray)throws Exception,BusinessException;
  
  public String getFnBizDate(String userName, String bookId);
  // 得到分配给用户的账套
  public List getUserBookList(String username);
  // 财务月结
  public void updateFBizDate(String bookId, String bizDate) throws BusinessException;
}
