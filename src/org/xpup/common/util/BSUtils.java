package org.xpup.common.util;

import java.io.InputStream;

import javax.servlet.ServletContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionServlet;
import org.apache.struts.config.ModuleConfig;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.struts.DelegatingActionUtils;

public final class BSUtils {

  private final static String FILE_APPLICATION_CONTEXT = "applicationContext.xml";

  private static XmlBeanFactory bf = null;

  public static void initApplicationContext(String contextFileName) {
    InputStream is = Thread.currentThread().getContextClassLoader()
        .getResourceAsStream(contextFileName);
    if (is == null) {
      log.error("未能成功加载文件名为 " + contextFileName + " 配置文件！");
    }
    try {
      Resource resource = new InputStreamResource(is);
      bf = new XmlBeanFactory(resource);
    } catch (Throwable ex) {
      log.error("创建bean工厂失败！系统将无法正常使用业务服务。", ex);
    }
  }

  public static void initApplicationContext() {
    initApplicationContext(FILE_APPLICATION_CONTEXT);
  }

  public static Object getBusinessService(String businessServiceName) {
    if (businessServiceName == null) {
      throw new IllegalArgumentException("参数 businessServiceName 不能为 null");
    }

    Object bs = null;
    if (bf == null) {
      log.error("applicationContext没有被初始化或成功初始化！");
    }
    try {
      bs = bf.getBean(businessServiceName);
    } catch (BeansException ex) {
      log.error("获取业务服务对象 '" + businessServiceName + "' 失败!!!", ex);
      throw ex;
    } catch (IllegalStateException ex) {
      log.error("获取业务服务对象 '" + businessServiceName + "' 失败!!!", ex);
      throw ex;
    }
    return bs;
  }

  public static Object getBusinessService(String businessServiceName,
      ServletContext servletContext) {
    if (servletContext == null) {
      throw new IllegalArgumentException("参数 servletContext 不能为 null");
    }
    if (businessServiceName == null) {
      throw new IllegalArgumentException("参数 businessServiceName 不能为 null");
    }

    Object bs = null;
    try {
      WebApplicationContext wac = WebApplicationContextUtils
          .getRequiredWebApplicationContext(servletContext);
      bs = wac.getBean(businessServiceName);
    } catch (BeansException ex) {
      log.error("获取业务服务对象 '" + businessServiceName + "' 失败!!!", ex);
      throw ex;
    } catch (IllegalStateException ex) {
      log.error("获取业务服务对象 '" + businessServiceName + "' 失败!!!", ex);
      throw ex;
    }
    return bs;
  }

  public static Object getBusinessService(String businessServiceName,
      Action action) {
    if (action == null) {
      throw new IllegalArgumentException("参数 action 不能为 null");
    }
    if (businessServiceName == null) {
      throw new IllegalArgumentException("参数 businessServiceName 不能为 null");
    }

    Object bs = null;
    try {
      ServletContext servletContext = action.getServlet().getServletContext();
      WebApplicationContext wac = WebApplicationContextUtils
          .getRequiredWebApplicationContext(servletContext);
      bs = wac.getBean(businessServiceName);
    } catch (BeansException ex) {
      log.error("获取业务服务对象 '" + businessServiceName + "' 失败!!!", ex);
      throw ex;
    } catch (IllegalStateException ex) {
      log.error("获取业务服务对象 '" + businessServiceName + "' 失败!!!", ex);
      throw ex;
    }
    return bs;
  }

  /**
   * 该方法用于在Action的方法中，获取业务服务对象。该方法支持struts的多模块开发，即对于一个
   * 可能会划分为多的个模块给不同的开发小组的工程，请应该使用此方法，这样会使模块轻易集成到一 个一WEB应用中。
   * 
   * @param action 调用该方法的方法所在的Action
   * @param moduleConfig 调用该方法的方法的ModuleConfig
   * @param businessServiceName 在spring配置文件中，配置的业务服务的名称
   * @return 业务服务对象
   */
  public static Object getBusinessService(String businessServiceName,
      Action action, ModuleConfig moduleConfig) {

    return getBusinessService(businessServiceName, action.getServlet(),
        moduleConfig);

  }

  public static Object getBusinessService(String businessServiceName,
      ActionServlet servlet, ModuleConfig moduleConfig) {
    if (businessServiceName == null) {
      throw new IllegalArgumentException("参数 businessServiceName 不能为 null");
    }
    if (moduleConfig == null) {
      throw new IllegalArgumentException("参数 moduleConfig 不能为 null");
    }

    Object bs = null;
    try {
      WebApplicationContext wac = DelegatingActionUtils
          .getRequiredWebApplicationContext(servlet, moduleConfig);
      bs = wac.getBean(businessServiceName);
    } catch (BeansException ex) {
      log.error("获取业务服务对象 '" + businessServiceName + "' 失败!!!", ex);
      throw ex;
    } catch (IllegalStateException ex) {
      log.error("获取业务服务对象 '" + businessServiceName + "' 失败!!!", ex);
      throw ex;
    }
    return bs;
  }

  private static final Log log = LogFactory.getLog(BSUtils.class);
}