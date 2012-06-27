package org.xpup.hafmis.orgstrct.dto;

public class UserAssignBookDTO {
  private String id="";
  private String username="";
  private String bookid="";
  private String bookname="";
  
  public String getBookid() {
    return bookid;
  }
  public void setBookid(String bookid) {
    this.bookid = bookid;
  }
  public String getBookname() {
    return bookname;
  }
  public void setBookname(String bookname) {
    this.bookname = bookname;
  }
  public String getId() {
    return id;
  }
  public void setId(String id) {
    this.id = id;
  }
  public String getUsername() {
    return username;
  }
  public void setUsername(String username) {
    this.username = username;
  }

}
