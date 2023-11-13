# demo-mybatis-chainedtransactionmanager
demo mybatis chained transaction manager

ChainedTransactionManager는 보통의 경우에는 잘 동작한다. 설정된 다수 데이터소스에 대해 동시 commit/rollback 이 정상적으로 동작한다.

다만, commit/rollback 동작 시 문제가 되는 경우, 예을들어 커넥션 timeout이 난경우나 커넥션 접속이 끊긴 상황 등의 예외적인 경우에 일부 commit이 일부 rollback이 발생할 수 있다. 즉, 트랜잭션의 atomicity가 보장이 안된다.

ChainedTransactionManager 는 spring-boot 2.5 버전부터 deprecated 되었으나, 3.1.5 버전(현시점 최신버전)에서도 제거되지 않은 상태로 남아있다. 

이와같은 제약사항([1]트랜잭션의 미보장, [2]deprecated 상태) 이 존재함에서 사용하겠다고 개발팀의 합의한다면 사용하는 것을 고려해볼 수 도 있을 듯하다.
