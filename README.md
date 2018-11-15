# Yishun
Workshop at ISS
Building Enterprise Applicaiton Using Java EE.

Reference
https://github.com/chukmunnlee/beauj_nov12

Examples

# Workshop 01
Query Weather

curl "http://localhost:8080/workshop01/weather?cityName=Singapore"

# Workshop 02
Retrieve customer record by customer ID

curl "http://localhost:8080/workshop02/customer-sql?cust_id=1"

Retrieve Purchase Orders by customer ID

curl "http://localhost:8080/workshop02/purchaseOrder?custId=1"

# Workshop 03

# Workshop 04
curl -H "Accept: application/json" "http://localhost:8080/workshop04/api/customer/1"

curl -H "Accept: application/json" http://localhost:8080/workshop04/api/customer/async/1
