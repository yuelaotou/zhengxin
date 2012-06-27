package org.xpup.hafmis.sysloan.accounthandle.adjustaccount.action;

public class T {
  static public double log(double value, double base) {
    return Math.log(value) / Math.log(base);
  }

  // 计算100的以10为底的对数就变为非常简单了：
  double log1 = T.log(100, 10); // log is 2.0
  // 512的以2为底的对数是：

  double log2 = T.log(512, 2); // log is 9.0
  // 下面的两个简单的方法也都是很有用的：

  static public double log2(double value) {
    return log(value, 2.0);
  }

  static public double log10(double value) {
    return log(value, 10.0);
  }

  public void main() {
    System.out.println(log1);
  }
}