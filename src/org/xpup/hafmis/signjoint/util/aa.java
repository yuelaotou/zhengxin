package org.xpup.hafmis.signjoint.util;
import java.io.File;    
import java.io.FileOutputStream;    
import java.io.UnsupportedEncodingException;    
   
/**   
 * 2007-8-10 jyin at gomez dot com   
 */   
public class aa {
  
    public static void main(String[] args) {    
  
      System.out.println(SignTools.conversionTo15DigitCard_num("210105198410262218"));   

    }
    
    private static String getBinaryString(int i) {    
      String binaryStr = Integer.toBinaryString(i);    
      int length = binaryStr.length();    
      for (int l = 0; l < 8 - length; l++) {    
          binaryStr = "0" + binaryStr;    
      }    
      return binaryStr;    
  } 
    
    
    public static String gbToUtf8(String str) throws UnsupportedEncodingException {    
        StringBuffer sb = new StringBuffer();    
        for (int i = 0; i < str.length(); i++) {    
            String s = str.substring(i, i + 1);    
            if (s.charAt(0) > 0x80) {    
                byte[] bytes = s.getBytes("Unicode");    
                String binaryStr = "";    
                for (int j = 2; j < bytes.length; j += 2) {    
                    // the first byte    
                    String hexStr = getHexString(bytes[j + 1]);    
                    String binStr = getBinaryString(Integer.valueOf(hexStr, 16).intValue());    
                    binaryStr += binStr;    
                    // the second byte    
                    hexStr = getHexString(bytes[j]);    
                    binStr = getBinaryString(Integer.valueOf(hexStr, 16).intValue());    
                    binaryStr += binStr;    
                }    
                // convert unicode to utf-8    
                String s1 = "1110" + binaryStr.substring(0, 4);    
                String s2 = "10" + binaryStr.substring(4, 10);    
                String s3 = "10" + binaryStr.substring(10, 16);    
                byte[] bs = new byte[3];    
                bs[0] = Integer.valueOf(s1, 2).byteValue();    
                bs[1] = Integer.valueOf(s2, 2).byteValue();    
                bs[2] = Integer.valueOf(s3, 2).byteValue();    
                String ss = new String(bs, "UTF-8");    
                sb.append(ss);    
            } else {    
                sb.append(s);    
            }    
        }    
        return sb.toString();    
    }    
   
    private static String getHexString(byte b) {    
        String hexStr = Integer.toHexString(b);    
        int m = hexStr.length();    
        if (m < 2) {    
            hexStr = "0" + hexStr;    
        } else {    
            hexStr = hexStr.substring(m - 2);    
        }    
        return hexStr;    
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    private static int by2int(int b)
    {
      return b & 0xff;
    }
    
    public static String UTF82GB2312(String param)
    {
      try
      {
          param = new String(param.getBytes("ISO8859-1"), "UTF-8");
          byte[] bytes = param.getBytes("UTF-8");
          param = UTF82GB2312(bytes);
          return param;
      }
      catch(Exception e)
      {
          return null;
      }
    }
    
    private static String UTF82GB2312(byte buf[])
    {
      int len = buf.length;
      StringBuffer sb = new StringBuffer(len/2);
      for(int i =0; i<len;i++)
      {
          if(by2int(buf[i])<=0x7F) sb.append((char)buf[i]);
          else if(by2int(buf[i])<=0xDF && by2int(buf[i])>=0xC0)
          {
            int bh = by2int(buf[i] & 0x1F);
            int bl = by2int(buf[++i] & 0x3F);
            bl = by2int(bh << 6 | bl);
            bh = by2int(bh >> 2);
            int c = bh<<8 | bl;
            sb.append((char)c);
          } else if(by2int(buf[i])<=0xEF && by2int(buf[i])>=0xE0){
            int bh = by2int(buf[i] & 0x0F);
            int bl = by2int(buf[++i] & 0x3F);
            int bll = by2int(buf[++i] & 0x3F);
            bh = by2int(bh << 4 | bl>>2);
            bl = by2int(bl << 6 | bll);
            int c = bh<<8 | bl;
      //¿Õ¸ñ×ª»»Îª°ë½Ç
      if(c==58865){
          c = 32;
      }
            sb.append((char)c);
          }
      }
      return sb.toString();
    }

    
    
    
    
    
    
    
    
    
   
   
}   

