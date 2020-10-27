package pers.hfl.hflblog.global;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;
import pers.hfl.hflblog.exception.BlogException;
import pers.hfl.hflblog.model.enums.Impl.ErrorInfoEnum;
import pers.hfl.hflblog.model.vo.Results;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName GlobalExceptionHandler
 * @Author HFL
 * @Date 2020/10/23/023 16:35
 * @Version V1.0
 * <p>
 * 注解说明：
 * @Slf4j lombok 注解，自动注入log对象到容器，可以使用log对象打印日志
 * @ControllerAdvice 表示这个类用于全局异常处理
 * @ExceptionHandler 表示此类处理什么类型的异常
 * @ResponseStatus 返回的http状态码
 * </p>
 **/
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 自定义异常处理
     *
     * @param exception 自定义异常
     * @return 返回自定义异常信息
     */
    @ResponseBody
    @ExceptionHandler(value = BlogException.class)
    @ResponseStatus(HttpStatus.OK)
    public Results blogExceptionHandler(BlogException exception) {
        log.error("BlogException:{}", exception.getMessage());
        return exception.toResultVO();
    }

    /**
     * 处理找不到资源
     *
     * @param exception 异常
     * @return 返回异常信息
     */
    @ResponseBody
    @ExceptionHandler(value = NoHandlerFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Results noHandlerFoundExceptionHandler(NoHandlerFoundException exception) {
        log.error("NoHandlerFoundException:{}", exception.getMessage());
        return Results.fromErrorInfo(ErrorInfoEnum.RESOURCES_NOT_FOUND);
    }

    /**
     * 处理未知错误
     *
     * @param exception 异常
     * @return 返回异常信息
     */
    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    @ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
    public Results exceptionHandler(Exception exception) {
        exception.printStackTrace();
        log.error("Exception:{}", exception.getMessage());
        return Results.fromErrorInfo(ErrorInfoEnum.UNKNOWN_ERROR);
    }

    /**
     * 处理参数校验失败异常
     *
     * @param e 异常
     * @return 返回异常信息
     */
    @ResponseBody
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.OK)
    public Results handleValidationExceptions(MethodArgumentNotValidException e) {
        Map<String, String> errors = new HashMap<>();
        e.getBindingResult().getAllErrors().forEach((error) -> {
            String fileName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fileName, errorMessage);
        });
        return Results.error("参数错误", errors);
    }
}
