package com.github.anhTom2000.web.exceptionHandler;

import com.github.anhTom2000.dto.ResultDTO;
import com.github.anhTom2000.exceptions.UnLoginException;
import com.github.anhTom2000.utils.httpcode.Httpcode;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

/**
 * @Description : TODO  全局的异常处理器
 * @Author : Weleness
 * @Date : 2020/05/14
 */
@ControllerAdvice
public class ApplicationExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public ResultDTO validationError(MethodArgumentNotValidException ex) {
        // 获取绑定的异常信息
        BindingResult bindingResult = ex.getBindingResult();
        ResultDTO resultDTO = null;
        if (bindingResult.hasErrors()) {
            List<ObjectError> errors = bindingResult.getAllErrors();
            if (!errors.isEmpty()) {
                // 这里列出了全部错误参数，按正常逻辑，只需要第一条错误即可
                FieldError fieldError = (FieldError) errors.get(0);
                resultDTO = ResultDTO.builder().code(Httpcode.CLIENT_ERROR_CODE.getCode()).message(fieldError.getDefaultMessage()).status(false).build();
            }
        } else
            resultDTO = ResultDTO.builder().code(Httpcode.CLIENT_ERROR_CODE.getCode()).message("请求参数错误").status(false).build();

        return resultDTO;
    }

    @ExceptionHandler(UnLoginException.class)
    @ResponseBody
    public ResultDTO userUnLoginError(Exception ex) {
        return ResultDTO.builder().code(Httpcode.SERVER_ERROR_CODE.getCode()).message(ex.getMessage()).status(false).build();
    }


    @ExceptionHandler(NotFoundException.class)
    @ResponseBody
    public ResultDTO notFoundError(Exception ex) {
        return ResultDTO.builder().code(Httpcode.SERVER_ERROR_CODE.getCode()).message(ex.getMessage()).status(false).build();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    @ExceptionHandler(Throwable.class)
    public ResultDTO runTimeException(Throwable t){
         return ResultDTO.builder().code(Httpcode.SERVER_ERROR_CODE.getCode()).message(t.getMessage()).status(false).build();
    }

}
