build_project() {
  echo "----- Building restaurant search rank -----"
  sh ./gradlew bootJar
}

run_project() {
  echo "----- Running restaurant search rank -----"
    docker build -t search-rank .
    docker run search-rank
    docker logs --tail=500 -f search-rank
}

build_project && run_project