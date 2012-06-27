package org.xpup.common.util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import org.xpup.common.domain.DomainObject;

public final class ListUtils {
  public static void addElement(DomainObject obj, String target,
      HttpServletRequest request) {
    List elems = (List) request.getSession().getAttribute(target);
    if (elems != null) {
      elems.add(0, obj);
    } else {
      elems = new ArrayList(1);
      elems.add(obj);
      request.getSession().setAttribute(target, elems);
    }
  }

  public static void removeElement(DomainObject obj, String target,
      HttpServletRequest request) {
    List elems = (List) request.getSession().getAttribute(target);
    if (elems != null) {
      elems.remove(obj);
    }
  }

  public static void replaceElement(DomainObject obj, String target,
      HttpServletRequest request) {
    List elems = (List) request.getSession().getAttribute(target);
    if (elems != null) {
      int pos = elems.indexOf(obj);
      if (pos != -1) {
        elems.remove(pos);
        elems.add(pos, obj);
      } else {
        elems.add(obj);
      }
    } else {
      elems.add(obj);
    }
  }

  /**
   * 将 List 类型的列表转化成 Serializable 类型。
   * 
   * @param list 需要转化的列表。
   * @return 转化后的 Serializable 类型。
   */
  public static Serializable convertToSerializable(List list) {
    if (list == null)
      return null;
    if (list instanceof Vector) {
      return (Vector) list;
    } else if (list instanceof ArrayList) {
      return (ArrayList) list;
    } else if (list instanceof LinkedList) {
      return (LinkedList) list;
    } else {
      Vector temp = new Vector();
      for (int i = 0; i < list.size(); i++) {
        temp.add(i, list.get(i));
      }
      return temp;
    }
  }
}
