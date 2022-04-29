package util;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @author: joe_su
 * @date: 2:29 PM 04-29-2022
 */
public class CaptchaDownloader {
    /**
    * @ClassName: CaptchaDownloader
    * @Description: download the captcha image to the local and recognize it manually
    * @author: joe_su
    * @date: 2:29 PM 2022/4/29
    */
    public void downloadCaptcha(HttpServletRequest request, HttpServletResponse response) {
        try {
            //img path
            URL url = new URL("http://zhjw.scu.edu.cn/img/captcha.jpg");
            //create a httpClient object
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(5*1000);
            InputStream inputStream = conn.getInputStream();
            byte[] data = readInputStream(inputStream);
            inputStream.read(data);
            inputStream.close();
            response.setContentType("image/jpg"); // set return file type
            OutputStream outputStream = response.getOutputStream();
            outputStream.write(data);
            outputStream.flush();
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static byte[] readInputStream(InputStream inputStream) throws Exception {
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[2048];
        int len = 0;
        while((len = inputStream.read(buffer)) != -1) {
            outStream.write(buffer,0,len);
        }
        inputStream.close();
        return outStream.toByteArray();
    }
}
