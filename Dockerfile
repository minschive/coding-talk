# 베이스 이미지로 OpenJDK 사용
FROM openjdk:17-jdk-slim

# 작업 디렉토리 설정
WORKDIR /app

# 로컬의 JAR 파일을 컨테이너로 복사
COPY target/coding-talk-0.0.1-SNAPSHOT.jar app.jar

# 컨테이너 실행 시 Spring Boot 애플리케이션 실행
ENTRYPOINT ["java", "-jar", "app.jar"]
