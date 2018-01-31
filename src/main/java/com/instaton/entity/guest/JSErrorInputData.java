package com.instaton.entity.guest;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.instaton.entity.BaseInput;

@JsonIgnoreProperties(ignoreUnknown = true)
public class JSErrorInputData extends BaseInput {

  private static final long serialVersionUID = 1L;

  private String msg;
  private String jsUrl;
  private String line;
  private String col;
  private String stack;
  private String url;

  public String getMsg() {
    return msg;
  }

  public void setMsg(String msg) {
    this.msg = msg;
  }

  public String getJsUrl() {
    return jsUrl;
  }

  public void setJsUrl(String jsUrl) {
    this.jsUrl = jsUrl;
  }

  public String getLine() {
    return line;
  }

  public void setLine(String line) {
    this.line = line;
  }

  public String getCol() {
    return col;
  }

  public void setCol(String col) {
    this.col = col;
  }

  public String getStack() {
    return stack;
  }

  public void setStack(String stack) {
    this.stack = stack;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  @Override
  public String toString() {
    return "JSError [msg="
        + msg
        + ", jsUrl="
        + jsUrl
        + ", line="
        + line
        + ", col="
        + col
        + ", stack="
        + stack
        + ", url="
        + url
        + "]";
  }
}
