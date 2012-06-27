package org.xpup.hafmis.sysfinance.reportmng.financereport.createreport.business;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.common.util.BusiConst;
import org.xpup.hafmis.common.util.bizlog.BusiLogConst;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysfinance.common.dao.FnOperateLogDAO;
import org.xpup.hafmis.sysfinance.common.dao.ReportMngDAO;
import org.xpup.hafmis.sysfinance.common.domain.entity.FnOperateLog;
import org.xpup.hafmis.sysfinance.common.domain.entity.ReportMng;
import org.xpup.hafmis.sysfinance.reportmng.financereport.createreport.bsinterface.ICreateReportBS;
import org.xpup.hafmis.sysfinance.reportmng.financereport.createreport.dto.ReportMngDTO;
import org.xpup.security.common.dao.MenuItemDAO;
import org.xpup.security.common.dao.MuRelationDAO;
import org.xpup.security.common.domain.MenuItem;
import org.xpup.security.common.domain.MuRelation;

public class CreateReportBS implements ICreateReportBS {

  private ReportMngDAO reportMngDAO = null;

  private FnOperateLogDAO fnOperateLogDAO = null;

  private MenuItemDAO menuItemDAO = null;

  private MuRelationDAO muRelationDAO = null;


  public void setReportMngDAO(ReportMngDAO reportMngDAO) {
    this.reportMngDAO = reportMngDAO;
  }

  public void setFnOperateLogDAO(FnOperateLogDAO fnOperateLogDAO) {
    this.fnOperateLogDAO = fnOperateLogDAO;
  }

  public void setMenuItemDAO(MenuItemDAO menuItemDAO) {
    this.menuItemDAO = menuItemDAO;
  }

  public void setMuRelationDAO(MuRelationDAO muRelationDAO) {
    this.muRelationDAO = muRelationDAO;
  }

