FROM nexus.ezdrav.kz:8100/eh-openjdk:11-jre-slim

RUN apt-get update -y && apt-get install -y libfreetype6

COPY ./target/eh-physicalExaminationNew.jar app.jar

ENTRYPOINT ["java", "-XX:+UnlockExperimentalVMOptions", "-XX:+UseZGC", "-XX:+HeapDumpOnOutOfMemoryError", "-XX:HeapDumpPath=/root/dump/oom.bin", "-jar", "app.jar"]
