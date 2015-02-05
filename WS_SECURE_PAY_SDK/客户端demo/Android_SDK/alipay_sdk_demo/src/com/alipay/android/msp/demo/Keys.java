/*
 * Copyright (C) 2010 The MobileSecurePay Project
 * All right reserved.
 * author: shiqun.shi@alipay.com
 * 
 *  提示：如何获取安全校验码和合作身份者id
 *  1.用您的签约支付宝账号登录支付宝网站(www.alipay.com)
 *  2.点击“商家服务”(https://b.alipay.com/order/myorder.htm)
 *  3.点击“查询合作者身份(pid)”、“查询安全校验码(key)”
 */

package com.alipay.android.msp.demo;

//
// 请参考 Android平台安全支付服务(msp)应用开发接口(4.2 RSA算法签名)部分，并使用压缩包中的openssl RSA密钥生成工具，生成一套RSA公私钥。
// 这里签名时，只需要使用生成的RSA私钥。
// Note: 为安全起见，使用RSA私钥进行签名的操作过程，应该尽量放到商家服务器端去进行。
public final class Keys {

	//合作身份者id，以2088开头的16位纯数字
	public static final String DEFAULT_PARTNER = "2088511672368605";

	//收款支付宝账号
	public static final String DEFAULT_SELLER = "xiaopcorp@163.com";

	//商户私钥，自助生成
	public static final String PRIVATE = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAO4Rwz4ZXoqVaIMm"+
									"rPrxWxHGR+6CXq3YBpeEy8ZlivoTyYyws9J/DlGbELPFWUbFVet+Xu5cRUwnmzVJ"+
									"yVF3vSfa94fklTfAi6drheSh6qByPJFrHC6tHmzXAbLgHNzhy0tBc4N6inhNp6i0"+
									"Tz3GEfJVnh4pRnmTMZu0a/POK78tAgMBAAECgYAhKXZB6zTBJyjExBlqZz5hTnZs"+
									"odpgauwYJRZhj3l5dlIn9+FEnBA5i9kawY27Dvf18sd0eoPopMe494tEfuJpKw21"+
									"ZQYjOgCQQrTcmRS+rRYE9kAjWVoEVF+Z57LK/gbdlsOSf/7ZYIcgHxM8jeJ8e94b"+
									"nvwDO+GItfNiR8QbYQJBAPomojp/OtIpPJRdVOq92Yep6SF81gF1dkBG4wa61Jf6"+
									"LuRlF7YyK+VYxFuz1xpsThJTBpq2D6YaLcP+X2RMRTkCQQDzos+L3oNOk3v+seev"+
									"u+mqa5CVh9uksflMR2GT38FuFGxva244AqycV0lZfo5DWxgnEOPfOYECEuXkMuiJ"+
									"cB2VAkEAlHHWtZYI5uxXqceU+9R7wTkAQsIdETKOYOhwEu9N934c06Z2IsW1LN23"+
									"cJ/RVznMPq2MbsT5ll1UqUlLfG6SaQJAWQM64NdDEgpAWyWUiFgXXvQ9tuQUE3AS"+
									"rRPHSseNXDKKC4sskbooZlZdrhzWzTUS9fLtzjisMZ3F8EHJQ616oQJBANOvYl7l"+
									"EfVeHEK741E2JBQlVpD5XGph9HKXADKVwXbNMYZLuvTdsLGG9oX/adzOf27x01d/"+
									"WVF8wWGFGF+PtPo=";

	public static final String PUBLIC = "biv2rp0qgk9yikkvvr3w5w2fka8hm32w";
	
//	public static final String PUBLIC = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCnxj/9qwVfgoUh/y2W89L6BkRAFljhNhgPdyPuBV64bfQNN1PjbCzkIM6qRdKBoLPXmKKMiFYnkd6rAoprih3/PrQEB/VsW8OoM8fxn67UDYuyBTqA23MML9q1+ilIZwBC2AQ2UBVOrFXfFl75p6/B5KsiNG9zpgmLCUYuLkxpLQIDAQAB";

}
