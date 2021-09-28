package hello.exception.resolver;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class MyHandlerExceptionResolver implements HandlerExceptionResolver {
    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {

        try {
            if (ex instanceof IllegalArgumentException) {
                log.info("IllegalArgumentException resolver to 400");
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, ex.getMessage());
                return new ModelAndView();
                //예외를 먹어버리고, 빈 ModelAndView를 반환하면 정상처리 가능.
                //return return -> Servlet까지 올라감.
                //servlet은 sendError 호출했는지 봤는데 400이네? 이에 해당하는 로직 처리함.


            }
        }catch (IOException e){
            log.error("resolver ex", e);
        }
        //null을 return 하면 터진 예외가 쭈욱 올라감.
        return null;
    }
}
