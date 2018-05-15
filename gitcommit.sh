#git checkout master
#git pull
echo "new build" >> heroku-platform/commitFile
git status
git add -A
git status
git commit -am "my update msg"
git status
git push
git status
