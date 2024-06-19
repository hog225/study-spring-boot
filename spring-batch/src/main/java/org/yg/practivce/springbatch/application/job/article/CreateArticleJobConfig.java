package org.yg.practivce.springbatch.application.job.article;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.data.RepositoryItemWriter;
import org.springframework.batch.item.data.builder.RepositoryItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.yg.practivce.springbatch.application.job.article.param.CreateArticleJobParam;
import org.yg.practivce.springbatch.application.model.ArticleModel;
import org.yg.practivce.springbatch.domain.article.Article;
import org.yg.practivce.springbatch.domain.article.repository.ArticleRepository;

import java.util.List;

// Job이 실행중에 종료가 되면 batch_job_excution EXIT_CODE 가 UNKNOWN일 거다 이 상태에서 같은 Job ID 로 Batch를 실행 시키면 실행이 되지 않는다.
@Configuration
@Slf4j
@RequiredArgsConstructor
public class CreateArticleJobConfig {
    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final ArticleRepository articleRepository;
    private final JdbcTemplate jdbcTemplate;
    private final CreateArticleJobParam createArticleJobParam;

    @Bean
    public void printMan(){
        log.info("BEAN~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    }

    @Bean
    public Job createArticleJob(){
        log.info("createArticleJob");
        return jobBuilderFactory.get("createArticleJob")
                .incrementer(new RunIdIncrementer())
                .start(createArticleStep())
                .build();

    }
    //Article.csv 를 읽어서 Batch로 디비에 저장
    @Bean
    @JobScope
    public Step createArticleStep() {
        return stepBuilderFactory.get("createArticleStep")
//                .tasklet((contribution, chunkContext) -> {
//                    log.info("Step !!!!!!!!");
//                    return RepeatStatus.FINISHED;
//                })
                .<ArticleModel, Article>chunk(10)// 쉽게 말해서 update 하는 사이즈 chunkSize개씩 모아서 커밋 함
                .reader(createArticleReader())
                .processor(createArticleProcessor())
                .writer(createArticleRepositoryWriter())
                .build();
    }

    // application Arg 에 데이터가 있을 시 Job Param으로 가져올 수 있다.
    @Bean
    @StepScope
    public FlatFileItemReader<ArticleModel> createArticleReader(){
        log.info("param!!! {}", createArticleJobParam.getName());
        return new FlatFileItemReaderBuilder<ArticleModel>()
                .name("createArticleReader")
                .resource(new ClassPathResource("Article.csv"))
                .delimited()
                .names("title", "content")
                .fieldSetMapper(new BeanWrapperFieldSetMapper<>())
                .targetType(ArticleModel.class)
                .build();
    }

    @Bean
    public ItemProcessor<ArticleModel, Article> createArticleProcessor(){
        return articleModel -> Article.builder()
                .title(articleModel.getTitle())
                .content(articleModel.getContent())
                .build();

    }


    @Bean
    public RepositoryItemWriter createArticleRepositoryWriter() {
        return new RepositoryItemWriterBuilder<Article>()
                .repository(articleRepository)
                .build();

    }
    //JDBC 로 하면 훨신 빠르다. rewriteBatchedStatements=true 를 application properties에서 해주면 더 빠르다
    //JPA로 하면 느림
    @Bean
    public ItemWriter<Article> createArticleWriter() {
        return articles -> jdbcTemplate.<Article>batchUpdate("insert into Article (title, content, createdAt) values (?, ? ,?)",
                articles,
                100,
                (ps, article) -> {
                    ps.setObject(1, article.getTitle());
                    ps.setObject(2, article.getContent());
                    ps.setObject(3, article.getCreatedAt());
                });
    }


}
