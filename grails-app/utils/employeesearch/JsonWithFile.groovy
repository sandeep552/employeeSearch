package employeesearch
/**
 * Created by vemishetti on 10/16/2018.
 */
import groovy.json.JsonBuilder
import groovy.json.JsonSlurper

class JsonWithFile {
    static jsonToFile(Object content, String filePath) {
        new File(filePath).write(new JsonBuilder(content).toPrettyString())
    }

    static Object fileToJson(String filePath) {
        return new JsonSlurper().parseText(new File(filePath).text)
    }
}
