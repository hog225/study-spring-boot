package org.yg.practivce.springbatch.application.job;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.yg.practivce.springbatch.application.model.ArticleModel;
import org.yg.practivce.springbatch.domain.entity.Article;

@Configuration
@Slf4j
@RequiredArgsConstructor
public class CreateArticleJobConfig {
    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job createArticleJob(){
        log.info("createArticleJob");
        return jobBuilderFactory.get("createArticleJob")
                .incrementer(new RunIdIncrementer())
                .start(createArticleStep())
                .build();

    }

    @Bean
    public Step createArticleStep() {
        return stepBuilderFactory.get("createArticleStep")
                .tasklet((contribution, chunkContext) -> {
                    log.info("Step !!!!!!!!");
                    return RepeatStatus.FINISHED;
                })
                .build();
    }

    @Bean
    public FlatFileItemReader<ArticleModel> createArticleReader(){
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

    @Bean re
}
