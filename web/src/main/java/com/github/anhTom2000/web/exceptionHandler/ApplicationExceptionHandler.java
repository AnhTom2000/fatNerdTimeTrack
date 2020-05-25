package com.github.anhTom2000.web.exceptionHandler;

import com.github.anhTom2000.dto.ResultDTO;
import com.github.anhTom2000.exceptions.NoFoundException;
import com.github.anhTom2000.exceptions.UnLoginException;
import com.github.anhTom2000.utils.httpcode.Httpcode;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Description : TODO  全局的异常处理器
 * @Author : Weleness
 * @Date : 2020/05/14
 */
@ControllerAdvice
public class ApplicationExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BindException.class)
    @ResponseBody
    public ResultDTO validationError(BindException ex) {
        String message = ex.getBindingResult().getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.joining());
        return new ResultDTO(Httpcode.CLIENT_ERROR_CODE.getCode(),message,false);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseBody
    public ResultDTO ConstraintViolationExceptionHandler(ConstraintViolationException e) {
        String message = e.getConstraintViolations().stream().map(ConstraintViolation::getMessage).collect(Collectors.joining());
        return new ResultDTO(Httpcode.CLIENT_ERROR_CODE.getCode(),message,false);
    }

    //处理请求参数格式错误 @RequestBody上validate失败后抛出的异常是MethodArgumentNotValidException异常。
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public ResultDTO MethodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {
        String message = e.getBindingResult().getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.joining());
        return new ResultDTO(Httpcode.CLIENT_ERROR_CODE.getCode(),message,false);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(UnLoginException.class)
    @ResponseBody
    public ResultDTO userUnLoginError(Exception ex) {
        return ResultDTO.builder().code(Httpcode.SERVER_ERROR_CODE.getCode()).message(ex.getMessage()).status(false).build();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(NoFoundException.class)
    @ResponseBody
    public ResultDTO notFoundError(Exception ex) {
        return ResultDTO.builder().code(Httpcode.CLIENT_ERROR_CODE.getCode()).message(ex.getMessage()).status(false).build();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    @ExceptionHandler(Throwable.class)
    public ResultDTO runTimeException(Throwable t){
         return ResultDTO.builder().code(Httpcode.CLIENT_ERROR_CODE.getCode()).message(t.getMessage()).status(false).build();
    }

}
