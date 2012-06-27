package org.xpup.hafmis.sysloan.common.biz.palindromeimpswitch.dto;



import org.xpup.common.util.imp.domn.interfaces.impDto;


public class OneTailDTO extends impDto{
  /**
   * 
   */
  private static final long serialVersionUID = 1L;
  /**
   * 适用于各个银行的DTO
   */
//  static final String[] values = { "One", "Two","Three","Four","Five","Six","Seven",
//    "Eight","Nine","Ten","Eleven","Twelve","Thirteen","Fourteen","Fifteen","Sixteen","Seventeen","Eighteen","Nineteen","Twenty" };
  private String one;
  private String two;
  private String three;
  private String four;
  private String five;
  private String six;
  private String seven;
  private String eight;
  private String nine;
  private String ten;
  private String eleven;
  private String twelve;
  private String thirteen;
  private String fourteen;

  public String getFour() {
    return four;
  }

  public void setFour(String four) {
    this.four = four;
  }

  public String getOne() {
    return one;
  }

  public void setOne(String one) {
    this.one = one;
  }

  public String getThree() {
    return three;
  }

  public void setThree(String three) {
    this.three = three;
  }

  public String getTwo() {
    return two;
  }

  public void setTwo(String two) {
    this.two = two;
  }

  public String getEight() {
    return eight;
  }

  public void setEight(String eight) {
    this.eight = eight;
  }

  public String getEleven() {
    return eleven;
  }

  public void setEleven(String eleven) {
    this.eleven = eleven;
  }

  public String getFive() {
    return five;
  }

  public void setFive(String five) {
    this.five = five;
  }

  public String getFourteen() {
    return fourteen;
  }

  public void setFourteen(String fourteen) {
    this.fourteen = fourteen;
  }

  public String getNine() {
    return nine;
  }

  public void setNine(String nine) {
    this.nine = nine;
  }

  public String getSeven() {
    return seven;
  }

  public void setSeven(String seven) {
    this.seven = seven;
  }

  public String getSix() {
    return six;
  }

  public void setSix(String six) {
    this.six = six;
  }

  public String getTen() {
    return ten;
  }

  public void setTen(String ten) {
    this.ten = ten;
  }

  public String getThirteen() {
    return thirteen;
  }

  public void setThirteen(String thirteen) {
    this.thirteen = thirteen;
  }

  public String getTwelve() {
    return twelve;
  }

  public void setTwelve(String twelve) {
    this.twelve = twelve;
  }

  
}
//public class BrowserClass {
//  public static void main(String[] args) {
//    Display display = new Display();
//    final Shell shell = new Shell(display);
//    shell.setText("浏览器");
//    shell.setSize(620, 500);
//    ToolBar toolbar = new ToolBar(shell, SWT.NONE);
//    toolbar.setBounds(5, 5, 200, 30);
//    ToolItem goButton = new ToolItem(toolbar, SWT.PUSH);
//    goButton.setText("前进");
//    ToolItem backButton = new ToolItem(toolbar, SWT.PUSH);
//    backButton.setText("后退");
//    ToolItem stopButton = new ToolItem(toolbar, SWT.PUSH);
//    stopButton.setText("停止");
//    final Text text = new Text(shell, SWT.BORDER);
//    text.setBounds(5, 35, 400, 25);
//    final Browser browser = new Browser(shell, SWT.NONE);
//    browser.setBounds(5, 75, 600, 400);
//    Listener listener = new Listener() {
//      public void handleEvent(Event event) {
//        ToolItem item = (ToolItem) event.widget;
//        String string = item.getText();
//        if (string.equals("后退"))
//          browser.back();
//        else if (string.equals("停止"))
//          browser.stop();
//        else if (string.equals("前进"))
//          browser.setUrl(text.getText());
//      }
//    };
//    goButton.addListener(SWT.Selection, listener);
//    backButton.addListener(SWT.Selection, listener);
//    stopButton.addListener(SWT.Selection, listener);
//    text.addListener(SWT.DefaultSelection, new Listener() {
//      public void handleEvent(Event e) {
//        browser.setUrl(text.getText());
//      }
//    });
//    shell.open();
//    browser.setUrl("http://www.yahoo.com.cn");
//    while (!shell.isDisposed()) {
//      if (!display.readAndDispatch())
//        display.sleep();
//    }
//    display.dispose();
//  }
//}
