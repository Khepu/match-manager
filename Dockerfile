FROM maven:3.8.5-openjdk-17 as builder

COPY . .

ARG MAVEN_PROFILE="clean-release"
ARG EXTRA_ARGS=""

RUN mkdir -p /root/.m2 \
    && mvn package -f ./pom.xml

FROM openjdk:17.0.2-jdk-slim as final

ENV SERVER_PORT=8080 \
    USER=matchmanager \
    DB_PASSWORD=postgres \
    DB_USERNAME=postgres \
    DB_HOST=localhost \
    DB_PORT=5432 \
    DB_DATABASE="match_manager" \
    DB_THREADS=20 \
    DB_SCHEDULER_BUFFER_SIZE=100 \
    DB_SCHEDULER_SECONDS_TO_LIVE=300

EXPOSE $SERVER_PORT

COPY --from=builder /scripts/run.sh /run.sh

RUN useradd -ms /bin/bash $USER \
    && mkdir -p /opt/$USER/match-manager \
    && chown -R $USER:$USER /opt/$USER/ \
    && chmod 0700 /run.sh

COPY --chown=$USER:$USER --from=builder /match-manager-core/target/match-manager.jar /opt/$USER/match-manager/

WORKDIR /opt/$USER/match-manager

ENTRYPOINT ["/run.sh"]
