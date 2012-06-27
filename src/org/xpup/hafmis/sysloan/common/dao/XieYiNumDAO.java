package org.xpup.hafmis.sysloan.common.dao;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Iterator;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import org.apache.commons.lang.Validate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.xpup.hafmis.common.util.BusiConst;
import org.xpup.hafmis.common.util.BusiTools;
import org.xpup.hafmis.sysloan.common.domain.entity.SpecialBorrower;
import org.xpup.hafmis.sysloan.common.domain.entity.XieYiNum;
import org.xpup.hafmis.sysloan.loanapply.specialapply.dto.SpecialapplyDTO;
import org.xpup.hafmis.sysloan.loanapply.specialapply.dto.SpecialapplyInfoDTO;
import org.xpup.hafmis.sysloan.loanapply.specialapply.dto.SpecialapplyNewDTO;

/**
 * 协议号表
 * 
 * @author wsh 2007-9-13
 */
public class XieYiNumDAO extends HibernateDaoSupport {
  /**
   * 根据主键查询
   * 
   * @param id
   * @return
   */
  public XieYiNum queryById(Serializable id) {
    Validate.notNull(id);
    return (XieYiNum) getHibernateTemplate().get(XieYiNum.class,
        id);
  }
  

  

  

  /**
   * 插入记录
   * 
   * @param specialBorrower
   * @return
   */
  public Serializable insert(XieYiNum xieYiNum) {
    Serializable id = null;
    try {
      Validate.notNull(xieYiNum);
      id = getHibernateTemplate().save(xieYiNum);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return id;
  }


}
