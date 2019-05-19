
package br.ufpe.cin;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the br.ufpe.cin package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _Brands_QNAME = new QName("http://cin.ufpe.br/", "brands");
    private final static QName _CarsByBrand_QNAME = new QName("http://cin.ufpe.br/", "carsByBrand");
    private final static QName _CarsByBrandResponse_QNAME = new QName("http://cin.ufpe.br/", "carsByBrandResponse");
    private final static QName _CarPricesResponse_QNAME = new QName("http://cin.ufpe.br/", "carPricesResponse");
    private final static QName _CarPrices_QNAME = new QName("http://cin.ufpe.br/", "carPrices");
    private final static QName _BrandsResponse_QNAME = new QName("http://cin.ufpe.br/", "brandsResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: br.ufpe.cin
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link CarsByBrandResponse }
     * 
     */
    public CarsByBrandResponse createCarsByBrandResponse() {
        return new CarsByBrandResponse();
    }

    /**
     * Create an instance of {@link Brands }
     * 
     */
    public Brands createBrands() {
        return new Brands();
    }

    /**
     * Create an instance of {@link CarsByBrand }
     * 
     */
    public CarsByBrand createCarsByBrand() {
        return new CarsByBrand();
    }

    /**
     * Create an instance of {@link CarPrices }
     * 
     */
    public CarPrices createCarPrices() {
        return new CarPrices();
    }

    /**
     * Create an instance of {@link CarPricesResponse }
     * 
     */
    public CarPricesResponse createCarPricesResponse() {
        return new CarPricesResponse();
    }

    /**
     * Create an instance of {@link BrandsResponse }
     * 
     */
    public BrandsResponse createBrandsResponse() {
        return new BrandsResponse();
    }

    /**
     * Create an instance of {@link Car }
     * 
     */
    public Car createCar() {
        return new Car();
    }

    /**
     * Create an instance of {@link Brand }
     * 
     */
    public Brand createBrand() {
        return new Brand();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Brands }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://cin.ufpe.br/", name = "brands")
    public JAXBElement<Brands> createBrands(Brands value) {
        return new JAXBElement<Brands>(_Brands_QNAME, Brands.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CarsByBrand }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://cin.ufpe.br/", name = "carsByBrand")
    public JAXBElement<CarsByBrand> createCarsByBrand(CarsByBrand value) {
        return new JAXBElement<CarsByBrand>(_CarsByBrand_QNAME, CarsByBrand.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CarsByBrandResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://cin.ufpe.br/", name = "carsByBrandResponse")
    public JAXBElement<CarsByBrandResponse> createCarsByBrandResponse(CarsByBrandResponse value) {
        return new JAXBElement<CarsByBrandResponse>(_CarsByBrandResponse_QNAME, CarsByBrandResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CarPricesResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://cin.ufpe.br/", name = "carPricesResponse")
    public JAXBElement<CarPricesResponse> createCarPricesResponse(CarPricesResponse value) {
        return new JAXBElement<CarPricesResponse>(_CarPricesResponse_QNAME, CarPricesResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CarPrices }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://cin.ufpe.br/", name = "carPrices")
    public JAXBElement<CarPrices> createCarPrices(CarPrices value) {
        return new JAXBElement<CarPrices>(_CarPrices_QNAME, CarPrices.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BrandsResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://cin.ufpe.br/", name = "brandsResponse")
    public JAXBElement<BrandsResponse> createBrandsResponse(BrandsResponse value) {
        return new JAXBElement<BrandsResponse>(_BrandsResponse_QNAME, BrandsResponse.class, null, value);
    }

}
