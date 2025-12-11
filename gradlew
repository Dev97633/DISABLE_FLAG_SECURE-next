#!/usr/bin/env sh
APP_BASE_NAME=`dirname "$0"`
GRADLE_HOME="$APP_BASE_NAME/gradle"
exec "$APP_BASE_NAME/gradlew" "$@"
