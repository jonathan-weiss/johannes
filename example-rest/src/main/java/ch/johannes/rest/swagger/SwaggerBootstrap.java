package ch.johannes.rest.swagger;

import io.swagger.jaxrs.config.BeanConfig;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

public class SwaggerBootstrap extends HttpServlet {

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        // see http://swagger.io/specification/ for further details
        BeanConfig beanConfig = new BeanConfig();
        beanConfig.setVersion("0.0.1");
        beanConfig.setSchemes(new String[]{"http"});
        beanConfig.setResourcePackage("ch.johannes.rest.endpoints");
        beanConfig.setScan(true);

        // Set base path
        final String contextPath = config.getServletContext().getContextPath();
        beanConfig.setBasePath(contextPath + "/api");
    }

}