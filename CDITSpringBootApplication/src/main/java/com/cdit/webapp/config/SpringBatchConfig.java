package com.cdit.webapp.config;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import com.cdit.webapp.model.Employee;
import com.cdit.webapp.processor.EmployeeProcessor;

@Configuration
@EnableBatchProcessing
public class SpringBatchConfig {
	
	@Autowired
	public JobBuilderFactory jobBuilderFactory;
	
	@Autowired
	public StepBuilderFactory stepBuilderFactory;
	
	@Autowired
	public DataSource dataSource;
	
	@Bean
	public FlatFileItemReader<Employee> reader() {
		FlatFileItemReader<Employee> reader = new FlatFileItemReader<Employee>();
		ClassPathResource resource = new ClassPathResource("employees.csv");
		reader.setResource(resource);
		
        reader.setLineMapper(new DefaultLineMapper<Employee>() {{
            setLineTokenizer(new DelimitedLineTokenizer() {{
                setNames(new String[] { "firstName", "lastName", "salary"});
            }});
            setFieldSetMapper(new BeanWrapperFieldSetMapper<Employee>() {{
                setTargetType(Employee.class);
            }});
        }});
	
		return reader;
	}
	
	@Bean
	public JdbcBatchItemWriter<Employee> writer() {
		JdbcBatchItemWriter<Employee> writer = new JdbcBatchItemWriter<Employee>();
		writer.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>());
		String sql = "INSERT INTO employee(firstName, lastName, salary)";
		sql += " VALUES (:firstName, :lastName, :salary)";
		writer.setSql(sql);
		writer.setDataSource(dataSource);
		return writer;
	}
	
	@Bean
	public Step step() {
		return stepBuilderFactory.get("step").<Employee, Employee>chunk(3)
		.reader(reader())
		.writer(writer())
		.build();
				
	}
	
	@Bean
	public Job importUserJob() {
		return jobBuilderFactory.get("importUserJob")
		.incrementer(new RunIdIncrementer())
		.flow(step())
		.end()
		.build();
	}

}
