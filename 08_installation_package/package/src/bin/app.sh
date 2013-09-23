#!/bin/bash

DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"

cd "$DIR/.."

CLASSPATH=$(printf ":%s" $(find lib -name "*.jar"))
CLASSPATH="${CLASSPATH:1}"

#Propagate kill signal to Java process
trap 'kill $(jobs -p)' INT

PARAMS=""
while [[ "$1" != "" ]]; do
	if [[ "$1" == "--debug" ]] || [[ "$1" == "-d" ]]; then
		EXTRA="$EXTRA -Xdebug -Xrunjdwp:transport=dt_socket,address=5005,server=y,suspend=y"
		shift
		continue
	fi
	PARAMS="$PARAMS $1"
	shift
done

java $EXTRA -cp $CLASSPATH org.atteo.moonshine.example.Main $PARAMS