  /**
   * 根据条件查询创建报表的列表
   */
  public List findcreateReportMngList(Pagination pagination,
      SecurityInfo securityInfo) throws Exception, BusinessException {
    List list = null;
    try {
      list = new ArrayList();

      String tablenamequery = (String) pagination.getQueryCriterions().get(
          "tablenamequery");
      String createtimestart = (String) pagination.getQueryCriterions().get(
          "createtimestart");
      String createtimeend = (String) pagination.getQueryCriterions().get(
          "createtimeend");
      String orderBy = (String) pagination.getOrderBy();
      String order = (String) pagination.getOrderother();
      int start = pagination.getFirstElementOnPage() - 1;
      int pageSize = pagination.getPageSize();
      int page = pagination.getPage();

      list = reportMngDAO.findcreateReportMngList_WL(tablenamequery,
          createtimestart, createtimeend, orderBy, order, start, pageSize,
          page, securityInfo);
      ReportMngDTO reportMngDTO = null;
      for (int i = 0; i < list.size(); i++) {
        // 转换
        reportMngDTO = (ReportMngDTO) list.get(i);
        reportMngDTO.setCreatetime(reportMngDTO.getCreatetime()
            .substring(0, 11));
      }

      int count = reportMngDAO.findcreateReportMngListCount_WL(tablenamequery,
          createtimestart, createtimeend, securityInfo);
      pagination.setNrOfElements(count);

    } catch (BusinessException bex) {
      throw bex;
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }

  /**
   * 根据ID查询报表头信息
   */
  public ReportMng queryCreateReportMngById(Pagination pagination,
      SecurityInfo securityInfo) throws Exception, BusinessException {
    ReportMng reportMng = null;
    try {
      String modifyid = (String) pagination.getQueryCriterions()
          .get("modifyid");
      reportMng = reportMngDAO.queryById(new Integer(modifyid));
    } catch (Exception e) {
      e.printStackTrace();
    }
    return reportMng;
  }

  /**
   * 新增报表信息
   */
  public void insertCreateReportMng(ReportMngDTO reportMngDTO,
      SecurityInfo securityInfo) throws Exception, BusinessException {
    try {
      String id = reportMngDTO.getId();
      if (!(id == null || "".equals(id))) {// 是由“修改”过来的确定
        // 更新各表数据

        // FN400
        ReportMng reportMng = reportMngDAO.queryById(new Integer(id));
        reportMng.setTailtableNameChinese(reportMngDTO
            .getTailtableNameChinese());
        reportMng.setColspan(reportMngDTO.getColspan());
        reportMng.setRowspan(reportMngDTO.getRowspan());
        reportMngDAO.update(reportMng);

        // 尾表
        reportMngDAO.createTailTable_WL(reportMng, "1");

        // CA104：生成两条记录
        String[] urlid = new String[2];
        urlid = reportMng.getUrlid().split(",");
        MenuItem menuItem1 = menuItemDAO.getMenuItemById_WL(urlid[0]);
        menuItem1.setCaption(reportMngDTO.getTailtableNameChinese()
            .concat("定义"));
        MenuItem menuItem2 = menuItemDAO.getMenuItemById_WL(urlid[1]);
        menuItem2.setCaption(reportMngDTO.getTailtableNameChinese()
            .concat("查询"));

        // FN311：操作日志表
        FnOperateLog fnOperateLog = new FnOperateLog();
        fnOperateLog.setBookId(securityInfo.getBookId());
        fnOperateLog.setOpSys("" + BusiLogConst.OP_SYSTEM_TYPE_FINANCE);
        fnOperateLog.setOpModel(""
            + BusiLogConst.FN_OP_REPORTMNG_FINANCEREPORT_CREATEREPORT);
        fnOperateLog.setOpButton("" + BusiLogConst.BIZLOG_ACTION_MODIFY);
        fnOperateLog.setOpIp(securityInfo.getUserInfo().getUserIp());
        fnOperateLog.setOpTime(new Date());
        fnOperateLog.setOperator(securityInfo.getUserInfo().getUsername());
        fnOperateLogDAO.insert(fnOperateLog);

      } else {
        // 插入各表数据

        // FN400
        ReportMng reportMng = new ReportMng();
        reportMng.setBookId(securityInfo.getBookId());
        reportMng.setTailtableName("临时");
        reportMng.setTailtableNameChinese(reportMngDTO
            .getTailtableNameChinese());
        reportMng.setColspan(reportMngDTO.getColspan());
        reportMng.setRowspan(reportMngDTO.getRowspan());
        reportMng.setQuerymode("" + BusiConst.REPORTQUERYMANNER_MONTH);
        reportMng.setUrlid("临时");
        reportMng.setCreatetime(new Date());
        reportMng.setCreateperson(securityInfo.getUserInfo().getUsername());
        String seq400 = reportMngDAO.insert(reportMng).toString();
        ReportMng reportMng_up = reportMngDAO.queryById(new Integer(seq400));
        reportMng_up.setTailtableName("FN40".concat(seq400));

        // CA104：生成两条记录
        MenuItem menuItem1 = new MenuItem();

        menuItem1.setCaption(reportMngDTO.getTailtableNameChinese()
            .concat("定义"));
        menuItem1.setUrl("./sysfinance/defineReportForwardURLAC.do?tablename="
            + reportMng.getTailtableName() + "");
        MenuItem menuItem11 = menuItemDAO.getParentMenuItemID_WL();
        menuItem1.setParentMenuItem(menuItem11);
        String position1 = menuItemDAO.getNextPositionValue_WL(menuItem11
            .getId().toString());
        menuItem1.setPosition(Integer.parseInt(position1) + 1);
        menuItem1.setOpSystemType("" + BusiLogConst.OP_SYSTEM_TYPE_FINANCE);
        String menu1id = (String) menuItemDAO.insert(menuItem1);

        MenuItem menuItem2 = new MenuItem();
        menuItem2.setCaption(reportMngDTO.getTailtableNameChinese()
            .concat("查询"));
        menuItem2.setUrl("./sysfinance/queryReportForwardURLAC.do?tablename="
            + reportMng.getTailtableName() + "");
        MenuItem menuItem22 = menuItemDAO.getParentMenuItemID_WL();
        menuItem2.setParentMenuItem(menuItem22);
        String position2 = menuItemDAO.getNextPositionValue_WL(menuItem22
            .getId().toString());
        menuItem2.setPosition(Integer.parseInt(position2) + 1);
        menuItem2.setOpSystemType("" + BusiLogConst.OP_SYSTEM_TYPE_FINANCE);
        String menu2id = (String) menuItemDAO.insert(menuItem2);

        reportMng_up = reportMngDAO.queryById(new Integer(seq400));
        reportMng_up.setTailtableName("FN40".concat(seq400));
        reportMng.setUrlid(menu1id.concat(",").concat(menu2id));

        // 生成的尾表
        reportMngDAO.createTailTable_WL(reportMng, "0");

        // FN311：操作日志表
        FnOperateLog fnOperateLog = new FnOperateLog();
        fnOperateLog.setBookId(securityInfo.getBookId());
        fnOperateLog.setOpSys("" + BusiLogConst.OP_SYSTEM_TYPE_FINANCE);
        fnOperateLog.setOpModel(""
            + BusiLogConst.FN_OP_REPORTMNG_FINANCEREPORT_CREATEREPORT);
        fnOperateLog.setOpButton("" + BusiLogConst.BIZLOG_ACTION_ADD);
        fnOperateLog.setOpIp(securityInfo.getUserInfo().getUserIp());
        fnOperateLog.setOpTime(new Date());
        fnOperateLog.setOperator(securityInfo.getUserInfo().getUsername());
        fnOperateLogDAO.insert(fnOperateLog);

        // 将新增的报表的节点分配给所有具有该账套权限并具有“财务报表”节点的用户
        // 得到“报表管理”和“财务报表”的节点
        MenuItem temp_menuItem = new MenuItem();
        temp_menuItem = menuItemDAO.getParentMenuItemID_WL();
        List menuItemIds = new ArrayList();
        menuItemIds.add(temp_menuItem.getParentMenuItem().getId());
        menuItemIds.add(temp_menuItem.getId());
        menuItemIds.add(menuItem1.getId());
        menuItemIds.add(menuItem2.getId());
        // 得到ca105,ca106中所有有“财务报表”节点且用户账套权限为当前账套的用户IDList (跳转/toindex.jsp)
        List useridList = muRelationDAO.getUserIdByMenuId_WL(temp_menuItem
            .getId().toString(), securityInfo);
        for (int i = 0; i < useridList.size(); i++) {
          String userId = useridList.get(i).toString();

          Iterator it = menuItemIds.iterator();
          while (it.hasNext()) {
            String temp_id = (String) it.next();
            List mus = muRelationDAO.queryByCriterions(temp_id, userId);
            if (mus.size() == 0) {
              MuRelation muRelation = new MuRelation();
              muRelation.getMenuItem().setId(temp_id);
              muRelation.getUser().setId(userId);
              muRelationDAO.insert(muRelation);
            }

            MenuItem item = menuItemDAO.queryById(temp_id);
            while (item.getParentMenuItem() != null) {
              item = item.getParentMenuItem();
              List prs = muRelationDAO.queryByCriterions(item.getId(), userId);
              if (prs.size() > 0) {
                MuRelation pr = (MuRelation) prs.get(0);
                pr.setAuthNumber(pr.getAuthNumber() + 1);
              } else {
                MuRelation pr = new MuRelation();
                pr.getMenuItem().setId(item.getId());
                pr.getUser().setId(userId);
                muRelationDAO.insert(pr);
              }
            }
          }

        }
      }

    } catch (BusinessException bex) {
      throw bex;
    } catch (Exception e) {
      e.printStackTrace();
    }

  }

  /**
   * 根据条件查询创建报表的打印列表
   */
  public List findcreateReportMngAllList(Pagination pagination,
      SecurityInfo securityInfo) throws Exception, BusinessException {
    List list = null;
    try {
      list = new ArrayList();

      String tablenamequery = (String) pagination.getQueryCriterions().get(
          "tablenamequery");
      String createtimestart = (String) pagination.getQueryCriterions().get(
          "createtimestart");
      String createtimeend = (String) pagination.getQueryCriterions().get(
          "createtimeend");

      list = reportMngDAO.findcreateReportMngAllList_WL(tablenamequery,
          createtimestart, createtimeend, securityInfo);
      ReportMngDTO reportMngDTO = null;
      for (int i = 0; i < list.size(); i++) {
        // 转换
        reportMngDTO = (ReportMngDTO) list.get(i);
        reportMngDTO.setCreatetime(reportMngDTO.getCreatetime()
            .substring(0, 11));
      }

    } catch (BusinessException bex) {
      throw bex;
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }

  /**
   * 检查删除报表
   */
  public String checkdeleteReportTable(String reporttableid,
      SecurityInfo securityInfo) throws Exception, BusinessException {
    String flag = "no";
    try {
      // 判断该报表是否存在数据库中，用id关联
      ReportMng reportMng = reportMngDAO.queryById(new Integer(reporttableid));
      if (reportMng == null) {
        throw new BusinessException("该报表已经被删除了!!");
      }
      // 判断该报表是否存在数据
      String tablename = reportMng.getTailtableName();
      int count = reportMngDAO.getReportCol_WL(tablename);
      String row = reportMng.getRowspan();
      for (int i = 1; i < (count + 1); i++) {
        for (int j = 1; j < (Integer.parseInt(row) + 1); j++) {
          String content = reportMngDAO.getContentByColRow_WL(tablename, i, j,
              securityInfo);
          if (!(content == null || content.length() == 0)) {
            flag = "have";
            return flag;
          }
        }
      }

    } catch (BusinessException bex) {
      throw bex;
    } catch (Exception e) {
      e.printStackTrace();
    }
    return flag;

  }

  /**
   * 删除报表
   */
  public void deleteReport(String reporttableid, SecurityInfo securityInfo)
      throws Exception, BusinessException {
    try {
      // 判断该报表是否存在数据库中，用id关联
      ReportMng reportMng = reportMngDAO.queryById(new Integer(reporttableid));
      if (reportMng == null) {
        throw new BusinessException("该报表已经被删除了!!");
      }

      reportMngDAO.deleteReport_WL(reporttableid, reportMng.getUrlid(),
          reportMng.getTailtableName(), "CA106", "CA105");

      // FN311：操作日志表
      FnOperateLog fnOperateLog = new FnOperateLog();
      fnOperateLog.setBookId(securityInfo.getBookId());
      fnOperateLog.setOpSys("" + BusiLogConst.OP_SYSTEM_TYPE_FINANCE);
      fnOperateLog.setOpModel(""
          + BusiLogConst.FN_OP_REPORTMNG_FINANCEREPORT_CREATEREPORT);
      fnOperateLog.setOpButton("" + BusiLogConst.BIZLOG_ACTION_DELETE);
      fnOperateLog.setOpIp(securityInfo.getUserInfo().getUserIp());
      fnOperateLog.setOpTime(new Date());
      fnOperateLog.setOperator(securityInfo.getUserInfo().getUsername());
      fnOperateLogDAO.insert(fnOperateLog);

    } catch (BusinessException bex) {
      throw bex;
    } catch (Exception e) {
      e.printStackTrace();
    }

  }

  /**
   * 检查修改报表
   */
  public String checkModifyReport(String reporttableid, String newcol,
      String newrow, SecurityInfo securityInfo) throws Exception,
      BusinessException {
    String flag = "no";
    try {
      // 判断该报表是否存在数据库中，用id关联
      ReportMng reportMng = reportMngDAO.queryById(new Integer(reporttableid));
      if (reportMng == null) {
        throw new BusinessException("该报表已经被删除了!!");
      }
      // 判断该报表是否存在数据
      String tablename = reportMng.getTailtableName();
      int count = reportMngDAO.getReportCol_WL(tablename);// col
      String row = reportMng.getRowspan();// row

      if (newcol.compareTo("" + count) < 0 && newrow.compareTo(row) < 0) {// 少行又少列
        for (int i = Integer.parseInt(newcol) + 1; i < (count + 1); i++) {
          for (int j = 1; j < (Integer.parseInt(row) + 1); j++) {
            String content = reportMngDAO.getContentByColRow_WL(tablename, i,
                j, securityInfo);
            if (!(content == null || content.length() == 0)) {
              flag = "have";
              return flag;
            }
          }
        }
        for (int i = 1; i < (count + 1); i++) {
          for (int j = Integer.parseInt(newrow) + 1; j < (Integer.parseInt(row) + 1); j++) {
            String content = reportMngDAO.getContentByColRow_WL(tablename, i,
                j, securityInfo);
            if (!(content == null || content.length() == 0)) {
              flag = "have";
              return flag;
            }
          }
        }
      } else if (newcol.compareTo("" + count) < 0) {// 减少行
        for (int i = Integer.parseInt(newcol) + 1; i < (count + 1); i++) {
          for (int j = 1; j < (Integer.parseInt(row) + 1); j++) {
            String content = reportMngDAO.getContentByColRow_WL(tablename, i,
                j, securityInfo);
            if (!(content == null || content.length() == 0)) {
              flag = "have";
              return flag;
            }
          }
        }
      } else if (newrow.compareTo(row) < 0) {// 减少列
        for (int i = 1; i < (count + 1); i++) {
          for (int j = Integer.parseInt(newrow) + 1; j < (Integer.parseInt(row) + 1); j++) {
            String content = reportMngDAO.getContentByColRow_WL(tablename, i,
                j, securityInfo);
            if (!(content == null || content.length() == 0)) {
              flag = "have";
              return flag;
            }
          }
        }
      }

    } catch (BusinessException bex) {
      throw bex;
    } catch (Exception e) {
      e.printStackTrace();
    }
    return flag;

  }

}
