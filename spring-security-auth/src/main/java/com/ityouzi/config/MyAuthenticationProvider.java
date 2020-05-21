package com.ityouzi.config;

import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * @Auther: Liberal-World
 * @Date: 2020-05-21 09:15
 * @Description:
 * @Version 1.0
 */
public class MyAuthenticationProvider extends DaoAuthenticationProvider {

    @Override
    protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken authentication)
            throws AuthenticationException {
//        // 获取当前请求
//        HttpServletRequest req = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
//        String code = req.getParameter("code");
//        // 从 session 中获取生成的验证码字符串
//        String verify_code = (String) req.getSession().getAttribute("verify_code");
//        if (code == null || verify_code == null || !code.equals(verify_code)){
//            throw new AuthenticationServiceException("验证码错误");
//        }
//        // 最后通过 super 调用父类方法，也就是 DaoAuthenticationProvider 的 additionalAuthenticationChecks 方法，该方法中主要做密码的校验
//        super.additionalAuthenticationChecks(userDetails,authentication);


        if (!((MyWebAuthenticationDetails) authentication.getDetails()).isPassed()){
            throw new AuthenticationServiceException("验证码错误");
        }
        super.additionalAuthenticationChecks(userDetails, authentication);
    }


}
