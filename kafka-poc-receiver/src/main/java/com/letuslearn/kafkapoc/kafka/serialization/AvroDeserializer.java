package com.letuslearn.kafkapoc.kafka.serialization;

import java.util.Arrays;
import java.util.Map;

import javax.xml.bind.DatatypeConverter;

import org.apache.avro.io.DatumReader;
import org.apache.avro.io.Decoder;
import org.apache.avro.io.DecoderFactory;
import org.apache.avro.specific.SpecificDatumReader;
import org.apache.avro.specific.SpecificRecordBase;
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Deserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.letuslearn.avro.Message;


public class AvroDeserializer<T extends SpecificRecordBase> implements Deserializer<T> {

	  public Class<T> getTargetType() {
		return targetType;
	}
	public void setTargetType(Class<T> targetType) {
		this.targetType = targetType;
	}

	private static final Logger LOGGER = LoggerFactory.getLogger(AvroDeserializer.class);

	  protected  Class<T> targetType;

	  
	  public AvroDeserializer()
	  {
		 this.targetType = (Class<T>) Message.class;
		  LOGGER.debug(":::::::::::TargetType:::::::::{}",this.targetType);
		 
	  }
	  public AvroDeserializer(Class<T> targetType) {
	    this.targetType=targetType;
	  }

	  @Override
	  public void close() {
	    // No-op
	  }

	  @Override
	  public void configure(Map<String, ?> arg0, boolean arg1) {
	    // No-op
	  }

	  @SuppressWarnings("unchecked")
	  @Override
	  public T deserialize(String topic, byte[] data) {
	    try {
	      T result = null;

	      if (data != null) {
	        LOGGER.debug("data='{}'", DatatypeConverter.printHexBinary(data));

	        DatumReader<T> datumReader =
	            new SpecificDatumReader<>(targetType.newInstance().getSchema());
	        Decoder decoder = DecoderFactory.get().jsonDecoder(this.targetType.newInstance().getSchema(), new String(data));

	        result = (T) datumReader.read(null, decoder);
	        LOGGER.debug("deserialized data='{}'", result);
	      }
	      return result;
	    } catch (Exception ex) {
	      throw new SerializationException(
	          "Can't deserialize data '" + Arrays.toString(data) + "' from topic '" + topic + "'", ex);
	    }
	  }
	}
