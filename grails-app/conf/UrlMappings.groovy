class UrlMappings {

	static mappings = {
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
              /*  //http://localhost:8090/employeeSearch/ws/employeesearch/employee/1.json
                "/ws/employeesearch/employee/$id.$type"(controller: "employeeRest") {
                action = [GET: "show", PUT: "update", DELETE: "delete", POST: "unsupported"]
            }
            "/ws/employeesearch/employee/_search.$type"(controller: "employeeRest") {
                action = [GET: "unsupported", PUT: "unsupported", DELETE: "unsupported", POST: "search"]
            }*/
        }

        "/ws/employeesearch/employee/$id.$type"(controller: "employeeRest") {
            action = [GET: "show", PUT: "update", DELETE: "delete", POST: "unsupported"]
        }
        "/ws/employeesearch/employee/filterByAge.$type"(controller: "employeeRest") {
            action = [POST: "filterByAge"]
        }


        "/"(view:"/index")
        "500"(view:'/error')
	}
}
