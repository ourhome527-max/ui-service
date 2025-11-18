# 1. Java 17 (JDK)의 경량화 버전을 기반 이미지로 사용
FROM openjdk:17-jdk-slim

# 2. 컨테이너 내부의 작업 디렉토리 설정
WORKDIR /app

# 3. (가장 중요) Gradle로 빌드된 .jar 파일을 컨테이너로 복사
#    Gradle은 'build/libs/' 폴더에 jar를 생성합니다.
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} app.jar

# 4. application.yml에서 8081 포트를 사용하도록 설정했으므로
#    컨테이너의 8081 포트를 외부에 노출
EXPOSE 8081

# 5. 컨테이너가 시작될 때 이 명령어를 실행하여 Spring Boot 앱을 구동
ENTRYPOINT ["java", "-jar", "/app.jar"]