package org.xpup.security.buildtime.bizsrvc;

import java.io.Serializable;
import java.util.List;

import org.xpup.common.exception.BusinessException;
import org.xpup.security.common.domain.MenuItem;

public interface IMaintainMenuBS {

  public List loadMenuItemTree();

  public Serializable createMenuItem(MenuItem menuItem)
      throws BusinessException;

  public void modifyMenuItem(MenuItem menuItem) throws BusinessException;

  public void removeMenuItem(Serializable menuItemId) throws BusinessException;

  public MenuItem findMenuItem(Serializable menuItemId)
      throws BusinessException;

}