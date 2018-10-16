package employeesearch

import grails.converters.JSON
import groovy.json.JsonOutput

class EmployeeRestController {
    static allowedMethods = [update: "PUT", delete: "DELETE", filterByAge: "POST"]
    static responseFormats = ["xml", "json"]
    def grailsApplication

    def update(){
        def jsonData = request.JSON
        def result =[:]
        if(jsonData){
            def dirName = grailsApplication.config.employee.local.database.dir
            def fileName = grailsApplication.config.employee.local.database.fileName
                File file = new File(dirName + File.separator +fileName);
                if(file.exists()){
                    if(file.length() != 0){
                        def  jsonObjectFromFile = JsonWithFile.fileToJson("${dirName}${File.separator}${fileName}")
                        def existingObj = jsonObjectFromFile.find{ it.id == jsonData.id}
                        if(existingObj){
                            jsonObjectFromFile.removeAll{it.id==jsonData.id}
                        }
                        jsonObjectFromFile << jsonData
                        file.withWriter('UTF-8') { writer ->
                            writer.write(JsonOutput.prettyPrint(JsonOutput.toJson(jsonObjectFromFile)))
                        }
                    }
                }
                else{
                    def jsonArray = []
                    jsonArray << jsonData
                    file.withWriter('UTF-8') { writer ->
                        writer.write(JsonOutput.prettyPrint(JsonOutput.toJson(jsonArray)))
                    }
                }

            result."status"=200
            result."message"="Sucessful"
        }
        else{
            result."status"=400
            result."message"="Try with Valid input"
        }
        render result as JSON
    }

    def show(){
        def result =[:]
        def  jsonObjectFromFile = getJsonFromFile()
        if(jsonObjectFromFile){
            def existingObj = jsonObjectFromFile.find{ it.id.toString() == params.id.toString()}
            if(existingObj){
                render existingObj as JSON
            }
            else{
                result.status=404
                result."message"="Resource Not Found"
                render result as JSON
            }
        }
        else{
            result.status=404
            result."message"="Search File Not Found"
            render result as JSON
        }
    }

    def filterByAge(){
        def filterParams = request.JSON
        def results =[:]
        def  jsonObjectFromFile = getJsonFromFile()
        //def operators = ["lt":"<","lte":"<=","gt":">","gte":">=","eq":"==","ne":"!="]

        if(jsonObjectFromFile){
            switch(filterParams.operator){
                case 'lt':
                    results = jsonObjectFromFile.findAll{ (it.age as int) < filterParams.value }
                    break;
                case 'lte':
                    results = jsonObjectFromFile.findAll{ (it.age as int) <= filterParams.value }
                    break;
                case 'gt':
                    results = jsonObjectFromFile.findAll{ (it.age as int) > filterParams.value }
                    break;
                case 'gte':
                    results = jsonObjectFromFile.findAll{ (it.age as int) >= filterParams.value }
                    break;
                case 'eq':
                    results = jsonObjectFromFile.findAll{ (it.age as int) == filterParams.value }
                    break;
                case 'ne':
                    results = jsonObjectFromFile.findAll{ (it.age as int) != filterParams.value }
                    break;

            }
            if(filterParams.sort=="asc"){
                results = results.sort{it.age}
            }
            else{
                results = results.sort{-it.age}
            }

            render results as JSON
        }
    }

    def getJsonFromFile(){
        def dirName = grailsApplication.config.employee.local.database.dir
        def fileName = grailsApplication.config.employee.local.database.fileName
        File file = new File(dirName + File.separator +fileName);
        if(file.exists()){
            if(file.length() != 0){
                return JsonWithFile.fileToJson("${dirName}${File.separator}${fileName}")
            }
            else {
                return null
            }
        }
        else {
            return null
        }
    }
}
