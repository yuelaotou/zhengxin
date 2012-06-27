package org.xpup.security.common.domain;

import java.util.LinkedHashSet;
import java.util.Set;

import org.xpup.common.domain.DomainObject;

public class MenuItem extends DomainObject {

  private static final long serialVersionUID = -2849887598999393297L;

  private Set subMenuItems = new LinkedHashSet();

  private MenuItem parentMenuItem;

  private int position;

  private String target = "workaround";

  private String description;

  private String url;

  private String caption;
  
  private String opSystemType="";
  /**
   * @return Returns the caption.
   */
  public String getCaption() {
    return caption;
  }

  /**
   * @param caption The caption to set.
   */
  public void setCaption(String caption) {
    this.caption = caption;
  }

  /**
   * @return Returns the url.
   */
  public String getUrl() {
    return url;
  }

  /**
   * @param url The url to set.
   */
  public void setUrl(String url) {
    this.url = url;
  }

  /**
   * @return Returns the description.
   */
  public String getDescription() {
    return description;
  }

  /**
   * @param description The description to set.
   */
  public void setDescription(String description) {
    this.description = description;
  }

  /**
   * @return Returns the target.
   */
  public String getTarget() {
    return target;
  }

  /**
   * @param target The target to set.
   */
  public void setTarget(String target) {
    this.target = target;
  }

  /**
   * @return Returns the position.
   */
  public int getPosition() {
    return position;
  }

  /**
   * @param position The position to set.
   */
  public void setPosition(int postion) {
    this.position = postion;
  }

  /**
   * @return Returns the parentMenuItem.
   */
  public MenuItem getParentMenuItem() {
    return parentMenuItem;
  }

  /**
   * @param parentMenuItem The parentMenuItem to set.
   */
  public void setParentMenuItem(MenuItem parentMenuItem) {
    this.parentMenuItem = parentMenuItem;
  }

  /**
   * @return Returns the subMenuItems.
   */
  public Set getSubMenuItems() {
    return subMenuItems;
  }

  /**
   * @param subMenuItems The subMenuItems to set.
   */
  public void setSubMenuItems(Set subMenuItems) {
    this.subMenuItems = subMenuItems;
  }

  public void addSubMenuItem(MenuItem menuItem) {
    menuItem.setParentMenuItem(this);
    subMenuItems.add(menuItem);
  }

  public boolean removeSubMenuItem(MenuItem menuItem) {
    boolean success = subMenuItems.remove(menuItem);
    menuItem.setParentMenuItem(null);
    return success;
  }

  public boolean isRoot() {
    return getParentMenuItem() != null ? false : true;
  }

  public boolean isLeaf() {
    int count = getSubMenuItems().size();
    return count > 0 ? false : true;
  }

  public String getOpSystemType() {
    return opSystemType;
  }

  public void setOpSystemType(String opSystemType) {
    this.opSystemType = opSystemType;
  }

}
