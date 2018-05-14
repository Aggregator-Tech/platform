#cd ..
#rm -rf heroku-platform
#ssh -T git@github.com
#git clone git://github.com/aggregatortech/Aggregator-Tech/heroku-platform
echo 'Path is ${PWD}'
#cd ..
echo 'Path is ${PWD}'
#git remote set-url origin git://github.com/aggregatortech/Aggregator-Tech/heroku-platform
echo $PWD
echo ${GIT_USERNAME}
echo ${GIT_PASSWORD}
#git config --global user.name "atul-aggregatortech"
#git config --global user.email "atul.aggregatortech@gmail.com"
#git config --global http.sslverify false
git config --global http.proxy http://www-proxy.us.oracle.com:80
git config --global https.proxy http://www-proxy.us.oracle.com:80
git checkout master
touch commitFile
git add -A
git commit -am "my update msg"
git push git://github.com/aggregatortech/Aggregator-Tech/platform
