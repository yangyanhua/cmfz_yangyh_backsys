package com.baizhi.yangyh.controller;
import com.baizhi.yangyh.entity.User;
import com.baizhi.yangyh.service.UserService;
import com.baizhi.yangyh.util.SecurityCode;
import com.baizhi.yangyh.util.SecurityImage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @RequestMapping("value=/login.do")
    public String login(HttpServletRequest request, HttpSession session, User user,String code) {
        System.out.println(request.getSession().getServletContext().getRealPath("/"));
      //  String securityCode = (String) session.getAttribute("securityCode");
       // if (code.equals(securityCode)) {
            User user1 = userService.queryUser(user);
            if (user1 != null) {
                System.out.println("密码验证码正确");
                return "main";
            }
            return "login";
      //  }return "no";
    }
    @RequestMapping("value=/code.do")
    public void code(HttpSession session, HttpServletResponse response) throws Exception {
        // 生成验证码随机数
        String securityCode = SecurityCode.getSecurityCode();
        System.out.println(securityCode);
        // 将随机数存入session里
        session.setAttribute("securityCode", securityCode);
        // 生成验证码图片
        BufferedImage image = SecurityImage.createImage(securityCode);
        // 将验证码图片响应到客服端 //1.是图片 //2.这是格式 //3.这是响应
        ImageIO.write(image, "png", response.getOutputStream());

    }
}
