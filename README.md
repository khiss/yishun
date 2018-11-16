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

Retrieve customer record by customer ID

curl "http://localhost:8080/workshop03/customer?CustomerId=1"

Create new Customer

curl -d "CustomerId=1033&name=test&addressline1=one&addressline2=two&city=Singapore&state=sg&zip=85638&phone=12345678&fax=123456789&email=xyz&creditLimt=123&discountCode=H" "http://localhost:8080/workshop03/customer"

# Workshop 04
curl -H "Accept: application/json" "http://localhost:8080/workshop04/api/customer/1"

curl -H "Accept: application/json" http://localhost:8080/workshop04/api/customer/async/1

# Workshop 05

curl -v -H "Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJ3b3Jrc2hvcDA1IiwiZXhwIjoxNTQyMzU5MTM3LCJpc3MiOiJ3b3Jrc2hvcDA1Iiwicm9sZSI6ImF1dGhlbnRpY2F0ZWQiLCJ1c2VybmFtZSI6ImFsaWNlIn0.RymBboTCVIQjFX9O44TcxTRtnW7-qmHHyf7aCwpdh1A" http://localhost:8080/workshop05/api/customer/1
