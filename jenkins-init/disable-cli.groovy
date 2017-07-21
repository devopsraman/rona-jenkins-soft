import jenkins.AgentProtocol
import jenkins.model.Jenkins
import hudson.model.RootAction

println "Disabling the Jenkins CLI..."

// disabled CLI access over TCP listener (separate port)
def p = AgentProtocol.all()
p.each { x ->
    if (x.name?.contains("CLI")) {
        println "Removing protocol ${x.name}"
        p.remove(x)
    }
}

// disable CLI access over /cli URL
def removal = { lst ->
    lst.each { x ->
        if(x.getClass().name.contains("CLIAction")) {
            println "Removing extension ${x.getClass().name}"
            lst.remove(x)
        }
    }
}

def j = Jenkins.instance;
removal(j.getExtensionList(RootAction.class))
removal(j.actions)

//logger.info("CLI disabled")
