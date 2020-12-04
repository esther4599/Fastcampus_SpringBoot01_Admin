package com.project.admin.model.network.request;

import com.project.admin.model.enumclass.ItemStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ItemApiRequest {

    private Long id;
    private ItemStatus status;
    private String name;
    private String title;
    private String content;
    private BigDecimal price;
    private String brandName;

    private LocalDateTime registeredAt;
    private LocalDateTime unregisteredAt;

    //외래키. 실제 디비에 저장되는 값
    private Long partnerId;
}
