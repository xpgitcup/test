
import test.NewDomainClass

class BootStrap {

    def init = { servletContext ->
        environments {
            development {
                for (int i=0; i<15; i++) {
                    def n = new NewDomainClass(name: "张${i}")
                    n.save()
                }
            }
        }
    }
    def destroy = {
    }
}
