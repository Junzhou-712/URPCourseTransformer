package util;
import org.apache.commons.codec.digest.DigestUtils;

public class Encrypt {
    /**
    * @ClassName: Encrypt
    * @Description: password encrypto algorithm
    * @author: joe_su
    * @date: 1:12 PM 2022/4/29
    */
    public static String digestString(String str) {
        String newStr = null;
        newStr = DigestUtils.md5Hex(str);
        return newStr;
    }
}
