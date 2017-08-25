package groovy

import groovy.json.JsonBuilder
import groovyx.net.http.RESTClient
import org.apache.http.HttpStatus
import spock.lang.Specification

import static groovyx.net.http.ContentType.JSON

class ProductSpecification extends Specification {

    def static final  id = UUID.randomUUID().toString()

    def "Should create Product"() {
        given:

        def json = new JsonBuilder()

        json {
            productId id
            name {
                en 'crop_top'
                de 'cropten top'
            }
            price {
                base 'INR'  // parenthesis are optional
                amount 100.56
                supportedCurrencies 'EUR', 'USD'
            }
            categoryId '32'
        }


        when:
        //def prettyJson = JsonOutput.prettyPrint(json.toString())println("---------"+json.toString())
        def client = new RESTClient("http://localhost:8080", JSON)
        def response = client.post(path: "/products", body: json.toString())

        then:
        println("-------" + response.toString())
        assert response.status == HttpStatus.SC_CREATED
    }

    def "Should get Product"() {

        when:
        def client = new RESTClient("http://localhost:8080", JSON)
        def response = client.get(path: "/products/"+id)

        then:
         assert response.status == HttpStatus.SC_OK
    }
}