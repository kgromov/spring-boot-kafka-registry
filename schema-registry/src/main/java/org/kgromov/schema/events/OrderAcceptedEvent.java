/**
 * Autogenerated by Avro
 *
 * DO NOT EDIT DIRECTLY
 */
package org.kgromov.schema.events;

@org.apache.avro.specific.AvroGenerated
public interface OrderAcceptedEvent {
  public static final org.apache.avro.Protocol PROTOCOL = org.apache.avro.Protocol.parse("{\"protocol\":\"OrderAcceptedEvent\",\"namespace\":\"org.kgromov.schema.events\",\"types\":[{\"type\":\"record\",\"name\":\"OrderAcceptedEvent\",\"fields\":[{\"name\":\"orderNumber\",\"type\":\"long\"}]}],\"messages\":{}}");

  @org.apache.avro.specific.AvroGenerated
  public interface Callback extends OrderAcceptedEvent {
    public static final org.apache.avro.Protocol PROTOCOL = org.kgromov.schema.events.OrderAcceptedEvent.PROTOCOL;
  }
}