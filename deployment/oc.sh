oc login https://api.starter-ca-central-1.openshift.com -u huber.chrigu@gmail.com

oc new-build redhat-openjdk18-openshift:latest~git@bitbucket.org:huberchrigu/paas-demo.git --build-secret=ssh --source-secret=ssh
oc new-app paas-demo
oc expose svc/paas-demo

oc new-app luiscoms/openshift-rabbitmq:management

oc new-app mongodb-persistent

oc new-app mysql-persistent

echo "Adapt Deployment YAML:"
echo "...
spec:
      containers:
        - env:
            - name: SPRING_PROFILES_ACTIVE
              value: openshift
            - name: OPENSHIFT_MYSQL_DB_USERNAME
              valueFrom:
                secretKeyRef:
                  key: database-user
                  name: mysql
            - name: OPENSHIFT_MYSQL_DB_PASSWORD
              valueFrom:
                secretKeyRef:
                  key: database-password
                  name: mysql
            - name: OPENSHIFT_MYSQL_DB_HOST
              value: 172.30.43.218
            - name: OPENSHIFT_MYSQL_DB_PORT
              value: '3306'
            - name: OPENSHIFT_MYSQL_DB_NAME
              valueFrom:
                secretKeyRef:
                  key: database-name
                  name: mysql
            - name: OPENSHIFT_MONGODB_DB_USERNAME
              valueFrom:
                secretKeyRef:
                  key: database-user
                  name: mongodb
            - name: OPENSHIFT_MONGODB_DB_PASSWORD
              valueFrom:
                secretKeyRef:
                  key: database-password
                  name: mongodb
            - name: OPENSHIFT_MONGODB_DB_NAME
              valueFrom:
                secretKeyRef:
                  key: database-name
                  name: mongodb
            - name: OPENSHIFT_MONGODB_DB_HOST
              value: 172.30.210.250
            - name: OPENSHIFT_MONGODB_DB_PORT
              value: '27017'
..."

echo "Or use client tools alternatively"