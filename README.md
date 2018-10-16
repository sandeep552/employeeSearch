# employeeSearch
Demo Application 

Technologies used:
Grails : 2.4.3
Java : 1.7.0U71

Employee details will be saved under a configured location "employee.local.database.dir" Directory  and "employee.local.database.fileName" in a local file.


API's to create/update/search the employee record are below .

API's :
To get the details of the employee.
Method: HTTP GET
url: http://localhost:8090/employeeSearch/ws/employeesearch/employee/$id.json
Pay Load : Empty 
content type: application/json

To create/update  the employee record .
Method: HTTP PUT
url: http://localhost:8090/employeeSearch/ws/employeesearch/employee/$id.json
Pay Load : {"id": 3, "fullName": "Employee 007", "age": 99, "salary": 9999}
content type: application/json


Filter the employee record by age .
Method: HTTP PUT
url: http://localhost:8090/employeeSearch/ws/employeesearch/employee/filterByAge.json
Pay Load : {"operator": "lt", "value": 30, "sort": "asc"}
content type: application/json

