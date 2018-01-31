package com.instaton.service;

public class BaseService {

  public <T> boolean isEmpty(T data) {
    return data == null;
  }
}
