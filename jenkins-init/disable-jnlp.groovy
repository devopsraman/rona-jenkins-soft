import jenkins.model.Jenkins
import jenkins.security.s2m.*

Jenkins jenkins = Jenkins.getInstance()

// Disable jnlp port
jenkins.setSlaveAgentPort(-1);

// Disable old Non-Encrypted protocols
HashSet<String> newProtocols = new HashSet<>(jenkins.getAgentProtocols());
newProtocols.removeAll(Arrays.asList(
        "JNLP3-connect", "JNLP2-connect", "JNLP-connect"
));
jenkins.setAgentProtocols(newProtocols);
jenkins.save()