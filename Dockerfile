# --- Stage 1: Build ---
# 프로젝트의 Java 버전에 맞춰 'jdk17' 이미지를 빌더로 사용합니다.
FROM gradle:jdk17-jammy as builder

# 작업 디렉토리 설정
WORKDIR /app

# 먼저 빌드 설정 파일만 복사하여 의존성을 다운로드합니다. (Docker 캐싱 효율 극대화)
COPY build.gradle settings.gradle ./
RUN gradle build --no-daemon -x test

# 전체 소스 코드를 복사합니다.
COPY src ./src

# 애플리케이션을 빌드하여 .jar 파일을 생성합니다. (-x test는 빌드 시 테스트를 건너뛰는 옵션)
RUN gradle build --no-daemon -x test


# --- Stage 2: Final Image ---
# 실제 실행에는 JRE(Java Runtime Environment)만 있으면 되므로, 더 가벼운 JRE 이미지를 사용합니다.
FROM eclipse-temurin:17-jre-jammy

# 작업 디렉토리 설정
WORKDIR /app

# Builder 스테이지에서 생성된 실행 가능한 .jar 파일만 복사해옵니다.
# 복사하면서 파일 이름을 app.jar로 통일하면, 나중에 버전이 바뀌어도 ENTRYPOINT를 수정할 필요가 없습니다.
COPY --from=builder /app/build/libs/MUSINSSAK-0.0.1-SNAPSHOT.jar app.jar

# 애플리케이션 포트 번호인 8080 포트를 외부에 노출합니다.
EXPOSE 8080

# 컨테이너가 시작될 때 'java -jar app.jar' 명령어를 실행하여 애플리케이션을 구동합니다.
ENTRYPOINT ["java", "-jar", "app.jar"]