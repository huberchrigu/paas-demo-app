oc login https://api.starter-ca-central-1.openshift.com -u huber.chrigu@gmail.com

oc new-build redhat-openjdk18-openshift:latest~git@bitbucket.org:huberchrigu/paas-demo.git --build-secret=ssh --source-secret=ssh
oc new-app paas-demo
oc expose svc/paas-demo

oc new-app luiscoms/openshift-rabbitmq:management

oc new-app mongodb-persistent

oc new-app mysql-persistent