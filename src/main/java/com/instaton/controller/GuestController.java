package com.instaton.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.instaton.constant.EndpointConstant;

@RestController
@RequestMapping(EndpointConstant.API_ENDPOINT_GUEST_PATTERN)
public class GuestController {

  private static Logger logger = LoggerFactory.getLogger(GuestController.class);

  @Autowired private HttpServletRequest request;

  // @RequestMapping(value = "/jserrorlogger", method = { RequestMethod.POST })
  // public InstatonResponse<Boolean> logJSErrors(@RequestBody JSErrorInputData inputData) {
  //
  // Authentication authentication = authenticationService.getCurrentAuthentication();
  //
  // if (authentication != null && authentication instanceof AuthenticationToken) {
  // AuthenticationToken currentAuthentication = (AuthenticationToken) authentication;
  //
  // logger.error("JSErrorController currentAuthentication = {} inputData = {}", currentAuthentication.toString(), inputData.toString());
  // }
  // else {
  // String remoteAddr = request.getRemoteAddr();
  //
  // logger.error("JSErrorController remoteAddr = {} inputData = {}", remoteAddr, inputData.toString());
  // }
  //
  // return InstatonResponseBuilder.buildSuccessResponseData(Boolean.TRUE);
  // }
}
