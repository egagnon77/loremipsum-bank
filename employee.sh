echo "Starting script."
argc="$@"
x=0
# x=0 for unset variable
for arg in $argc
do
   case $x in
        "--add" )
          ADDCLIENT="$x=$arg" ;;
        "--list" )
          LIST="$x=$arg" ;;
        "--accept" )
          ACCEPT="$x=$arg" ;;
        "--client" )
          CLIENT="$x=$arg" ;;
        "--reject" )
          REJECT="$x=$arg" ;;
        "--tasks" )
          TASKS="$x" ;;
        "--upgrade" )
          UPGRADE="$x" ;;
        "--downgrade" )
          DOWNGRADE="$x" ;;
    esac
    x=$arg
done

echo "${ADDCLIENT} ${LIST}"

mvn install exec:java -pl employee -Dexec.mainClass=employee.EmployeeApplication -Dexec.args="${ADDCLIENT} ${LIST}" -Dexec.cleanupDaemonThreads=false
