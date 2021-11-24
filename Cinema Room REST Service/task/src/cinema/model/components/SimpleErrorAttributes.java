package cinema.model.components;


import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.Map;

@Component
public class SimpleErrorAttributes extends DefaultErrorAttributes {

    @Override
    public Map<String, Object> getErrorAttributes(WebRequest webRequest, ErrorAttributeOptions options) {
        Throwable throwable = getError(webRequest);
        Map<String, Object> errorAttributes = new HashMap<>();
        errorAttributes.put("error", ((ResponseStatusException) throwable).getReason());
        return errorAttributes;
    }
}
