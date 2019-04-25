package lambda.vo;



import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import com.sun.org.apache.xml.internal.security.exceptions.Base64DecodingException;
import com.sun.org.apache.xml.internal.security.utils.Base64;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;


/**
 * AES 是一种可逆加密算法，对用户的敏感信息加密处理 对原始数据进行AES加密后，在进行Base64编码转化；
 *     private static String sKey = "TEST54321!@#";
    private static String ivParameter = "12345678901234ad";
    AES/CBC/PKCS5Padding
 */
public class UsualAES {

    /*
     * 加密用的Key 可以用26个字母和数字组成 此处使用AES-128-CBC加密模式，key需要为16位。
     */
    private static String sKey = "TEST54321!@#";
    private static String ivParameter = "12345678901234ad";
    private static UsualAES instance = null;

    private UsualAES() {

    }

    public static UsualAES getInstance() {
        if (instance == null)
            instance = new UsualAES();
        return instance;
    }
    
//public static String Encrypt(String encData ,String secretKey,String vector) throws Exception {
//        
//        if(secretKey == null) {
//            return null;
//        }
//        if(secretKey.length() != 16) {
//            return null;
//        }
//        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
//        byte[] raw = secretKey.getBytes();
//        SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
//        IvParameterSpec iv = new IvParameterSpec(vector.getBytes());// 使用CBC模式，需要一个向量iv，可增加加密算法的强度
//        cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
//        byte[] encrypted = cipher.doFinal(encData.getBytes("utf-8"));
//        return new BASE64Encoder().encode(encrypted);// 此处使用BASE64做转码。
//    }


    // 加密
    public String encrypt(String sSrc,String key) throws Exception {
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        byte[] raw = key.getBytes();
        SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
        IvParameterSpec iv = new IvParameterSpec(ivParameter.getBytes());// 使用CBC模式，需要一个向量iv，可增加加密算法的强度
        cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
        byte[] encrypted = cipher.doFinal(sSrc.getBytes("utf-8"));
        return new BASE64Encoder().encode(encrypted);// 此处使用BASE64做转码。
    }

    // 解密
//    public String decrypt(String sSrc) throws Exception {
//        try {
//            byte[] raw = sKey.getBytes("ASCII");
//            SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
//            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
//            IvParameterSpec iv = new IvParameterSpec(ivParameter.getBytes());
//            cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
//            byte[] encrypted1 = new BASE64Decoder().decodeBuffer(sSrc);// 先用base64解密
//            byte[] original = cipher.doFinal(encrypted1);
//            String originalString = new String(original, "utf-8");
//            return originalString;
//        } catch (Exception ex) {
//            return null;
//        }
//    }
    
    public String decrypt(String sSrc,String key,String ivs) throws Exception {
        try {
            byte[] raw = key.getBytes("ASCII");
            SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            IvParameterSpec iv = new IvParameterSpec(ivs.getBytes());
            cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
            byte[] encrypted1 = new BASE64Decoder().decodeBuffer(sSrc);// 先用base64解密
            byte[] original = cipher.doFinal(encrypted1);
            String originalString = new String(original, "utf-8");
            return originalString;
        } catch (Exception ex) {
            return null;
        }
    }
    
    public static String encodeBytes(byte[] bytes) {
        StringBuffer strBuf = new StringBuffer();

        for (int i = 0; i < bytes.length; i++) {
            strBuf.append((char) (((bytes[i] >> 4) & 0xF) + ((int) 'a')));
            strBuf.append((char) (((bytes[i]) & 0xF) + ((int) 'a')));
        }

        return strBuf.toString();
    }

    /*增强加密
     * 密码为原始12位+4位随机数，随机数带入密文中
     * */
    public static String encryptAd(String content,String password ){
      int  num=(int) (Math.random()*9000+1000);
      String newPassword=password+num;
      //加密   
     System.out.println("加密前：" + content);   
     String tt4=null;
	try {
		tt4 = UsualAES.getInstance().encrypt(content,newPassword);
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    
     tt4=tt4.replace('+', '[');
     tt4=tt4.replace('/', ']');
     String  result =tt4+num;
     return result;
    }
    
    /*增强解密
     * 明文[0,15] 位：密文为24 +4=28 位   明文[16,31] 位：密文为44 +4=48 位  
     * */
    public static String decryptAd(String data ) throws Base64DecodingException, IOException{
  	  data= data.replace('[', '+');
  	  data=data.replace(']', '/');
  	  int len=data.length();
  	 
           String  realkey=sKey+data.substring(len-4,len);
           String  realcontent=data.substring(0,len-4);
           String result=null;
		try {
			result = UsualAES.getInstance().decrypt(realcontent,realkey,ivParameter);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
          
     
       return result;
      }
    public static void main(String[] args) throws Exception {
        // 需要加密的字串
        String cSrc = "abc";
        String password = "TEST54321!@#";  
        // 加密
       
        String enString =encryptAd(cSrc,password);
        System.out.println("加密后的字串是：" + enString);

        // 解密
       
        String DeString = decryptAd("5z9WEequVr7qtd+WoxV+Kw==");
        System.out.println("解密后的字串是：" + DeString);
       // lUseTime = System.currentTimeMillis() - lStart;
       
    }

}