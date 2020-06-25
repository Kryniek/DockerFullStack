# DockerFullStack
Docker Compose = DB + Backend + Frontend

# Usage

Just run `docker-compose up -d`. All services will build and run in detached mode (in background).

MySQL database service can be connected by:
- Host: `127.0.0.1`
- Port: `9997`
- Username: `root`
- Password: `root`
- Default schema (optional): `mydb`

Spring boot backend service expose only one endpoint to retrieve users. Can be reached by: `http://localhost:9999/api/users`.

Angular frontend service can be accessed by: `http://localhost:9998/`.

# Docker commands --help (in polish)
- Uruchamianie nowego kontenera

`docker run <id>`

- Uruchamianie kontenera już istniejącego

`docker start <id>`

- Zatrzymywanie kontenera

`docker stop <id>`

- Zatrzymywanie wszystkich kontenerów

`docker stop $(docker ps -aq)`

- Ściąganie obrazu z repozytorium dockera

`docker pull <id>`

- Ściąganie obrazu z repozytorium dockera o konkretnej wersji

`docker pull <id>:<version> //domyślnie <id>:latest`

- Przeglądanie uruchomionych kontenerów

`docker ps`

- Przeglądanie lokalnych obrazów

`docker images`

- uruchomienie kontenera z konkretnym id

`docker run --name <id>`

- Uruchamianie kontenera z bashem ubuntu i wejście do niego

`docker run -it --name <id> ubuntu /bin/bash`

- Uruchamianie kontenera z połączonymi portami

`docker run -p 80:8080 <id>`

- Uruchamianie kontenera w trybie "detached", czyli w tle

`docker run -d <id>`

- Usuwanie kontenera

`docker rm <id>`

- Usuwanie obrazu

`docker rmi <id>`

- Zapisywanie obrazów do plików spakowanych

`docker save <id> -o file.zip`

- Wejdź do aktualnie uruchomionego kontenera

`docker exec -it <id> /bin/bash`

- Usuwa kontener zaraz po zatrzymaniu kontenera

`docker run --rm <id>`

- Uruchomienie statycznej strony za pomocą nginx

`docker run --rm -d -p 8080:80 --name nginx -v C:/Users/<Path to directory>:/usr/share/nginx/html nginx`

- Kopiowanie zawartości folderu do uruchomionego kontenera

`docker cp <directory>/. <id>:<directory in container>`

- Listowanie zawartości folderu w uruchomionym kontenerze

`docker exec <id> ls <container directory>`

- Tworzenie własnego obrazu (z plikami). Kontener bazowy musi być aktualnie uruchomiony

`docker commit <base container id> <user>:<tag>`
```
Przykład:
docker commit nginx kryniek:nginx
```

- Uruchamianie kontenera z bazą danych

`docker run --name <id> --rm -d -e MYSQL_ROOT_PASSWORD=<root password> -e MYSQL_DATABASE=<db name> -p 3333:3306 -v <db volume name>:/var/lib/mysql mysql:5.7`

- Uruchamianie kontenera z node.js działającym na lokalnym kodzie źródłowym

`docker run -p 8080:3000 -v $(pwd):/var/www -w "/var/www" node npm start`

- Uruchamianie kontenera z Spring Boot działającym na lokalnym kodzie źródłowym

1.Stworzenie pliku: backend.dockerfile

```
FROM maven:3.6.3-openjdk-11
COPY ./Backend /usr/src/Backend
WORKDIR /usr/src/Backend
EXPOSE 8080
ENTRYPOINT ["mvn", "spring-boot:run"]
```

2.Zbudowanie obrazu:

`docker build -f backend.dockerfile -t kryniek/backend .`

3.Uruchomienie kontenera:

`docker run --name backend --rm -p 9999:8080 kryniek/backend:latest`

- Uruchamianie kontenera z Angularem działającym na lokalnym kodzie źródłowym

1.Stworzenie pliku: frontend.dockerfile

```
FROM node:8.16.2
RUN npm install -g @angular/cli@7.3.10
COPY ./Frontend /home/node/Frontend
WORKDIR /home/node/Frontend
EXPOSE 4200
ENTRYPOINT ["ng", "serve", "--host", "0.0.0.0"]
```

2.Zbudowanie obrazu:

`docker build -f frontend.dockerfile -t kryniek/frontend .`

3.Uruchomienie kontenera:

`docker run --name frontend --rm -p 9998:4200 kryniek/frontend:latest`

- Usuwanie wszystkich zastopowanych kontenerów

`docker container prune`

- Usuwanie wszystkich obrazów "śmieci"

`docker rmi $(docker images -f "dangling=true" -q)`