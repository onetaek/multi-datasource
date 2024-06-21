# Multi Datasource 설정과 JTA를 활용한 분산 트랜잭션 관리

<div align="center">
  <img src="https://github.com/onetaek/multi-datasource/assets/86419261/a1256209-2af8-46d1-b096-467f25d91117" width="600"/>
</div>


## 소개
- 이 프로젝트는 Spring Boot를 사용하여 다중 데이터 소스 설정 및 JTA(Java Transaction API)를 활용한 분산 트랜잭션 관리를 구현합니다.
- 여러 데이터베이스 간의 트랜잭션 일관성을 보장하기 위해 Bitronix Transaction Manager를 사용합니다.
- 코드를 통해 쉽게 실습할 수 있도록 H2 DB를 사용하여 별도의 DB설정 없이 바로 코드를 받고 테스트 해볼 수 있도록 코드를 적성하였습니다. 
- 트랜잭션 롤백이 동작하는 것을 테스트할 수 있는 API를 개발하였습니다.

## 버전
- Java 17
- Spring Boot 3.3

## 설정 방법
1. **프로젝트 클론**
    ```bash
    git clone https://github.com/onetaek/multi-datasource-practice.git
    ```
2. **의존성 설치**
    ```bash
    ./gradlew clean build
    ```
3. **환경 설정**
    `application.yml` H2 DB의 메모리 모드로 동작하도록 구성하여 따로 설정을 건드릴 것이 없습니다.

## velog 정리

<a href="https://velog.io/@wontaekoh/Multi-Datasource-%EC%84%A4%EC%A0%95%EA%B3%BC-JTA%EB%A5%BC-%ED%99%9C%EC%9A%A9%ED%95%9C-%EB%B6%84%EC%82%B0-%ED%8A%B8%EB%9E%9C%EC%9E%AD%EC%85%98-%EA%B4%80%EB%A6%AC">
  <img src="https://velog-readme-stats.vercel.app/api?name=wontaekoh&slug=Multi-Datasource-설정과-JTA를-활용한-분산-트랜잭션-관리" />
</a>
