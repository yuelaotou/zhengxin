/**
 * Created on : 2006-6-12 14:37:46
 * Created by : 田野
 * Email      : tian.ye@neusoft.com
 * Copyright  : www.xpup.org
 */
package org.xpup.security.wsf.acegiex;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.log4j.Logger;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGEncodeParam;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

/**
 * 校验码生成工具类
 * 
 * @author $Author$
 * @version $Revision$,$Date$
 */
public class GenerateImageServlet extends HttpServlet {

  private static final long serialVersionUID = -5923184087385523815L;

  private static final Logger logger = Logger
      .getLogger(GenerateImageServlet.class);

  private int count = 6;

  private int width = 90, height = 20;

  private String strategy = "safe";

  private Font font = new Font("Times New Roman", Font.PLAIN, 18);

  public void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    response.setContentType("image/jpeg"); // 必须设置ContentType为image/jpeg
    response.setHeader("Pragma", "No-cache");
    response.setHeader("Cache-Control", "no-cache");
    response.setDateHeader("Expires", 0);

    // 生成指定位数的随机字符串
    String randomChars = RandomStringUtils.random(count, '0', 'Z', true, true)
        .toLowerCase();

    // 把验证码置入到session
    HttpSession session = request.getSession(true);
    session
        .setAttribute(
            AuthenticationProcessingFilterEx.ACEGI_SECURITY_FORM_VALIDATECODE_KEY_IN_SESSION,
            randomChars);

    try {
      OutputStream out = response.getOutputStream();

      // 应用算法
      Class cls = this.getClass();
      Method algorithmMethod = cls.getDeclaredMethod(strategy, new Class[] {
          String.class, OutputStream.class });
      algorithmMethod.invoke(this, new Object[] { randomChars, out });

    } catch (Exception e) {
      logger.info("图片生成失败", e);
    }
  }

  /**
   * 产生jpeg图片并写入到流中
   * <p>
   * 安全性：弱；性能：强
   * 
   * @param randomChars 图片内容
   * @param out 待写图片的流
   */
  protected void fastest(String randomChars, OutputStream out) throws Exception {
    // 创建内存图像
    BufferedImage bi = new BufferedImage(width, height,
        BufferedImage.TYPE_INT_RGB);
    Graphics2D g = bi.createGraphics();
    g.clearRect(0, 0, width, height);
    g.setColor(Color.CYAN);
    g.setFont(new Font("Times New Roman", Font.PLAIN, 18));
    g.drawString(randomChars, 8, 16);

    // 使用JPEG编码，输出到response的输出流
    JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
    JPEGEncodeParam param = encoder.getDefaultJPEGEncodeParam(bi);

    param.setQuality(1.0f, false);

    encoder.setJPEGEncodeParam(param);

    encoder.encode(bi);
  }

  /**
   * 产生jpeg图片并写入到流中
   * <p>
   * 安全性：中；性能：中
   * 
   * @param randomChars 图片内容
   * @param out 待写图片的流
   */
  protected void faster(String randomChars, OutputStream out) throws Exception {
    // 在内存中创建图象
    BufferedImage image = new BufferedImage(width, height,
        BufferedImage.TYPE_INT_RGB);

    // 获取图形上下文
    Graphics g = image.getGraphics();

    // 设定背景色
    g.setColor(new Color(0xDCDCDC));
    g.fillRect(0, 0, width, height);

    // 画边框
    g.setColor(Color.black);
    g.drawRect(0, 0, width - 1, height - 1);

    // 将认证码显示到图象中
    g.setColor(Color.black);

    g.setFont(font);

    // 取随机产生的认证码
    char[] chars = randomChars.toCharArray();
    for (int i = 0; i < chars.length; i++) {
      g.drawString(String.valueOf(chars[i]), 13 * i + 6, 16);
    }

    // 随机产生多个干扰点，使图象中的认证码不易被其它程序探测到
    Random random = new Random();
    for (int i = 0; i < 75; i++) {
      int x = random.nextInt(width);
      int y = random.nextInt(height);
      g.drawOval(x, y, 0, 0);
    }

    // 图象生效
    g.dispose();

    // 输出图象到页面
    ImageIO.write(image, "JPEG", out);
  }

  /**
   * 产生jpeg图片并写入到流中
   * <p>
   * 安全性：强；性能：弱
   * 
   * @param randomChars 图片内容
   * @param out 待写图片的流
   */
  protected void safe(String randomChars, OutputStream out) throws Exception {
    // 在内存中创建图象
    BufferedImage image = new BufferedImage(width, height,
        BufferedImage.TYPE_INT_RGB);
    // 获取图形上下文
    Graphics g = image.getGraphics();
    // 生成随机类
    Random random = new Random();
    // 设定背景色
    g.setColor(getRandColor(200, 250));
    g.fillRect(0, 0, width, height);
    // 设定字体
    g.setFont(font);

    // 画边框
    g.setColor(getRandColor(20, 130));
    g.drawRect(0, 0, width - 1, height - 1);

    // 随机产生多条干扰线，使图象中的认证码不易被其它程序探测到
    g.setColor(getRandColor(160, 200));
    for (int i = 0; i < 150; i++) {
      int x = random.nextInt(width);
      int y = random.nextInt(height);
      int xl = random.nextInt(12);
      int yl = random.nextInt(12);
      g.drawLine(x, y, x + xl, y + yl);
    }

    // 取随机产生的认证码
    char[] chars = randomChars.toCharArray();
    for (int i = 0; i < chars.length; i++) {
      // 将认证码显示到图象中
      g.setColor(new Color(20 + random.nextInt(110), 20 + random.nextInt(110),
          20 + random.nextInt(110)));

      // 调用函数出来的颜色相同，可能是因为种子太接近，所以只能直接生成
      g.drawString(String.valueOf(chars[i]), 13 * i + 6, 16);
    }

    // 图象生效
    g.dispose();
    // 输出图象到页面
    ImageIO.write(image, "JPEG", out);
  }

  /**
   * 生成指定范围内的随机色
   * 
   * @param start
   * @param end
   * @return
   */
  protected Color getRandColor(int start, int end) {// 给定范围获得随机颜色
    Random random = new Random();

    int r = start + random.nextInt(end - start);
    int g = start + random.nextInt(end - start);
    int b = start + random.nextInt(end - start);

    return new Color(r, g, b);
  }

  public void init(ServletConfig servletConfig) throws ServletException {
    super.init(servletConfig);

    // 生成图片中的随机数的位数
    String numLength = getInitParameter("randomNumber.length");
    this.count = Integer.valueOf(numLength).intValue();

    // 生成图片的策略算法
    this.strategy = getInitParameter("randomImage.strategy");

  }

}
