
class BootStrap {

    def grailsApplication
    def init = { servletContext ->
        def dirName = grailsApplication.config.employee.local.database.dir
        def fileName = grailsApplication.config.employee.local.database.fileName
        def dir = new File(dirName)
        if (!dir.exists()) {
            dir.mkdir();
        }
        File file = new File(dirName + File.separator +fileName);
        file.withWriter('UTF-8') { writer ->
            writer.write("[]")
        }
    }
    def destroy = {
    }
}
