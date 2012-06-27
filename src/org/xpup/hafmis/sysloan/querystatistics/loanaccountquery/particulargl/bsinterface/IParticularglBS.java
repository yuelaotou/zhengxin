package org.xpup.hafmis.sysloan.querystatistics.loanaccountquery.particulargl.bsinterface;

import java.util.List;

import org.xpup.common.util.Pagination;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysloan.querystatistics.loanaccountquery.particulargl.form.ParticularglTaAF;

public interface IParticularglBS {

  ParticularglTaAF showparticularglList(Pagination pagination, SecurityInfo securityInfo) throws Exception;

  ParticularglTaAF showparticularglListTb(Pagination pagination, Pagination paginationta, SecurityInfo securityInfo)throws Exception;

  ParticularglTaAF showparticularglListTc(Pagination pagination, Pagination paginationta, SecurityInfo securityInfo)throws Exception;
  
  public ParticularglTaAF showparticularglListTd(Pagination pagination, Pagination paginationta, SecurityInfo securityInfo) throws Exception;
  public ParticularglTaAF showparticularglListTe(Pagination pagination, Pagination paginationta, SecurityInfo securityInfo) throws Exception;
  public List changePrintList(List list) throws Exception; 

}
