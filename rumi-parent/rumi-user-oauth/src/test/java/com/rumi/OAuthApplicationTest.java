package com.rumi;

import com.alibaba.fastjson.JSON;
import junit.framework.TestCase;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.jwt.Jwt;
import org.springframework.security.jwt.JwtHelper;
import org.springframework.security.jwt.crypto.sign.RsaSigner;
import org.springframework.security.jwt.crypto.sign.RsaVerifier;
import org.springframework.security.rsa.crypto.KeyStoreKeyFactory;

import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.interfaces.RSAPrivateKey;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName: OAuthApplicationTest
 * @Description:
 * @Author: CSH
 * @Date: 2025-05-18 16:02
 */
@SpringBootTest
public class OAuthApplicationTest extends TestCase {

    @Test
    public void testMain() {
        ClassPathResource resource = new ClassPathResource("changgou.jks");

        KeyStoreKeyFactory keyStoreKeyFactory = new KeyStoreKeyFactory(resource, "changgou".toCharArray());

        KeyPair changgou = keyStoreKeyFactory.getKeyPair("changgou", "changgou".toCharArray());

        RSAPrivateKey aPrivate = (RSAPrivateKey)changgou.getPrivate();

        //定义Payload
        Map<String, Object> tokenMap = new HashMap<>();
        tokenMap.put("id", "1");
        tokenMap.put("name", "itheima");
        tokenMap.put("roles", "ROLE_VIP,ROLE_USER");

        //生成Jwt令牌
        Jwt jwt = JwtHelper.encode(JSON.toJSONString(tokenMap), new RsaSigner(aPrivate));

        //取出令牌
        String encoded = jwt.getEncoded();
        System.out.println(encoded);
    }

    @Test
    public void testParseToken(){
        //令牌
        String token = "eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJyb2xlcyI6IlJPTEVfVklQLFJPTEVfVVNFUiIsIm5hbWUiOiJpdGhlaW1hIiwiaWQiOiIxIn0.HnD_h6J1eXazT8BBFrf3H85MMav5gkR3tSVggUJ5cK1H93fEcBk-y77kHDSuyu13mFb2-eMoCIwJb-VUYFXZ2yb87sNCpkS8Ps2-7KXm6hG1PuFxSxMn3oS4j87K-pGdCabD-zHZOpxEBSC68TUMUp2t7hgVbPkCNoYO7jU19J6352IjNMeExCJcBaJlGHRsAQbgiIm1fTEWk6gg9PCWFYl68J3yiunODl3DXZYUBjtseLkDGrCnclSvRHFiXZ3qB16c2L1n6sqTgOItXY1e7TdJRP1R7SccVyorKCbGeZC7tk21LOadcKDG2ojF--LKNFlsKdBpV5rOV5KuLZy1Ww";
        //公钥
        String publickey = "-----BEGIN PUBLIC KEY-----MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAmvsQHQOcDqbzO+f9L2aAbdh26L3wK4bh3FhQhfjgDViJJpnW8KVWsiwYRrGrWwzUcr2/3K8r0I0Go5ycCdCJqso0vG+xFukX78Ut+dFf8iiTQcQCzj7hROJfWyzMghW3IaJqlpRFDMPMXsN/EqjQ7swsNQBi0hKJdPblSsGtDT5IRdOgsQ0e/jUPcsSg+9Er2Kj9a+SsplkHnhrCChW+kMMpr06VNs/H1FvjybBg2eKKPmK0c4EFhUCweWNcSJhn3l6OgHKnp/0qxpVUSRTsC6CtdzdpJShrHF2Wf1NlhrBQp/7CdIGK4z5twyRJrM6XwMcOfp0rsJgLrv7fSnVVTwIDAQAB-----END PUBLIC KEY-----";

        //校验Jwt
        Jwt jwt = JwtHelper.decodeAndVerify(token, new RsaVerifier(publickey));

        //获取Jwt原始内容
        String claims = jwt.getClaims();
        System.out.println(claims);
        //jwt令牌
        String encoded = jwt.getEncoded();
        System.out.println(encoded);
    }

}