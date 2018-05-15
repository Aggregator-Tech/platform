#cd ..
#rm -rf heroku-platform
#ssh -T git@github.com

#echo 'Path is ${PWD}'

echo 'Path is  ${PWD}'
rm -rf heroku-platform
cd heroku-platform
git remote set-url origin https://github.com/Aggregator-Tech/heroku-platform
git remote add origin https://github.com/aggregatortech/Aggregator-Tech/heroku-platform

#echo $PWD
git config --global credential.helper 'store --file /var/jenkins_home/git.store'
git config --global user.name "aggregatortech"
git config --global user.email "aggregatortech@gmail.com"
#git config --global http.sslverify false
git config --global http.proxy http://www-proxy.us.oracle.com:80
git config --global https.proxy http://www-proxy.us.oracle.com:80
git init
git clone https://github.com/Aggregator-Tech/heroku-platform

git checkout master
git pull
echo "new build" >> heroku-platform/commitFile
git add -A
git commit -am "my update msg"
git push origin HEAD:master
