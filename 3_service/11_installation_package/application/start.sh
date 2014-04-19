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

