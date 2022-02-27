package yg.study.studyspringbatch.batchjob.config;

import org.springframework.batch.core.configuration.annotation.DefaultBatchConfigurer;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.SimpleBatchConfiguration;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.support.JobRepositoryFactoryBean;
import org.springframework.batch.support.DatabaseType;
import org.springframework.batch.support.transaction.ResourcelessTransactionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;

//@Configuration
//public class BatchDataConfig {
//    @Bean
//    public DataSource dataSource() {
//        EmbeddedDatabaseBuilder embeddedDatabaseBuilder = new EmbeddedDatabaseBuilder();
//        return embeddedDatabaseBuilder.addScript("classpath:org/springframework/batch/core/schema-drop-h2.sql")
//                .addScript("classpath:org/springframework/batch/core/schema-h2.sql")
//                .setType(EmbeddedDatabaseType.H2)
//                .build();
//    }
//
////    @Bean
////    public ResourcelessTransactionManager transactionManager() {
////        return new ResourcelessTransactionManager();
////    }
////
////    @Bean
////    public JobRepository jobRepository() throws Exception {
////        JobRepositoryFactoryBean factory = new JobRepositoryFactoryBean();
////        factory.setDatabaseType(DatabaseType.H2.getProductName());
////        factory.setDataSource(dataSource());
////        factory.setTransactionManager(transactionManager());
////        return factory.getObject();
////    }
//}
@Configuration
@EnableBatchProcessing
public class BatchDataConfig extends DefaultBatchConfigurer {

    @Override
    public void setDataSource(DataSource dataSource) {
        // If we don't provide a datasource, an in-memory map will be used.
    }
}