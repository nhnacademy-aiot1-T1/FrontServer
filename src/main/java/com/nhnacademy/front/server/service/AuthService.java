package com.nhnacademy.front.server.service;


/**
 * authController의 서비스 부분을 처리하는 class입니다!
 * @author AoiTuNa
 * @version 1.2
 * @see #getLoginToken(String, String, String)
 * @see #tokenLogout(String)
 */
public interface AuthService {

    /**
     * 로그인 토큰을 얻어오기 위한 서비스 메서드입니다!
     * @param id 유저가 입력한 emial값입니다!
     * @param password 유저가 입력한 password 값 입니다!
     * @param userAddress 유저의 IpAddress 주소를 담은 값 입니다!
     * @return 인증된 유저의 accessToken을 String으로 래핑해서 리턴합니다. 만약 없다면 null을 반환합니다!
     */
    String getLoginToken(String id, String password,String userAddress);

    /**
     * 로그아웃을 위한 메서드 입니다!
     * @param token 로그아웃을 위한 유저의 토큰정보를 보냅니다!
     */
    void tokenLogout(String token);


}
