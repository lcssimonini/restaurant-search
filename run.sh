run_docker_compose() {
  echo "----- Running docker-compose -----"
  sudo docker-compose --file docker-compose.yml down
  if [ ! "$WITH_CONTAINER" = '-docker' ]; then
    sudo docker-compose --file docker-compose.yml up -d
  fi
}

build_project() {
  echo "----- Building -----"
  sh ./gradlew bootJar
}

run_project() {
  echo "----- Running -----"
  if [ "$WITH_CONTAINER" = '-docker' ]; then
    docker build --t customer-search .
    sudo docker-compose --file ./docker/docker-compose.yml up -d
    docker logs --tail=500 -f customer-search
  else
    ${JAVA_HOME}/bin/java -jar build/libs/CustomerSearch-*.jar
  fi
}

#run everthing
run_docker_compose && build_project && run_project