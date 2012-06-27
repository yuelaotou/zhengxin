package org.xpup.paramsys;

public interface IParams {
  public String getString(String key);

  public String getString(String key, String defaultValue);

  public int getInt(String key);

  public int getInt(String key, int defaultValue);

  public double getDouble(String key);

  public double getDouble(String key, double defaultValue);

  public boolean getBoolean(String key);

  public boolean getBoolean(String key, boolean defaultValue);
}
