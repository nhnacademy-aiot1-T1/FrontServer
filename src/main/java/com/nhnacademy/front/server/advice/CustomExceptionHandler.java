package com.nhnacademy.front.server.advice;

import com.nhnacademy.front.server.exception.LoginFailedException;
import com.nhnacademy.front.server.exception.NotFoundTokenException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Objects;

/**
 * 각각의 커스텀 예외의 대한 설정입니다.
 *
 * @version 1.0
 */
@ControllerAdvice
@Slf4j
public class CustomExceptionHandler {
    private static final String REDIRECT = "redirect:";

    /**
     * 로그인 실패에 대해 발생한 예외를 처리해주는 advice 입니다.
     *
     * @param loginFailedException 발생한 예외의 정보를 담고 있습니다.
     * @param redirectAttributes   Redirect 된 페이지에 정보를 전달 하기 위해 사용합니다.
     * @return 요청을 전달할 페이지를 로드합니다.
     */
    @ExceptionHandler(value = { LoginFailedException.class })
    public String handleLoginFailException(LoginFailedException loginFailedException, RedirectAttributes redirectAttributes) {

        redirectAttributes.addFlashAttribute("error", loginFailedException.getMessage());
        return REDIRECT + "pages/auth/login";
    }

    /**
     * 입력된 값이 spring validation에 적합하지 않았을 때 발생하는 예외를 처리합니다.
     *
     * @param ex    예외의 대한 정보를 가지고 있습니다!
     * @param model 전달된 페이지에 정보를 주기 위한 모델입니다!
     * @return 전달할 페이지를 리턴합니다!
     */
    @ExceptionHandler(value = { BindException.class }) // todo, 수정해야 함,
    public String handleValidationException(BindException ex, Model model) {
        if (ex.getBindingResult().getObjectName().equals("registerCheckDto")) {
            model.addAttribute("message", Objects.requireNonNull(ex.getFieldError()).getDefaultMessage());
            return "pages/auth/register";
        }
        log.warn(ex.getMessage());
        log.warn(ex.getBindingResult().toString());
        return null;
    }

}
