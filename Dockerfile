# 1. Java 17 (JDK)의 경량화 버전을 기반 이미지로 사용
FROM eclipse-temurin:17-jdk

# 2. 컨테이너 내부의 작업 디렉토리 설정
WORKDIR /app

# 3. (⭐️ 수정됨) .jar 파일의 '정확한 이름'을 직접 명시
#    (ARG와 *.jar 대신, Gradle 빌드 결과물의 실제 이름을 사용)
COPY build/libs/ui-service-0.0.1-SNAPSHOT.jar app.jar

# 4. Spring Boot 앱은 기본 8080 포트를 사용합니다.
#    (deployment.yaml의 targetPort와 일치)
EXPOSE 8080

# 5. 컨테이너가 시작될 때 이 명령어를 실행 (수정된 경로)
ENTRYPOINT ["java", "-jar", "app.jar"]