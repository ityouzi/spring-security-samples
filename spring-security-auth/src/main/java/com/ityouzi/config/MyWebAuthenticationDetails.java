package com.ityouzi.config;

import org.springframework.security.web.authentication.WebAuthenticationDetails;

import javax.servlet.http.HttpServletRequest;

/**
 * @Auther: Liberal-World
 * @Date: 2020-05-21 11:09
 * @Description:
 * @Version 1.0
 */
public class MyWebAuthenticationDetails extends WebAuthenticationDetails {

    private boolean isPassed;

    public MyWebAuthenticationDetails(HttpServletRequest req) {
        super(req);
        String code = req.getParameter("code");
        String verify_code = (String) req.getSession().getAttribute("verify_code");
        if (code != null && verify_code != null && code.equals(verify_code)){
            isPassed = true;
        }
    }

    public boolean isPassed(){
        return isPassed;
    }




    /**
     * 首先我们定义 MyWebAuthenticationDetails，由于它的构造方法中，刚好就提供了 HttpServletRequest 对象，
     * 所以我们可以直接利用该对象进行验证码判断，并将判断结果交给 isPassed 变量保存。
     * 「如果我们想扩展属性，只需要在 MyWebAuthenticationDetails 中再去定义更多属性，
     * 然后从 HttpServletRequest 中提取出来设置给对应的属性即可，这样，在登录成功后就可以随时随地获取这些属性了。」
     */

}
