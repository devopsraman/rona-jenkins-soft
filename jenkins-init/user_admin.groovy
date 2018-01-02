import jenkins.model.*
import hudson.security.*
import jenkins.security.s2m.AdminWhitelistRule

def instance = Jenkins.getInstance()

def hudsonRealm = new HudsonPrivateSecurityRealm(false)
hudsonRealm.createAccount("rgill","1st*Rate")
instance.setSecurityRealm(hudsonRealm)

def strategy = new GlobalMatrixAuthorizationStrategy()
  strategy.add(Jenkins.ADMINISTER, "rgill")
  instance.setAuthorizationStrategy(strategy)

instance.getInjector().getInstance(AdminWhitelistRule.class).setMasterKillSwitch(false)
instance.save()
