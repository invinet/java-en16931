#! /bin/bash

./gradlew javadoc
cp -r build/docs/javadoc/* docs
#git commit -a -m "Rebuild documentation"
#git push
