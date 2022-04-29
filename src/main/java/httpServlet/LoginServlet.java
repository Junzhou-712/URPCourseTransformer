package httpServlet;
import httpclient.LoginHttpClient;
import util.CaptchaDownloader;
import util.JsoupUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

/**
 * @author: joe_su
 * @date: 2:24 PM 04-29-2022
 */
@WebServlet("/system")
public class LoginServlet extends HttpServlet{
    /**
    * @ClassName: LoginServlet
    * @Description: serve as the client to send the POST request and receive the msg
    * @author: joe_su
    * @date: 2:24 PM 2022/4/29
    */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userId = request.getParameter("userId");
        String passWord = request.getParameter("passWord");
        String captcha = request.getParameter("captcha");
        System.out.println("账号：" + userId);
        System.out.println("密码：" + passWord);
        System.out.println("验证码：" + captcha);

        LoginHttpClient httpClient = new LoginHttpClient();
        String webData = httpClient.loginSystem(userId,passWord,captcha);
        //read the inner msg
        if("failed".equals(webData)) {
            String data = "登录失败，请检查输入信息是否正确";
            OutputStream outputStream = response.getOutputStream();
            response.setHeader("content-type","text/html;charset=UTF-8");
            byte[] dateByteArray = data.getBytes(StandardCharsets.UTF_8);
            outputStream.write(dateByteArray);
        } else {
            //login successfully
            response.setHeader("content-type","text/html;charset=UTF-8");
            PrintWriter pw = response.getWriter();
            pw.write(JsoupUtil.courseTableExtract(webData));
        }
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CaptchaDownloader captchaDownloader = new CaptchaDownloader();
        captchaDownloader.downloadCaptcha(request,response);
    }
}
