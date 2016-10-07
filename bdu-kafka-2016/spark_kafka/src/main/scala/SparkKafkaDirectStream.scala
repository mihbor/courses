import kafka.serializer.StringDecoder
import org.apache.spark._
import org.apache.spark.streaming._
import org.apache.spark.streaming.kafka.KafkaUtils
import org.apache.spark.SparkConf

object SparkKafkaDirectStream {
  def main(args:Array[String]) {
    if (args.length < 2) {
      System.err.println(s"""
          |Usage: SparkKafkaDirectStream <brokers> <topics>
          |  <brokers> is a list of one or more Kafka brokers
          |  <topics> is a list of one or more kafka topics to consume from
          |
          """.stripMargin)
      System.exit(1);
    }
    val Array(brokers, topics) = args
    
    val sparkConf = new SparkConf().setAppName("SparkKafkaDirectStream").setMaster("local[2]")
    val ctx = new StreamingContext(sparkConf, Seconds(10))
    
    val kafkaParams = Map[String, String]("metadata.broker.list" -> brokers)
    val topicSet = topics.split(",").toSet
    val kafkaStream =  KafkaUtils.createDirectStream[String, String, StringDecoder, StringDecoder](ctx, kafkaParams, topicSet)
    
    val lines = kafkaStream.map(_._2)
    
    lines.print()
    
    ctx.start()
    ctx.awaitTermination()
  }
}