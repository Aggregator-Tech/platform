cd ../heroku-platform
echo $PWD
echo ${GIT_USERNAME}
echo ${GIT_PASSWORD}
git config --global user.name "atul-aggregatortech"
git config --global user.email "atul.aggregatortech@gmail.com"
git config --global http.sslverify false
git clone https://github.com/Aggregator-Tech/heroku-platform
touch build.gradle
git add --all
git commit -m "Add change "
git push origin master
