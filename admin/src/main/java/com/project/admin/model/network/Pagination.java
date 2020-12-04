package com.project.admin.model.network;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Accessors(chain = true)
public class Pagination {

    private Integer totalPages; // 화면에 보여줄 총 페이지 수
    private Long totalElement; // 한 페이지에 보여줄 수 있는 사용자 수
    private Integer currentPage; // 현제 페이지 정보
    private Integer currentElement; // 현재 페이지에서 보여주는 총 페이지 수
}

//shift 두번 누르면 class를 찾을 수 있음 -> 해당 클래스로 바로 이동 가능