#!/bin/bash

#
# Script to execute the application directly from source code and Maven repository
#
#
# Usage:
#
# 1. Execute the application
#
# ./start.sh
#
# 2. Recompile and execute the application
#
# ./start.sh repositories
#
# 3. Start the application in debug mode
#
# ./start.sh --debug
#
# Configuration:
# 1. Set the ROOT variable below to the name of the project to start

ROOT="."
MAIN_CLASS="org.atteo.moonshine.example.Main"
HOME_DIR="target/home"

APP_PATH=$(echo -n target/classes)
EXTRA=""

trap "exit 1" ERR

while [[ "$1" != "" ]]; do
	if [[ "$1" == "--debug" ]] || [[ "$1" == "-d" ]]; then
		EXTRA="$EXTRA -Xdebug -Xrunjdwp:transport=dt_socket,address=5005,server=y,suspend=y"
		shift
		continue
	fi

	if [[ "$1" == "--recompile" ]] || [[ "$1" == "-r" ]]; then
		shift
		pushd -- "$1"
		mvn clean install
		popd
		shift
		continue
	fi

	if [[ "$1" == "--log-method-entry" ]]; then
		if [[ ! -r slf4j-ext-1.7.1.jar ]]; then
			wget http://search.maven.org/remotecontent?filepath=org/slf4j/slf4j-ext/1.7.1/slf4j-ext-1.7.1.jar -O slf4j-ext-1.7.1.jar
		fi
		if [[ ! -r javassist.jar ]]; then
			#wget http://search.maven.org/remotecontent?filepath=javassist/javassist/3.4.GA/javassist-3.4.GA.jar -O javassist.jar
			wget http://search.maven.org/remotecontent?filepath=org/javassist/javassist/3.16.1-GA/javassist-3.16.1-GA.jar -O javassist.jar
		fi
		EXTRA="$EXTRA -javaagent:../slf4j-ext-1.7.1.jar=time,level=info,ignore=org/slf4j/:ch/qos/logback/:org/apache/log4j/:com/sun/:com/google/:org/codehaus/janino/:org/atteo/evo/jpa/:com/atteo/evo/rest:org/hibernate:org/atteo/evo/hibernate"
		shift
		continue
	fi

	PARAMS="$PARAMS $1"
	shift
done


OLDEST_POM_DATE="$(find . -name pom.xml -exec stat -c %Y {} + | sort | tail -1)"

if [[ -e ".direct_classpath" ]]; then
	CLASSPATH_DATE="$(stat -c %Y .direct_classpath)"
fi


classpath=

if [[ -e ".direct_classpath" ]] && (( OLDEST_POM_DATE < CLASSPATH_DATE  )); then
	classpath="$(cat .direct_classpath)"
else

	echo "Calculating classpath for in-place execution..."
	next=false


	while read LINE; do
		if [[ "$next" == "true" ]]; then
			classpath="$LINE"
			break
		fi
		if echo "$LINE" | grep "Dependencies classpath:" > /dev/null; then
			next="true"
		fi
	done < <( cd $ROOT; mvn dependency:build-classpath -DincludeScope=runtime )
	echo "$classpath" > .direct_classpath
fi

java $EXTRA -cp $classpath:src/main/resources:$APP_PATH:$APP_PATH/WEB-INF/classes $MAIN_CLASS --home $HOME_DIR $PARAMS

