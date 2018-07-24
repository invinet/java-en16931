#! /bin/bash

./gradlew javadoc
cp -r build/docs/javadoc/* docs
