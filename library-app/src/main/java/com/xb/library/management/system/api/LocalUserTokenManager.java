package com.xb.library.management.system.api;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.xb.library.management.system.domain.Token;
import com.xb.library.management.system.domain.User;
import org.springframework.stereotype.Component;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @author xiabiao
 * @date 2022-12-05
 */
@Component
public class LocalUserTokenManager implements UserTokenManager {

  private BiMap<Token, User> map = HashBiMap.create();

  @Override
  public String set(User user) {
    if (map.containsValue(user)) {
      Token token = map.inverse().get(user);
      return token.getToken();
    }
    String token = UUID.randomUUID().toString().replace("-", "");
    Token key = new Token(token);
    key.setExpiredTimeSeconds(
        System.currentTimeMillis() / TimeUnit.SECONDS.toMillis(1) + Token.ACTIVE_SECONDS);
    map.put(key, user);
    return token;
  }

  @Override
  public User get(String token) {
    Token key = new Token(token);
    if (key.getExpiredTimeSeconds() > System.currentTimeMillis() / TimeUnit.SECONDS.toMillis(1)) {
      clear(token);
    }
    return map.get(key);
  }

  @Override
  public boolean clear(String token) {
    Token key = new Token(token);
    return map.remove(key) != null;
  }

  @Override
  public void refresh(String token) {
    User user = map.get(new Token(token));
    if (user != null) {
      Token realToken = map.inverse().get(user);
      long currentTimeMillis = System.currentTimeMillis();
      if (realToken.getExpiredTimeSeconds() > currentTimeMillis / TimeUnit.SECONDS.toMillis(1)) {
        realToken.setExpiredTimeSeconds(currentTimeMillis + Token.ACTIVE_SECONDS);
      } else {
        map.remove(realToken);
      }
    }
  }
}
