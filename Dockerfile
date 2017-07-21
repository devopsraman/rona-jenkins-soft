FROM docker.gillsoft.org/jenkins-base

MAINTAINER Ronan Gill <ronan.gill@gillsoft.org>

ENV JAVA_OPTS="-Djenkins.install.runSetupWizard=false -Djenkins.CLI.disabled=true"

COPY jenkins-init/* /usr/share/jenkins/ref/init.groovy.d/
COPY plugins.txt /usr/share/jenkins/ref/plugins.txt
RUN /usr/local/bin/install-plugins.sh < /usr/share/jenkins/ref/plugins.txt

RUN echo 2.0 > /usr/share/jenkins/ref/jenkins.install.UpgradeWizard.state
RUN echo 2.0 > /var/jenkins_home/jenkins.install.UpgradeWizard.state

USER jenkins
