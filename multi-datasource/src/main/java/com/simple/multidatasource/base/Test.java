package com.simple.multidatasource.base;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import org.springframework.scheduling.annotation.AsyncResult;


/**
 * @author czd
 */
public class Test {
    /**
     * 加解密时所需的密码，内部使用可以直接写死，外部调用动态传输可以直接在形参中定义
     */
    private static final String password ="gjh%^&(&  {}77";

    /**
     * 使用AES算法进行加密
     * @param str
     * @return
     */
    public static String jdkAESEncode(String str){
        try {
            //生成key
            KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
            keyGenerator.init(128,new SecureRandom(password.getBytes()));
            //生成密钥
            SecretKey secretKey = keyGenerator.generateKey();
            byte[] bytes = secretKey.getEncoded();
            //key转换
            Key key = new SecretKeySpec(bytes ,"AES");
            //加密
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE,key);
            byte [] result = cipher.doFinal(str.getBytes());
            String string = Base64.encode(result);
            return string;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public static String jdkAESDecode(String str){
        try {
            //生成key
            KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
            keyGenerator.init(128,new SecureRandom(password.getBytes()));
            //生成密钥
            SecretKey secretKey = keyGenerator.generateKey();
            byte[] bytes = secretKey.getEncoded();
            //key转换
            Key key = new SecretKeySpec(bytes ,"AES");
            //解密
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE,key);
            byte [] bytesContent = Base64.decode(str);
            byte [] result = cipher.doFinal(bytesContent);
            return new String(result);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }


    public static void main(String[] args) throws ExecutionException, InterruptedException {
//        String str = jdkAESEncode("12345");
//        System.out.println(str);
//        System.out.println(jdkAESDecode(str));
        ExecutorService executorService = Executors.newFixedThreadPool(4);
        ArrayList<Future<Integer>> futures = new ArrayList<>();
        List<Integer> nums = Arrays.asList(1, 2, 3, 4, 5);

        for (Integer num : nums) {
            Future<Integer> submit = executorService.submit(() -> {
                System.out.println(Thread.currentThread().getName());
                System.out.println(111);
                Thread.sleep(3000);
                return num;
            });
            futures.add(submit);
        }

            for (Future<Integer> future : futures) {
                Integer integer = future.get();
                System.out.println(integer);
            }
            System.out.println("end");

//            new Thread(() -> System.out.println(i)).start();
        }

}
class Mine{
    public static void main(String[] args) {
        System.out.println(111);
    }
}

