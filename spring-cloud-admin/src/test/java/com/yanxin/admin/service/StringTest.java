package com.yanxin.admin.service;

import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPath;
import com.alibaba.fastjson.support.spring.JSONPResponseBodyAdvice;
import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.junit.Test;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @program spring-cloud-example
 * @description:
 * @author: LiuYanXin
 * @create: 2021-05-24 11:44
 */
public class StringTest {

    @Test
    public void testContains() {

        String charStr = "commit,society,human";

        String value = StrUtil.subBefore(charStr, ",", false);

        System.out.println(value);

    }

    @Test
    public void replaceTest() {

        // target ip:192.136,182.394
        List<String> result = new ArrayList<>();
        String filed = "ip,b";
        // String charStr = "ip:192.168.3.58,192.168.3.51,b:234";
        String charStr = "ip:192.168.3.58,192.168.3.51,b:234";

        List<String> esList = Stream.of(filed.split(",")).collect(Collectors.toList());
        for (String str : esList) {
            String value = StrUtil.subAfter(charStr, str, false);
            String subValue = StrUtil.subAfter(value, ",", true);
            if (StrUtil.isNotEmpty(subValue) && StrUtil.contains(subValue, ':')) {

                System.out.println("final: " + str + StrUtil.subBefore(value, "," + subValue, false));
            } else {
                System.out.println("final: " + str + value);
            }
        }
    }

    @Test
    public void listStrTest() {

        String filed = "ip,b";
        List<String> valueList = Stream.of(filed.split(",")).collect(Collectors.toList());
        System.out.println(JSONObject.toJSONString(valueList));

    }

    @Test
    public void idTest() {

        System.out.println(IdUtil.createSnowflake(31L, 31L).nextId());
    }

    @Test
    public void intTest() {

        Pattern pattern = Pattern.compile("断言");
        Matcher match = pattern.matcher("断言");
        Assert.assertTrue(match.find());
    }

    @Test
    public void getPathNameTest() {

        String str = "bucketName=dpa&fileName=/dpa/public/test/123.jpg&girl=zhangxiang";
        List<String> params = Stream.of(StrUtil.split(str, "&")).collect(Collectors.toList());
        for (String param : params) {
            if (StringUtils.contains(param, "fileName")) {
                System.out.println(StrUtil.split(param, "=")[1]);
            }
        }
    }


    @Test
    public void jsonTest() {

        // String str = "{\"a\":\"a\",\"b\":[{\"c\":\"c\"},{\"d\":\"d\"}]}";
        String str = "a";
        final Object parse = JSONObject.parse(str);
        JSONPath.set(parse, "$['a']", "b");

        System.out.println(parse.toString());

    }

    @Test
    public void signParam() throws UnsupportedEncodingException, NoSuchAlgorithmException, InvalidKeyException {
        final String HTTP_METHOD = "GET";
        Map<String, Object> parameters = new HashMap();
        // 输入请求参数
        parameters.put("count", "1");
        parameters.put("vxnets.1", "vxnet-0");
        parameters.put("zone", "pek3a");
        parameters.put("instance_type", "small_b");
        parameters.put("signature_version", "1");
        parameters.put("signature_method", "HmacSHA256");
        parameters.put("instance_name", "demo");
        parameters.put("image_id", "centos64x86a");
        parameters.put("login_mode", "passwd");
        parameters.put("login_passwd", "QingCloud20130712");
        parameters.put("version", "1");
        parameters.put("access_key_id", "QYACCESSKEYIDEXAMPLE");
        parameters.put("action", "RunInstances");
        // parameters.put("time_stamp", formatIso8601Date(new Date()));
        parameters.put("time_stamp", "2013-08-27T14:30:10Z");
        // 排序请求参数
        String[] sortedKeys = parameters.keySet().toArray(new String[]{});
        Arrays.sort(sortedKeys);

        final String SEPARATOR = "\\n";
        // 构造 stringToSign 字符串
        StringBuilder signStr = new StringBuilder();
        signStr.append(HTTP_METHOD).append(SEPARATOR)
                .append("/iaas/").append("\\n");

        StringBuilder queryString = new StringBuilder();
        for(String key : sortedKeys) {
            // 这里注意编码 key 和 value
            queryString.append("&")
                    .append(percentEncode(key)).append("=")
                    .append(percentEncode((String) parameters.get(key)));
        }
        // 这里注意编码
        signStr.append(queryString.substring(1));
        /*signStr.append(percentEncode(
                queryString.toString().substring(1)));*/

        // 以下是一段计算签名的示例代码
        final String ALGORITHM = "HmacSHA256";
        final String ENCODING = "UTF-8";
        String secretKey = "SECRETACCESSKEY";

        SecretKeySpec signingKey = new SecretKeySpec(secretKey.getBytes(),
                ALGORITHM);
        Mac mac = Mac.getInstance(ALGORITHM);
        mac.init(signingKey);

        byte[] signData = mac.doFinal(signStr.toString().getBytes());
        String signature = new String(Base64.getEncoder().encode(signData));

        // .replaceAll(" ", "+");
        signature = URLEncoder.encode(signature, ENCODING);
        System.out.println(signature);

    }

    private static final String ENCODING = "UTF-8";
    private static String percentEncode(String value) throws UnsupportedEncodingException {
        return value != null ? URLEncoder.encode(value, ENCODING)
                // .replace("%7E", "~")  .replace("*", "%2A")
                .replace(":", "%3A")
                .replace("+", "%20")
                : null;
    }

    private static final String ISO8601_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss'Z'";
    private static String formatIso8601Date(Date date) {
        SimpleDateFormat df = new SimpleDateFormat(ISO8601_DATE_FORMAT);
        df.setTimeZone(new SimpleTimeZone(0, "GMT"));
        System.out.println(df.format(date));
        return df.format(date);
    }
}
