
package br.ufpe.cin;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlSeeAlso;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 * 
 */
@WebService(name = "IP2GeoHttpGet", targetNamespace = "http://ws.cdyne.com/")
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
@XmlSeeAlso({
    ObjectFactory.class
})
public interface IP2GeoHttpGet {


    /**
     * Use a License Key of 0 for Testing
     * 
     * @param licenseKey
     * @param ipAddress
     * @return
     *     returns br.ufpe.cin.IPInformation
     */
    @WebMethod(operationName = "ResolveIP")
    @WebResult(name = "IPInformation", targetNamespace = "http://ws.cdyne.com/", partName = "Body")
    public IPInformation resolveIP(
        @WebParam(name = "string", targetNamespace = "http://www.w3.org/2001/XMLSchema", partName = "ipAddress")
        String ipAddress,
        @WebParam(name = "string", targetNamespace = "http://www.w3.org/2001/XMLSchema", partName = "licenseKey")
        String licenseKey);

}
