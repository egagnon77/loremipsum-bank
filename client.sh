arg1=$1
arg2=$2
arg3=$3
arg4=$4

COMMANDS=$arg1

if [ "$arg2" ];
  then COMMANDS="$COMMANDS=$arg2"; 
fi

if [ "$arg3" ];
  then COMMANDS="$COMMANDS $arg3";
fi

if [ "$arg4" ];
  then COMMANDS="$COMMANDS=$arg4";
fi

echo "Evaluated arguments: ${COMMANDS}"

mvn exec:java -pl client -Dexec.mainClass=client.ClientApplication -Dexec.args="${COMMANDS}" -Dexec.cleanupDaemonThreads=false