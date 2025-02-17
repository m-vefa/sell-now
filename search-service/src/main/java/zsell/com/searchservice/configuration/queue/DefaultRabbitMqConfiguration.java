package zsell.com.searchservice.configuration.queue;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.config.RetryInterceptorBuilder;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.retry.RejectAndDontRequeueRecoverer;
import org.springframework.boot.autoconfigure.amqp.RabbitProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.retry.interceptor.RetryOperationsInterceptor;

@Configuration
@RequiredArgsConstructor
public class DefaultRabbitMqConfiguration {

    private final RabbitProperties rabbitProperties;
    private final CustomJackson2JsonMessageConverter customJackson2JsonMessageConverter;

    @Bean
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory cachingConnectionFactory = new CachingConnectionFactory();
        cachingConnectionFactory.setHost(rabbitProperties.getHost());
        cachingConnectionFactory.setPort(rabbitProperties.getPort());
        cachingConnectionFactory.setUsername(rabbitProperties.getUsername());
        cachingConnectionFactory.setPassword(rabbitProperties.getPassword());
        return cachingConnectionFactory;
    }

    @Bean
    public RabbitAdmin rabbitAdmin(ConnectionFactory connectionFactory) {
        return new RabbitAdmin(connectionFactory);
    }


    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(customJackson2JsonMessageConverter);
        rabbitTemplate.setChannelTransacted(true);
        return rabbitTemplate;
    }

    @Bean
    @Primary
    public RetryOperationsInterceptor rabbitRetryAdvice() {
        RabbitProperties.ListenerRetry retryConfig = rabbitProperties.getListener().getSimple().getRetry();
        return RetryInterceptorBuilder
                .stateless()
                .maxAttempts(retryConfig.getMaxAttempts())
                .backOffOptions(
                        retryConfig.getInitialInterval().toMillis(),
                        retryConfig.getMultiplier(),
                        retryConfig.getMaxInterval().toMillis()
                )
                .recoverer(new RejectAndDontRequeueRecoverer())
                .build();
    }
}
