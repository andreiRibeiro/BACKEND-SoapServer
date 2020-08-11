package br.com.aaribeiro.soapwebservice.configuration;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.server.EndpointInterceptor;
import org.springframework.ws.soap.security.xwss.XwsSecurityInterceptor;
import org.springframework.ws.soap.security.xwss.callback.SimplePasswordValidationCallbackHandler;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

import java.util.Collections;
import java.util.List;

@Configuration
@EnableWs
public class WebServiceConfiguration extends WsConfigurerAdapter {

    /*DISPONIBILIZAR SERVICO*/
    @Bean
    public ServletRegistrationBean messageDispatcherServlet(ApplicationContext context){
        MessageDispatcherServlet messageDispatcherServlet = new MessageDispatcherServlet();
        messageDispatcherServlet.setApplicationContext(context);
        messageDispatcherServlet.setTransformWsdlLocations(true);
        return new ServletRegistrationBean(messageDispatcherServlet, "/soap-server/*");
    }

    /*DISPONIBILIZAR WSDL*/
    @Bean(name = "customers")
    public DefaultWsdl11Definition defaultWsdl11Definition(){
        DefaultWsdl11Definition definition = new DefaultWsdl11Definition();
        definition.setPortTypeName("CustomerPort");
        definition.setTargetNamespace("http://aaribeiro.com.br");
        definition.setLocationUri("/soap-server");
        definition.setSchema(customerSchema());
        return definition;
    }

    @Bean
    public XsdSchema customerSchema(){
        return new SimpleXsdSchema(new ClassPathResource("customer-information.xsd"));
    }

    /*CONFIGURACAO DE SEGURANCA*/
    @Bean
    public XwsSecurityInterceptor securityInterceptor(){
        XwsSecurityInterceptor securityInterceptor = new XwsSecurityInterceptor();
        securityInterceptor.setCallbackHandler(simplePasswordValidationCallbackHandler());
        securityInterceptor.setPolicyConfiguration(new ClassPathResource("securityPolicy.xml"));
        return securityInterceptor;
    }

    @Bean
    public SimplePasswordValidationCallbackHandler simplePasswordValidationCallbackHandler(){
        SimplePasswordValidationCallbackHandler handler = new SimplePasswordValidationCallbackHandler();
        handler.setUsersMap(Collections.singletonMap("andrei", "123"));
        return handler;
    }

    @Override
    public void addInterceptors(List<EndpointInterceptor> interceptors) {
        interceptors.add(securityInterceptor());
    }
}
