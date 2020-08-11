package br.com.aaribeiro.soapwebservice.error;

import org.springframework.ws.soap.server.endpoint.annotation.FaultCode;
import org.springframework.ws.soap.server.endpoint.annotation.SoapFault;

@SoapFault(faultCode = FaultCode.CUSTOM, customFaultCode = "{http://aaribeiro.com.br}DL_005_CLIENTE_NAO_LOCALIZADO")
public class CustomerNotFoundException extends Exception {

    public CustomerNotFoundException(String message){
        super(message);
    }
}
