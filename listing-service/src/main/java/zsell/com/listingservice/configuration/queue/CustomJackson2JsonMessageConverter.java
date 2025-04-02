package zsell.com.listingservice.configuration.queue;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConversionException;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.lang.NonNull;

import java.util.Objects;

@Component
public class CustomJackson2JsonMessageConverter extends Jackson2JsonMessageConverter {

    @Override
    @NonNull
    public Object fromMessage(@NonNull Message message) throws MessageConversionException {
        MessageProperties messageProperties = message.getMessageProperties();
        if (Objects.nonNull(messageProperties) && (messageProperties.getContentType() == null || messageProperties.getContentType().trim().isEmpty())) {
            messageProperties.setContentType(MediaType.APPLICATION_JSON_VALUE);
        }
        return super.fromMessage(message);
    }
}