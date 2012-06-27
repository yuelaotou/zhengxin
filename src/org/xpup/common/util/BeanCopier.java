package org.xpup.common.util;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.Validate;
import org.xpup.common.exception.SystemException;

/**
 * Bean 属性的拷贝工具，提供了指定的 Bean 属性的拷贝。
 * 
 * @author $Author$
 * @version $Revision$,$Date$
 */
public class BeanCopier {
  public static void copyCertainProperty(Object destBean, String descPropName,
      Object sourceBean, String sourcePorpName) {
    Validate.notNull(destBean, "违反前置约束：参数 destBean 不可为 null");
    Validate.notNull(descPropName, "违反前置约束：参数 descPropName 不可为 null");
    Validate.notNull(sourceBean, "违反前置约束：参数 sourceBean 不可为 null");
    Validate.notNull(sourcePorpName, "违反前置约束：参数 sourcePorpName 不可为 null");
    Validate.notEmpty(descPropName, "违反前置约束：参数 descPropName 不可为空字符串");
    Validate.notEmpty(sourcePorpName, "违反前置约束：参数 sourcePorpName 不可为空字符串");
    try {
      Object o = PropertyUtils.getProperty(sourceBean, sourcePorpName);
      BeanUtils.copyProperty(destBean, descPropName, o);
    } catch (Exception ex) {
      throw new SystemException("拷贝 " + sourceBean + "." + sourcePorpName
          + " 到 " + destBean + "." + descPropName + " 发生异常", ex);
    }
  }

  public static void copyProperties(Object destBean, String[] descPropNames,
      Object sourceBean) {
    Validate.notNull(descPropNames, "违反前置约束：参数 descPropNames 不可为 null");
    for (int i = 0; i < descPropNames.length; i++) {
      copyCertainProperty(destBean, descPropNames[i], sourceBean,
          descPropNames[i]);
    }
  }

  public static void copyProperties(Object destBean, String[] descPropNames,
      Object sourceBean, String[] sourcePropNames) {
    Validate.notNull(descPropNames, "违反前置约束：参数 descPropNames 不可为 null");
    Validate.notNull(sourcePropNames, "违反前置约束：参数 sourcePropNames 不可为 null");
    if (descPropNames.length != sourcePropNames.length)
      throw new IllegalArgumentException("违反前置约束：需要拷贝的 Bean 中包含有不同数量的 Bean 属性");
    for (int i = 0; i < descPropNames.length; i++) {
      copyCertainProperty(destBean, descPropNames[i], sourceBean,
          descPropNames[i]);
    }
  }

  public static void copyProperties(Object destBean, Object sourceBean) {
    Validate.notNull(destBean, "违反前置约束：参数 destBean 不可为 null");
    Validate.notNull(sourceBean, "违反前置约束：参数 sourceBean 不可为 null");
    try {
      BeanUtils.copyProperties(destBean, sourceBean);
    } catch (Exception ex) {
      throw new SystemException(
          "拷贝 " + sourceBean + " 到 " + destBean + " 发生异常", ex);
    }
  }
}
