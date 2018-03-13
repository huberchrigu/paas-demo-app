cf login -a https://api.lyra-836.appcloud.swisscom.com -u username
cf push paas-demo -p target/paas-demo-0.0.1-SNAPSHOT.jar

cf create-service mongo mongodb small
cf create-service rabbitmqent usage rabbitmq
cf create-service mariadbent usage maria

cf bind-service paas-demo mongo
cf bind-service paas-demo rabbitmq
cf bind-service paas-demo maria

cf restart paas-demo