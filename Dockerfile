FROM docker.gillsoft.org/jenkins-docker

LABEL maintainer="Ronan Gill <ronan.gill@gillsoft.org>"

COPY jenkins-init/* /usr/share/jenkins/ref/init.groovy.d/
COPY plugins.txt /usr/share/jenkins/ref/plugins.txt

USER root

RUN /usr/local/bin/install-plugins.sh < /usr/share/jenkins/ref/plugins.txt
RUN echo 2.7.3 > /usr/share/jenkins/ref/jenkins.install.UpgradeWizard.state
RUN echo 2.7.3 > /usr/share/jenkins/ref/jenkins.install.InstallUtil.lastExecVersion
RUN install-plugins.sh
RUN chown -R jenkins\: /usr/share/jenkins/

USER jenkins

ENV JAVA_OPTS="-Djenkins.install.runSetupWizard=false -Djenkins.CLI.disabled=true"
ENV JENKINS_OPTS="--prefix=/jenkins"
