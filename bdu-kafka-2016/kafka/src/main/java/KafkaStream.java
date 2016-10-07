import org.apache.kafka.streams.processor.Processor;
import org.apache.kafka.streams.processor.ProcessorContext;

public class KafkaStream implements Processor<String, String> {

	@Override
	public void close () {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void init (ProcessorContext arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void process (String dummy, String line) {
//		KTable wordCounts = line.fla
	}

	@Override
	public void punctuate (long arg0) {
		// TODO Auto-generated method stub
		
	}
	

}
