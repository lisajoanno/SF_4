
package stubs.customerCare;

import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 * 
 */
@WebService(name = "CustomerCareService", targetNamespace = "http://webservice.webservicesmodule.modules.tcf.isa.polytech.unice.fr/")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface CustomerCareService {


    /**
     * 
     * @return
     *     returns java.util.List<stubs.customerCare.Cookies>
     */
    @WebMethod
    @WebResult(name = "recipes", targetNamespace = "")
    @RequestWrapper(localName = "listAllRecipes", targetNamespace = "http://webservice.webservicesmodule.modules.tcf.isa.polytech.unice.fr/", className = "stubs.customerCare.ListAllRecipes")
    @ResponseWrapper(localName = "listAllRecipesResponse", targetNamespace = "http://webservice.webservicesmodule.modules.tcf.isa.polytech.unice.fr/", className = "stubs.customerCare.ListAllRecipesResponse")
    public List<Cookies> listAllRecipes();

    /**
     * 
     * @param creditCardNumber
     * @param customerName
     * @throws AlreadyExistingCustomerException_Exception
     */
    @WebMethod
    @RequestWrapper(localName = "register", targetNamespace = "http://webservice.webservicesmodule.modules.tcf.isa.polytech.unice.fr/", className = "stubs.customerCare.Register")
    @ResponseWrapper(localName = "registerResponse", targetNamespace = "http://webservice.webservicesmodule.modules.tcf.isa.polytech.unice.fr/", className = "stubs.customerCare.RegisterResponse")
    public void register(
        @WebParam(name = "customer_name", targetNamespace = "")
        String customerName,
        @WebParam(name = "credit_card_number", targetNamespace = "")
        String creditCardNumber)
        throws AlreadyExistingCustomerException_Exception
    ;

    /**
     * 
     * @param orderId
     * @return
     *     returns stubs.customerCare.OrderStatus
     * @throws UnknownOrderId_Exception
     */
    @WebMethod
    @WebResult(name = "status", targetNamespace = "")
    @RequestWrapper(localName = "track", targetNamespace = "http://webservice.webservicesmodule.modules.tcf.isa.polytech.unice.fr/", className = "stubs.customerCare.Track")
    @ResponseWrapper(localName = "trackResponse", targetNamespace = "http://webservice.webservicesmodule.modules.tcf.isa.polytech.unice.fr/", className = "stubs.customerCare.TrackResponse")
    public OrderStatus track(
        @WebParam(name = "order_id", targetNamespace = "")
        String orderId)
        throws UnknownOrderId_Exception
    ;

}
