package org.xpup.common.util;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang.Validate;
import org.xpup.common.enums.OrderEnum;
public final class PaginationUtils {
  public static void updatePagination(Pagination pagination,
      HttpServletRequest request) {
    String use = request.getParameter("use");
    if ("pagination".equalsIgnoreCase(use)) {
      String page = request.getParameter("page");

      Validate.notNull(page);
      pagination.execute(page);
    } else if ("sort".equalsIgnoreCase(use)) {
      String orderBy = request.getParameter("orderBy");

      Validate.notNull(orderBy);
      if (orderBy.equalsIgnoreCase(pagination.getOrderBy())) {
        if (OrderEnum.ASC.equals(pagination.getOrder())) {
          pagination.setOrder(OrderEnum.DESC);
          pagination.setOrderother("DESC");
        } else {
          pagination.setOrderBy(orderBy);
          pagination.setOrder(OrderEnum.ASC);
          pagination.setOrderother("ASC");
        }
      } else {
        pagination.setOrderBy(orderBy);
        pagination.setOrder(OrderEnum.ASC);
        pagination.setOrderother("ASC");
      }
    }
  }

  public static Pagination makePagination(HttpServletRequest request) {
    // 声明返回值变量
    Pagination pagination = null;

    // 从request中取值。
    String use = request.getParameter("use");
    if (use == null)
      use = (String) request.getAttribute("use");
    String paginationXml = request.getParameter("paginationXml");
    if (paginationXml == null)
      paginationXml = (String) request.getAttribute("paginationXml");
    String page = request.getParameter("page");
    String orderBy = request.getParameter("orderBy");

    // 验证值
    Validate.isTrue("pagination".equalsIgnoreCase(use)
        || "sort".equalsIgnoreCase(use) || "business".equalsIgnoreCase(use));
    Validate.notNull(paginationXml);
    if ("pagination".equalsIgnoreCase(use)) {
      Validate.notNull(page);
    }
    if ("sort".equalsIgnoreCase(use)) {
      Validate.notNull(orderBy);
    }

    // 创建Pagination
    pagination = loadFromXml(paginationXml);

    // 变更pagination
    if ("pagination".equalsIgnoreCase(use)) {
      pagination.execute(page);
    } else if ("sort".equalsIgnoreCase(use)) {
      if (orderBy.equalsIgnoreCase(pagination.getOrderBy())) {
        if (OrderEnum.ASC.equals(pagination.getOrder())) {
          pagination.setOrder(OrderEnum.DESC);
        } else {
          pagination.setOrderBy(orderBy);
          pagination.setOrder(OrderEnum.ASC);
        }
      }
    }

    /*
     * user==pagination时，paginationXml非空，page(取值为数字或字符串：first,prev,next,last)非空。
     * user==sort时，paginationXml、orderBy非空。 user==business时，paginationXml非空。
     * pagination、sort直接使用ShowXxxAC，business时先调用自己的AC，再forward到FindXxxAC。
     */

    return pagination;
  }

  /**
   * <pagination> <count>132</count> <page-size>10</page-size> <current-page>5</current-page>
   * <order-by>organization.name</order-by> <order>asc</order>
   * <query-criterions> <query-criterion> 
   *      <key>name</key>
   *      <value>IBM</value>
   *    </query-criterion>
   *  </query-criterions>
   *<pagination>
   */
  public static Pagination loadFromXml(String xml) {
    // TODO 从XML中取出如下值，然后构建Pagination。

    int count = 132;
    int pageSize = 10;
    int page = 5;
    String orderBy = "";
    OrderEnum order = OrderEnum.ASC;
    Map queryCriterions = null;
    return new Pagination(count, pageSize, page, orderBy, order,
        queryCriterions);
  }
}
