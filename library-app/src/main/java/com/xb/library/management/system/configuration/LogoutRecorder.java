package com.xb.library.management.system.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author xiabiao
 * @date 2022-12-05
 */
public class LogoutRecorder implements LogoutHandler {

  private static final Logger LOGGER = LoggerFactory.getLogger(LogoutRecorder.class);

  @Override
  public void logout(
      HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
    Object principal = authentication.getPrincipal();
    LOGGER.info("user [{}] logout.", principal);
  }
}
