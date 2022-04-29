/**
 * Description: Test class for MD5
 *
 * @author: joe_su
 * @date: 1:27 PM 04-29-2022
 */
import org.junit.Test;
import util.Encrypt;

public class EncryptTest {
    Encrypt en = new Encrypt();
    //test password encrypto md5
    @Test
    public void testMd5() {
        String p1 = "123456";
        System.out.println(en.digestString(p1));
        //checked
    }
}
