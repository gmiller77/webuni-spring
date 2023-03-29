package hu.webuni.hr.greg77.config;
//package hu.webuni.hr.minta.config;
//
//import java.time.format.DateTimeFormatter;
//
//import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
//import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
//
//@Configuration
//public class JacksonConfig {
//
//	private static final DateTimeFormatter PATTERN = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
//
//	@Bean
//	public Jackson2ObjectMapperBuilderCustomizer jacksonCustomizer() {
//		
//		return builder -> {
//			builder.deserializers(
//					new LocalDateTimeDeserializer(PATTERN));
//			builder.serializers(
//					new LocalDateTimeSerializer(PATTERN));
//		};
//		
//	}
//	
//}
