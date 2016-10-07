import java.util.Arrays;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.KafkaException;
import org.apache.kafka.common.serialization.StringDeserializer;

public class KafkaCourseConsumer {

	public static void main(String[] args){
		Properties props = new Properties();
		props.put("bootstrap.servers", "localhost:9092");
		props.put("group.id", "blah");
		props.put("enable.auto.commit", "true");
		props.put("auto.commit.intervalms", 1000);
		props.put("session.timeout.ms", "30000");
		props.put("key.deserializer", StringDeserializer.class.getName());
		props.put("value.deserializer", StringDeserializer.class.getName());
		
		try (KafkaConsumer<String,String> consumer = new KafkaConsumer<>(props)){
		
			consumer.subscribe(Arrays.asList("test"));
			
			while(true) {
				ConsumerRecords<String,String> records = consumer.poll(100);
				for (ConsumerRecord<String,String> record: records) System.out.printf("offset = %d, key = %s \n", record.offset(), record.key(), record.value());
			}
		} catch (KafkaException e){
			e.printStackTrace();
		}
		
	}
}
