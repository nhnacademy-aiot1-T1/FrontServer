//package com.nhnacademy.front.server.advice;
//
//
//import lombok.extern.slf4j.Slf4j;
//import org.json.JSONObject;
//import org.springframework.http.HttpStatus;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.client.HttpClientErrorException;
//import org.springframework.web.servlet.mvc.support.RedirectAttributes;
//
//import javax.servlet.http.HttpServletRequest;
//
//
///**
// * 4xx번대 요청이 왔을 때 알맞은 처리를 위해서 만들어진 advice입니다!
// *
// * @author AoiTuNa
// * @version 1.1
// * @see #clientErrorState(HttpClientErrorException, RedirectAttributes, HttpServletRequest)
// */
//@ControllerAdvice
//@Slf4j
//public class HttpExceptions {
//    private static final String MESSAGE_NAME = "message";
//
//    /**
//     * 4xx 번대 에러인 HttpClientException을 받아서 처리하는 advice의 로직입니다!<br/>
//     * 400번 badRequest 401번 unauthorized status를 처리합니다!<br/>
//     * 각 예외의 message 부분의 값을 기준으로 각 예외의 발생 위치와 처리를 규정합니다!
//     *
//     * @param exception          해당하는 HttpClientException입니다!
//     * @param redirectAttributes 다시 연결된 html page에 flash를 추가하기 위한 코드입니다
//     * @return 403 요청이 들어오면 인증되지 않은 코인이라는 메시지를 flash로 보내 login 화면으로 redirect 합니다!
//     */
//    @ExceptionHandler(value = { HttpClientErrorException.class }) // todo, error code 마다 분리.
//    public String clientErrorState(HttpClientErrorException exception, RedirectAttributes redirectAttributes, HttpServletRequest httpServletRequest) {
//        String requestUri = httpServletRequest.getRequestURI();
//        log.info("request URL :" + requestUri);
//        if (exception.getStatusCode() == HttpStatus.BAD_REQUEST) {
//            if (requestUri.equals("http://GATEWAY-SERVICE/api/regenerate")) {
//                redirectAttributes.addFlashAttribute(MESSAGE_NAME, "허용되지 않은 토큰입니다!! 다시 로그인 해주세요!");
//                return "redirect:pages/error/403";
//            }
//        }
//        if (exception.getStatusCode() == HttpStatus.UNAUTHORIZED) {
//            if (requestUri.equals("http://GATEWAY-SERVICE/api/regenerate")) {
//                redirectAttributes.addFlashAttribute(MESSAGE_NAME, "accessToken의 정보가 일치하지 않습니다!");
//                return "redirect:pages/auth/login";
//            } else if (requestUri.equals("http://GATEWAY-SERVICE/api/auth/login")) {
//                redirectAttributes.addFlashAttribute(MESSAGE_NAME, "해당하는 유저가 존재하지 않습니다! ID, PW를 확인하세요!");
//                return "redirect:pages/auth/login";
//            }
//        }
//        //추가
//        return "pages/error/403";
//    }
//
//}
