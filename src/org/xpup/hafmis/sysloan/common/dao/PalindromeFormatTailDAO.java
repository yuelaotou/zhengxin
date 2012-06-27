package org.xpup.hafmis.sysloan.common.dao;

import java.io.Serializable;

import org.apache.commons.lang.Validate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.xpup.hafmis.sysloan.common.domain.entity.PalindromeFormatTail;
/** 
 * PL011 回文格式对应设置尾表
 * @author Administrator
 *
 */
public class PalindromeFormatTailDAO extends HibernateDaoSupport{
  /**
   * 根据主键查询
   * 
   * @param id
   * @return
   */
  public PalindromeFormatTail queryById(Serializable id) {
    Validate.notNull(id);
    return (PalindromeFormatTail) getHibernateTemplate().get(PalindromeFormatTail.class, id);
  }
  /**
   * 插入记录
   * 
   * @param assistantOrg
   * @return
   */
  public Serializable insert(PalindromeFormatTail palindromeFormatTail) {
    Serializable id = null;
    try {
      Validate.notNull(palindromeFormatTail);
      id = getHibernateTemplate().save(palindromeFormatTail);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return id;
  }
  /**
   * 删除
   */
  public void delete(PalindromeFormatTail palindromeFormatTail){
    try{
      getHibernateTemplate().delete(palindromeFormatTail);
    }catch(Exception a){
      a.printStackTrace();
    }
  }
}
