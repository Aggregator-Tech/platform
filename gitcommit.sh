#cd ..
#rm -rf heroku-platform
#ssh -T git@github.com
#git clone https://github.com/Aggregator-Tech/platform
#echo 'Path is ${PWD}'
#cd ..
echo 'Path is ${PWD}'
#git remote set-url origin aggregatortech@gmail.com://github.com/aggregatortech/Aggregator-Tech/heroku-platform
#echo $PWD
git config --global credential.helper 'store --file /var/jenkins_home/git.store'
git config --global user.name "aggregatortech"
git config --global user.email "aggregatortech@gmail.com"
#git config --global http.sslverify false
git config --global http.proxy http://www-proxy.us.oracle.com:80
git config --global https.proxy http://www-proxy.us.oracle.com:80
#git remote add origin https://aggregatortech:Westworld1@github.com/aggregatortech/Aggregator-Tech/platform
git pull
echo "new build" >> commitFile
git add -A
git commit -am "my update msg"
git push
