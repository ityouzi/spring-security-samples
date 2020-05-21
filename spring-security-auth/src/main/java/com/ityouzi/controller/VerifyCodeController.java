package com.ityouzi.controller;

import com.google.code.kaptcha.Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * @Auther: Liberal-World
 * @Date: 2020-05-21 09:51
 * @Description:
 * @Version 1.0
 */
@RestController
public class VerifyCodeController {

    @Autowired
    Producer producer;

    @GetMapping("/vc.jpg")
    public void getVerifyCode(HttpServletResponse resp, HttpSession session) throws IOException {
        resp.setContentType("image/jpeg");
        String text = producer.createText();
        session.setAttribute("verify_code",text);
        BufferedImage image = producer.createImage(text);
        try (ServletOutputStream out = resp.getOutputStream()){
            ImageIO.write(image,"jpg",out);
        }
    }
}
