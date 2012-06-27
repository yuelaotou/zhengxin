package org.xpup.paramsys;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.Validate;
import org.xpup.common.exception.SystemException;

public class FileParams extends AbstractParams {

  private Properties properties = new Properties();

  private List files = new ArrayList();

  public FileParams(List files) {
    Validate.notNull(files, "违反前置约束，参数 filePaths 不可为 null。");
    this.files = files;
    load();
  }

  protected Properties getProperties() {
    return properties;
  }

  private void load() {
    InputStream input = null;
    try {
      try {
        for (Iterator itor = files.iterator(); itor.hasNext();) {
          String path = (String) itor.next();
          path = StringUtils.trimToEmpty(path);
          input = getClass().getResourceAsStream(path);

          if (input == null) {
            throw new SystemException("找不到路径为 " + path
                + " 的配置文件，请确认路径正确，并以 / 开头");
          }

          Properties temp = new Properties();
          temp.load(input);
          input.close();

          properties.putAll(temp);
        }
      } finally {
        if (input != null) {
          input.close();
        }
      }
    } catch (IOException ex) {
      throw new SystemException("加载参数配置文件发生异常", ex);
    }
  }

}
