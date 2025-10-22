package com.example.ElectricityMgmt.mappers;

import com.example.ElectricityMgmt.dto.ConsumerResponseDTO;
import com.example.ElectricityMgmt.entities.Consumer;

public class ConsumerMapper {
//    public static Consumer mapToConsumerResponseDTOfromConsumer(ConsumerResponseDTO consumerResponseDTO) {
//        Consumer consumer = new Consumer().builder()
//                .id(consumerResponseDTO.getId())
//                .consumerNumber(consumerResponseDTO.getConsumerNumber())
//                .isConnected(consumerResponseDTO.isConnected())
//                .connectionType(consumerResponseDTO.getConnectionType())
//                .address(consumerResponseDTO.getAddress())
//                .mobileNumber(consumerResponseDTO.getMobileNumber())
//                .build();
//        return consumer;
//    }

    public static ConsumerResponseDTO mapToConsumerResponseDTO(Consumer consumer) {
        ConsumerResponseDTO consumerResponseDTO = new ConsumerResponseDTO().builder()
                .id(consumer.getId())
                .customerId(consumer.getCustomer().getId())
                .consumerNumber(consumer.getConsumerNumber())
                .connectionType(consumer.getConnectionType())
                .mobileNumber(consumer.getMobileNumber())
                .isConnected(consumer.isConnected())
                .build();
        return consumerResponseDTO;

    }

}
