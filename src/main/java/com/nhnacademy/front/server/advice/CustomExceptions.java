package com.nhnacademy.front.server.advice;

import com.nhnacademy.front.server.exception.JsonParseFailException;
import com.nhnacademy.front.server.exception.LoginFailedException;
import com.nhnacademy.front.server.exception.LogoutNotFoundTokenException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@ControllerAdvice
@Slf4j
public class CustomExceptions {

  @ExceptionHandler(value = JsonParseFailException.class)
  public String jsonParserFailActivePage(JsonParseFailException parseFailException, Model model){
    if(parseFailException.getOriginClass().equals("AuthAdapterImpl")){
      model.addAttribute("message",parseFailException.getMessage());
      return "pages/auth/register";
    }
    return null;
  }

  @ExceptionHandler(value = LoginFailedException.class)
  public String showLoginFailedReason(LoginFailedException loginFailedException, RedirectAttributes redirectAttributes){

      redirectAttributes.addFlashAttribute("error",loginFailedException.getMessage());
      return "redirect:pages/auth/login";
  }

  @ExceptionHandler(value = LogoutNotFoundTokenException.class)
  public String logoutFailedReason(LoginFailedException loginFailedException,RedirectAttributes redirectAttributes){
    redirectAttributes.addAttribute("error",loginFailedException.getMessage());
    return "redirect:/pages/auth/login";
  }
}
