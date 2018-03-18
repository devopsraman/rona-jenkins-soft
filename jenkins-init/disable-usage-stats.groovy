
import jenkins.model.Jenkins

def j = Jenkins.instance

j.setNoUsageStatistics(true)
j.save()
