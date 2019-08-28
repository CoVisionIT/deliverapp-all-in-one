mvn clean
mkdir target
mkdir target/mvn-repo
mkdir -p artifacts
cp -r artifacts/* target/mvn-repo
mvn deploy
cp -r target/mvn-repo/* artifacts/
git add artifacts/*

echo "don't forget to change the readme!"
