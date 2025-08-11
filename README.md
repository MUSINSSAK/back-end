-----

# back-end

## 🛠️ 기술 스택 (Tech Stack)

  * **Language**: Java 17
  * **Framework**: Spring Boot 3.x
  * **Data**: Spring Data JPA, QueryDSL
  * **Database**: MySQL 8.0
  * **DB Migration**: Flyway
  * **Build**: Gradle
  * **Container**: Docker

-----

## 🚀 개발 환경 설정 (Setup)

### 1\. 사전 준비 (Prerequisites)

  * [Docker Desktop](https://www.docker.com/products/docker-desktop)
  * JDK 17
  * IntelliJ IDEA 또는 선호하는 IDE

### 2\. 프로젝트 클론

```bash
git clone https://github.com/MUSINSSAK/back-end.git
cd back-end
```

### 3\. 환경 변수 설정

프로젝트 루트 디렉토리에 **`.env`** 파일을 생성하고 아래 내용을 채워주세요. 이 파일은 민감 정보를 관리하며, `.gitignore`에 등록되어 있어 Git에 올라가지 않습니다.

**.env**

```
# DB Connection Info
DB_USERNAME=xxxx
DB_PASSWORD=xxxx

# Docker-compose Variables
MYSQL_ROOT_PASSWORD=xxxx
MYSQL_DATABASE=musinsa_shop
MYSQL_USER=MUSINSSAK
MYSQL_PASSWORD=xxxx
```

> **Warning**
> `.env` 파일의 `DB_` 변수와 `MYSQL_` 변수는 각각 `application.yml`과 `docker-compose.yml`에서 사용됩니다.

-----

## ▶️ 애플리케이션 실행 (Run)

### 1\. 데이터베이스 실행 (Docker)

프로젝트 루트에서 아래 명령어를 실행하여 MySQL 데이터베이스 컨테이너를 실행합니다.

```bash
docker-compose up -d
```

  * **DB 접속 정보**: `host: localhost`, `port: 3300`

### 2\. 스프링 부트 애플리케이션 실행

IntelliJ에서 `MusinssakApplication.java`를 실행하거나, 터미널에서 아래 명령어를 입력합니다.

```bash
./gradlew bootRun
```

애플리케이션이 시작되면, **Flyway**가 `src/main/resources/db/migration` 경로의 SQL 파일을 읽어 자동으로 테이블을 생성하고 관리합니다.

-----

## ⚙️ 주요 설정 및 규칙

### 1\. 데이터베이스

  * **Flyway**: DB 스키마는 Flyway를 통해 버전 관리됩니다. 테이블 구조를 변경할 때는 `V{버전}___{설명}.sql` 형식의 마이그레이션 파일을 새로 생성해야 합니다.
  * **QueryDSL**: Type-safe한 동적 쿼리를 위해 QueryDSL을 사용합니다.
      * Q-Type 클래스는 Gradle 빌드 시 자동으로 생성되며(`build` 폴더 내부), Git에 커밋하지 않습니다.
      * 커스텀 리포지토리는 `Repository - RepositoryCustom - RepositoryImpl` 구조를 따릅니다.

### 2\. Git 사용 규칙

#### 브랜치 전략

  * `main`: 제품으로 출시될 수 있는 브랜치
  * `dev`: 다음 출시 버전을 개발하는 브랜치
  * `feat/{기능}`: 기능 개발 브랜치
  * `fix/{이슈}`: 버그 수정 브랜치
  * `chore/{설정}`: 빌드, 설정 등 기타 작업 브랜치

**작업 흐름**: `feat` 브랜치에서 기능을 개발한 후 `dev` 브랜치로 Pull Request를 보내 코드 리뷰 후 머지합니다.

#### 커밋 메시지 컨벤션

커밋 메시지는 아래 형식에 맞춰 작성합니다.

| 태그     | 설명                                   |
| :------- | :------------------------------------- |
| `feat`   | 새로운 기능 추가                       |
| `fix`    | 버그 수정                              |
| `chore`  | 빌드, 설정 등 코드 외적인 작업         |
| `docs`   | 문서 수정 (README 등)                  |
| `style`  | 코드 스타일 수정 (포맷팅, 세미콜론 등) |
| `refactor` | 코드 리팩토링 (기능 변화 없음)         |
| `test`   | 테스트 코드 추가 또는 수정             |

**예시**:

```
git commit -m "feat: 상품 검색을 위한 QueryDSL 로직 추가"
```
