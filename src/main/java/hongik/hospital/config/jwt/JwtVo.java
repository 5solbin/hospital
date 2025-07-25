package hongik.hospital.config.jwt;

public interface JwtVo {

    public static final String SECRET = "홍익병원"; // Hs256 (대칭키)
    public static final int EXPIRATION_TIME = 1000 * 60 * 60 * 24 * 7;  // 일주일
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER = "Authorization";
}
