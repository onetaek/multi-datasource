# Multi Datasource 설정과 JTA를 활용한 분산 트랜잭션 관리

<div align="center">
  <img src="https://github.com/onetaek/multi-datasource-practice/assets/86419261/7edb54f5-ab36-4b26-adf4-f04afabf93b1" width="400"/>
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

## 주요 코드 설명
### DB(firstdb, seconddb, thirddb) 데이터 소스 설정
- `FirstDatasourceConfig.java`
- `SecondDatasourceConfig.java`
- `ThirdDatasourceConfig.java`

### XA 데이터 소스 설정
- `XaDataSourceConfig.java` : firstdb, seconddb, thirddb 트랜잭션을 하나로 관리하기 위한 JTA 설정

### Properties 설정
- `DatabaseProperties.java` : firstdb, seconddb, thirddb 각각의 설정정보를 가져오는 Properties 클래스로 다중 DB연결을 할 때는 ORM을 사용할 때 네이밍 전략 등의 기본 설정 값들이 적용되지 않아 따로 속성 값을 명시해줘야합니다.

### 예제 사용법

- 3가지 DB에 데이터를 저장하는 API로 sequence(1,2,3만 사용)를 통해 저장 순서를 지정하고, throwError를 통해 저장 후 예외를 던질지 말지 선택할 수 있습니다.
```bash
curl -X POST "http://localhost:8080/" -H "Content-Type: application/json" -d
'{
    "firstDb": {
        "throwError": true,
        "sequence": 3,
        "stringColumn": "first db",
        "integerColumn": 100,
        "bigDecimalColumn": 111.111,
        "localDateTimeColumn": "2024-06-17T10:15:30"
    },
    "secondDb": {
        "throwError": false,
        "sequence": 2,
        "stringColumn": "second db",
        "integerColumn": 200,
        "bigDecimalColumn": 22.222,
        "localDateTimeColumn": "2024-06-18T10:15:30"
    },
    "thirdDb": {
        "throwError": false,
        "sequence": 1,
        "stringColumn": "third db",
        "integerColumn": 300,
        "bigDecimalColumn": 333.333,
        "localDateTimeColumn": "2024-06-19T10:15:30"
    }
}'
```

- 3가지 DB의 데이터를 각각 LIST로 볼 수 있습니다.
```bash
curl -X GET "http://localhost:8080/"
```
- 아래와 같이 Response가 반환 됩니다.
```json
{
    "secondDb": [
        {
            "id": 7,
            "stringColumn": "second db",
            "integerColumn": 200,
            "bigDecimalColumn": 22.22,
            "localDateTimeColumn": "2024-06-18T10:15:30"
        }
    ],
    "thirdDb": [
        {
            "id": 7,
            "stringColumn": "third db",
            "integerColumn": 300,
            "bigDecimalColumn": 333.33,
            "localDateTimeColumn": "2024-06-19T10:15:30"
        }
    ],
    "firstDb": [
        {
            "id": 7,
            "stringColumn": "first db",
            "integerColumn": 100,
            "bigDecimalColumn": 111.11,
            "localDateTimeColumn": "2024-06-17T10:15:30"
        }
    ]
}

```

- 3가지 DB의 데이터를 모두 DELETE하는 API 입니다.
```bash
curl -X DELETE "http://localhost:8080/"
```

## 배운 점
다중 데이터베이스 환경에서의 트랜잭션 관리와 JTA(Java Transaction API)를 활용한 분산 트랜잭션 처리에 대해 깊이 있게 학습할 수 있었습니다. 
특히, @Transactional의 동작 방식에 대해 자세히 공부할 수 있었고, Bitronix Transaction Manager를 사용하여 여러 데이터 소스 간의 일관성을 유지하는 방법을 이해하게 되었습니다.
