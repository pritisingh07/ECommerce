package groovy

import groovy.json.JsonBuilder
import groovy.json.JsonOutput
import groovyx.net.http.RESTClient
import org.apache.http.HttpStatus
import spock.lang.Specification

import static groovyx.net.http.ContentType.JSON

class ProductSpecification extends Specification {

	/*def "Should create Product"() {
		given:

		def json = new JsonBuilder()

		json {
			productId "top01"
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
		def prettyJson = JsonOutput.prettyPrint(json.toString())println("---------"+json.toString())
		def client = new RESTClient("http://localhost:8080", JSON)
		def response = client.post(path: "/products", body: json.toString())
		
		then:
		println("-------" + response.toString())
		response.status == HttpStatus.SC_OK
	}*/


	/*def "Should get Product"() {
		given:

		def json = new JsonBuilder()

		json {
			productId "top01"
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
		def client = new RESTClient("http://localhost:8080", JSON)
		def response = client.get(path: "/products", query:[id:])

		then:
		response.status == HttpStatus.SC_OK
	}*/
}