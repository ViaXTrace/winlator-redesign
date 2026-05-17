#!/usr/bin/env sh
# Gradle start up script for UN*X

APP_HOME="$(cd "$(dirname "$0")" && pwd -P)"
APP_NAME="Gradle"
APP_BASE_NAME="$(basename "$0")"

DEFAULT_JVM_OPTS='"-Xmx64m" "-Xms64m"'

MAX_FD="maximum"

warn() {
    echo "$*"
}

die() {
    echo
    echo "$*"
    echo
    exit 1
}

cygwin=false
msys=false
darwin=false
nonstop=false
case "$(uname)" in
  CYGWIN*) cygwin=true ;;
  Darwin*) darwin=true ;;
  MINGW*)  msys=true ;;
  NONSTOP*) nonstop=true ;;
esac

CLASSPATH=$APP_HOME/gradle/wrapper/gradle-wrapper.jar

if [ -n "$JAVA_HOME" ]; then
    if [ -x "$JAVA_HOME/jre/sh/java" ]; then
        JAVACMD="$JAVA_HOME/jre/sh/java"
    else
        JAVACMD="$JAVA_HOME/bin/java"
    fi
    if [ ! -x "$JAVACMD" ]; then
        die "ERROR: JAVA_HOME is set to an invalid directory: $JAVA_HOME
Please set the JAVA_HOME variable in your environment to match the location of your Java installation."
    fi
else
    JAVACMD="java"
    command -v java >/dev/null 2>&1 || die "ERROR: JAVA_HOME is not set and no 'java' command could be found in your PATH."
fi

if [ "$cygwin" = "false" -a "$darwin" = "false" -a "$nonstop" = "false" ]; then
    MAX_FD_LIMIT=$(ulimit -H -n)
    if [ $? -eq 0 ]; then
        if [ "$MAX_FD" = "maximum" -o "$MAX_FD" = "max" ]; then
            MAX_FD="$MAX_FD_LIMIT"
        fi
        ulimit -n $MAX_FD
    fi
fi

if $darwin; then
    GRADLE_OPTS="$GRADLE_OPTS \"-Xdock:name=$APP_NAME\" \"-Xdock:icon=$APP_HOME/media/gradle.icns\""
fi

exec "$JAVACMD" $DEFAULT_JVM_OPTS $JAVA_OPTS $GRADLE_OPTS \
    "-Dorg.gradle.appname=$APP_BASE_NAME" \
    -classpath "$CLASSPATH" \
    org.gradle.wrapper.GradleWrapperMain "$@"
