#!/bin/bash

arg1=$1
arg2=$2
arg3=$3
arg4=$4

if [ "$MGL_CLIENT_NAME" ];
  then COMMANDS="-n=$MGL_CLIENT_NAME";
fi

if [ "$arg1" == "-n" ]
  then 
    COMMANDS="$arg1=$arg2";
    
    if [ "$arg3" ];
      then COMMANDS="$COMMANDS $arg3";
    fi

    if [ "$arg4" ];
      then COMMANDS="$COMMANDS=$arg4";
    fi
  else
    if [ "$arg1" ];
      then COMMANDS="$COMMANDS $arg1"; 
    fi

    if [ "$arg2" ];
      then COMMANDS="$COMMANDS=$arg2"; 
    fi
fi

echo "Evaluated arguments: ${COMMANDS}"

mvn -q exec:java -pl client -Dexec.mainClass=client.ClientApplication -Dexec.args="${COMMANDS}" -Dexec.cleanupDaemonThreads=false