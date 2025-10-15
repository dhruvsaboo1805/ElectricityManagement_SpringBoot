package com.example.ElectricityMgmt.mappers;

import com.example.ElectricityMgmt.dto.ConsumerRequestDTO;
import com.example.ElectricityMgmt.dto.ConsumerResponseDTO;
import com.example.ElectricityMgmt.entities.Consumer;

public class ConsumerMapper {
    public static Consumer mapToConsumerResponseDTOfromConsumer(ConsumerResponseDTO consumerResponseDTO) {
        Consumer consumer = new Consumer().builder()
                .id(consumerResponseDTO.getId())
                .consumerNumber(consumerResponseDTO.getConsumerNumber())
                .isConnected(consumerResponseDTO.isConnected())
                .build();
        return consumer;
    }

    public static ConsumerResponseDTO mapToConsumerResponseDTO(Consumer consumer) {
        ConsumerResponseDTO consumerResponseDTO = new ConsumerResponseDTO().builder()
                .id(consumer.getId())
                .consumerNumber(consumer.getConsumerNumber())
                .customerId(consumer.getCustomer().getId())
                .isConnected(consumer.isConnected())
                .build();
        return consumerResponseDTO;

    }

    // i will check it some problem is arrising
//    public static Consumer mapToConsumer(ConsumerRequestDTO consumerRequestDTO) {
//        Consumer consumer = new Consumer().builder()
//                .consumerNumber(consumerRequestDTO.getConsumerNumber())
//
//    }
}
