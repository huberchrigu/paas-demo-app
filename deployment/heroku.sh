heroku login
heroku create sr-paas-demo
git push heroku master

heroku addons:create cleardb:ignite
heroku addons:create mongolab:sandbox
heroku addons:create cloudamqp:lemur