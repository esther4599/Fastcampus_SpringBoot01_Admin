package com.project.admin.model.network.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserOrderInfoApiResponse {

    private UserApiResponse userApiResponse;

    /*
    private List<OrderGroupApiResponse> orderGroupApiResponseList;
    위 코드도 문제가 있다. user 마다 order group을 갖으므로
    해당 코드는 userApiResponse에 작성한다.

    private List<ItemApiResponse> itemApiResponseList;
    위 코드에는 문제가 있다. order detail은 각 group 마다 존재해야 한다.
    따라서 orderGroupApiResponse에 위의 코드를 입력한다.
    */
}
