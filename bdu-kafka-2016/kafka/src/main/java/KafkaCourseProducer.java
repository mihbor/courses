import java.util.Properties;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.StringSerializer;

import static org.apache.kafka.clients.producer.ProducerConfig.*;

public class KafkaCourseProducer {
	public static void main(String[] args) throws InterruptedException {
		Properties props = new Properties();
		props.put(BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
		props.put(VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
		props.put(KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
		try(KafkaProducer<String, String> producer = new KafkaProducer<>(props)) {
			
			for(int i = 0; i < 100; i++) {
				producer.send(new ProducerRecord<String, String>("test",  "key"+i, "Message number "+ i), callback);
				System.out.println("Message " + i + " sent.");
				Thread.sleep(5);
			}
		}
	}
	
	private static Callback callback = new Callback(){
		public void onCompletion(RecordMetadata meta, Exception e) {
			if(e == null) System.out.println("Message delivered to partition " + meta.partition() + " with offset " + meta.offset());
			else e.printStackTrace();
		}
	};

}
