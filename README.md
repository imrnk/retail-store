REST Web service based retail store billing application. JAX-RS implementation : Apache CXF, Spring Framework is used for strutting and transaction. JPA with HIbernate implementation is used for CRUD operations.

To run:
```
$ mvn clean install
```
If you are using Tomcat: 

Deploy on tomcat by:
```
$ ./catalina.sh run

$ mvn tomcat7:deploy
```
Open browser and hit Swagger url to test the endpoints: 

http://localhost:8090/retailcounter/service/api-docs?/url=/retailcounter/service/swagger.json#

If you are using any other server, deploy the war and hit the above url with correct host & port:

Test Endpoints Using Swagger (check the above url):
1) First create some products (POST /retailcounter/service/product) :
``` JSON
[
{
  "prodCategory": "A",
  "name": "Apple",
  "description": "Red Juicy Desert Rose",
  "price": 6.25
},
  {
    "prodCategory": "A",
    "name": "Orange",
    "description": "Tangy Dutch",
    "price": 4.55
  },
{
  "prodCategory": "B",
  "name": "Toothbrush",
  "description": "Oral B",
  "price": 4.25
},
{
  "prodCategory": "C",
  "name": "Protinex",
  "description": "Baby Product",
  "price": 12.25
}
]
```
2) Next, create an order with some products (POST /retailcounter/service/store/order) :
```JSON
[
  {
    "productId": 1,
    "itemQuantity": 10
  },
  {
    "productId": 2,
    "itemQuantity": 6
  },
  {
    "productId": 3,
    "itemQuantity": 1
  },
  {
    "productId": 4,
    "itemQuantity": 1
  }
]
```
3) Search the order by order id (GET /retailcounter/service/store/order/1) :

4) Get the bill for the order (id : 1) (GET /retailcounter/service/store/bill/1) :

5) Update one or line items of order 1 (PUT /retailcounter/service/store/order/1):
```JSON
[
  {
    "lineId": 1,
    "productId": 1,
    "itemQuantity": 3
  }
]
```
6) Check the bill once again to see if its updated.

Note:
1) I am not persisting bills, but creating bills from orderId and lineitems stored in database. Therefore, bill Id is empty now. 
2) Also there is no bill date stored.
3) The product categories and related tax rate should also have been stored in database. But I've not done that assuming they are fixed and hence used enum.



