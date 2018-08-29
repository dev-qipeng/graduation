package site.qipeng.configuration.freemarker;

import org.springframework.web.servlet.view.freemarker.FreeMarkerView;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public class MyFreeMarkerView extends FreeMarkerView {
    @Override
    protected void exposeHelpers(Map<String, Object> model, HttpServletRequest request) throws Exception {
        model.put("ctx",request.getContextPath());
        super.exposeHelpers(model, request);
    }
}
