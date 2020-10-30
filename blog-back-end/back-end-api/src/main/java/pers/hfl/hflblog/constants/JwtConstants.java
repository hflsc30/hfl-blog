package pers.hfl.hflblog.constants;

/**
 * @ClassName JwtConstants
 * @Author HFL
 * @Date 2020/10/30/030 15:28
 * @Version V1.0
 **/
public class JwtConstants {
    public static final String ROLE_CLAIMS = "role";
    /**
     * 过期时间：24小时
     */
    public static final long EXPIRATION = 60 * 60*24L;
    /**
     * 私钥
     */
    public static final String JWT_SECRET_KEY = "123456";
    /**
     * JWT 前缀
     */
    public static final String TOKEN_PREFIX = "zhulinbimo_";
    /**
     * token 类型
     */
    public static final String TOKEN_TYPE = "JWT";
    /**
     * 签发者
     */
    public static final String TOKEN_ISSUER = "HFL";
    /**
     * 加在响应头的哪个字段
     */
    public static final String TOKEN_HEADER = "Authorization";
}
