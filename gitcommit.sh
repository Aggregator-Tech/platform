git config --global user.name "aggregatortech"
git config --global user.email "aggregatortech@gmail.com"
cp ../../webTemplate/build/distributions/webTemplate-1.0.zip deployable-artifacts/
git add -A
git commit -am "my update msg"
git config --global credential.helper 'store --file /var/jenkins_home/git.store'
git push
