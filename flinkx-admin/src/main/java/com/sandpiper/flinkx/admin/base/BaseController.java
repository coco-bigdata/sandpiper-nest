package com.sandpiper.flinkx.admin.base;


import com.baomidou.mybatisplus.extension.api.ApiController;
import com.sandpiper.flinkx.admin.util.JwtTokenUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

import static com.sandpiper.flinkx.core.util.Constants.STRING_BLANK;

/**
 *
 * @Author: sandpiper-nest
 * @Date: 2020/9/16 11:14
 * @Description: base controller
 **/
public class BaseController extends ApiController {

    public Integer getCurrentUserId(HttpServletRequest request) {
        Enumeration<String> auth = request.getHeaders(JwtTokenUtils.TOKEN_HEADER);
        String token = auth.nextElement().replace(JwtTokenUtils.TOKEN_PREFIX, STRING_BLANK);
        return JwtTokenUtils.getUserId(token);
    }
}