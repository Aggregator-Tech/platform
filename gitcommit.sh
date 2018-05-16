git config --global user.name "aggregatortech"
git config --global user.email "aggregatortech@gmail.com"
echo "new build" >> commitFile
git add -A
git commit -am "my update msg"
git config --global credential.helper 'store --file /var/jenkins_home/git.store'
git push
