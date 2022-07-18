# KB ADAA API Spring Boot client's reference example

#### Links
* [KB API Business portal](https://www.kb.cz/api)
* [KB API Developer portal](https://api.kb.cz/open/apim/store)
* [KB ADAA API Java SDK](https://github.com/komercka/adaa-client)
* [KB Client Registration API Java SDK](https://github.com/komercka/client-registration-client)
* [ADAA API technical manual (for production version)](https://www.kb.cz/getmedia/ffc70c65-cc28-4809-ad47-22b7b4361ce5/ADAA_Technical_manual_EN.pdf.aspx)
* [ADAA API technical manual (for sandbox version)](https://www.kb.cz/getmedia/3662e39f-04af-4872-bf02-eda9c05a0c11/API_Sandbox_Account-Direct-Access-API-Manual_EN.pdf.aspx)

---

This Java web application serves as a reference example, or an inspiration for developers who want to develop software based on the KB ADAA API. 
This example is based on the Spring Boot framework. For more examples check our Github repository.

#### How to run
1. Complete application properties located 
in the `./web/src/main/resources/application.yml` file:
    * `adaa-examle.client-registration-uri` - URI of the page of the KB Client Registration page
    * `adaa-examle.authorization-uri` - URI of the page of the KB Authorization server for getting OAuth2 authorization code
    * `adaa-examle.access-token-uri` - URI of the endpoint of the KB OAuth2 API for getting access token
    * `adaa-examle.secret` - Base64 encoded 256-bit key that used during getting client registration process
    * `adaa-examle.keystore-location` - path to the keystore with client certificate
    * `adaa-examle.keystore-password` - password of the keystore with client certificate
    * `adaa-examle.client-cert-password` - password of the client certificate
    * `adaa.client.baseUri` - URI of the KB ADAA API
    * `adaa.client.apiKey` - authorization API key generated at [KB API Developer portal](https://api.kb.cz/open/apim/store)
    * `clientregistration.client.baseUri` - URI of the endpoint of the KB Client Registration API for getting software statement
    * `clientregistration.client.apiKey` - authorization API key generated at [KB API Developer portal](https://api.kb.cz/open/apim/store)
    
2. You should build this project with Maven
    ```
    mvn clean install
    ```
    and then run the embedded Tomcat server using command in web maven module:
    ```
    mvn spring-boot:run -pl web
    ```
3. Or you can deploy a built `war` file to your own instance of the application server

### Running with Docker

**Don't forget to put your certificate somewhere inside the docker and fill in the `application.yml` path inside the container!**

1. `make build`
2. `make start`

#### Description
KB ADAA API authorization process is based on the OAuth2 specification. 
Thus, to call KB ADAA API and get user's transaction history you must complete following steps:
1. Registration of the application's instance
    * user grants access
    * gets software statement by calling `SoftwareStatementsApi#softwareStatement(SoftwareStatement req)` using [KB Client Registration API Java SDK](https://github.com/komercka/client-registration-client)
    * redirects user to the KB login page for authentication
    * after success authentication KB SAML server will send response with an encrypted client ID and a client secret to `/register/client` application endpoint. 
    Application decrypts client's registration data and store them to the further usage
2. OAuth2 authorization process
    * redirects user to KB OAuth2 authorization page. After user grant access, KB authorization server 
    will send a redirect response with authorization code back to the application's endpoint `/oauth2/authorize`
    * application calls KB OAuth2 API to get access token
3. Call KB ADAA API
    * application call KB ADAA API for user's transaction history and account balance
    * return HTML page with displayed transaction history and account balance to the user

The whole flow of this application is described by sequence diagram below:
![ADAA API example application (sequence diagram)](adaa-example-sequence-diagram.svg "ADAA API example application (sequence diagram)")

---
*For further details please read [ADAA API technical manual (for production version)](https://www.kb.cz/getmedia/ffc70c65-cc28-4809-ad47-22b7b4361ce5/ADAA_Technical_manual_EN.pdf.aspx) 
or [ADAA API technical manual (for sandbox version)](https://www.kb.cz/getmedia/3662e39f-04af-4872-bf02-eda9c05a0c11/API_Sandbox_Account-Direct-Access-API-Manual_EN.pdf.aspx).*

*If you still have any questions please contact a [KB API support team](mailto:api@kb.cz).*



