#!/bin/bash

echo "Starting client script."

argc="$@"
x=0

for arg in $argc
do
   case $x in
        "-n")
          CLIENT_NAME="$x $arg" ;;
   esac
   x=$arg

   case $arg in
    "--status")
      OPERATION="$arg" ;;
   esac
done

mvn install exec:java -pl client -Dexec.mainClass=client.ClientApplication -Dexec.args="${CLIENT_NAME} ${OPERATION}" -Dexec.cleanupDaemonThreads=false