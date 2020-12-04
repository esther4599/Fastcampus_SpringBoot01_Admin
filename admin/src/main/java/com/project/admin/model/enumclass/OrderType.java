package com.project.admin.model.enumclass;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@AllArgsConstructor
@Getter
public enum OrderType {
    ALL(0, "일괄", "일괄 배송"),
    EACH(1, "개별", "개별 배송");
    private Integer id;
    private String title;
    private String description;
}
