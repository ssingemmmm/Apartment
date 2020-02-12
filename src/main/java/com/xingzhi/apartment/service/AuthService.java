package com.xingzhi.apartment.service;


import com.xingzhi.apartment.model.User;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public interface AuthService {
    int authorize(HttpServletRequest req);
    Map<String, String> authenticate(User user);
}
