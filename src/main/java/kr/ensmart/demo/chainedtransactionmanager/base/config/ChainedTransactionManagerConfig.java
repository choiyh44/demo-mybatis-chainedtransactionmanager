package kr.ensmart.demo.chainedtransactionmanager.base.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.transaction.ChainedTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class ChainedTransactionManagerConfig {
	@Bean(name="db1Db2TransactionManager")
    @Autowired
    public PlatformTransactionManager db1Db2TransactionManager(   
        @Qualifier("db1TransactionManager") PlatformTransactionManager db1TransactionManager
        , @Qualifier("db2TransactionManager") PlatformTransactionManager db2TransactionManager)
    {
        return new ChainedTransactionManager(db1TransactionManager, db2TransactionManager);
    }

	@Bean(name="db2Db1TransactionManager")
    @Autowired
    public PlatformTransactionManager db2Db1TransactionManager(   
        @Qualifier("db2TransactionManager") PlatformTransactionManager db2TransactionManager
        , @Qualifier("db1TransactionManager") PlatformTransactionManager db1TransactionManager)
    {
        return new ChainedTransactionManager(db2TransactionManager, db1TransactionManager);
    }

}
