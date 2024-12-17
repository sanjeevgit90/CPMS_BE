package aurionpro.erp.ipms.utility;

import net.bytebuddy.utility.RandomString;

public class StringUtil {

    public static String randomPasswordString(int length){
        return RandomString.make(length);
    }
    
}