package com.project.admin.controller.api;

import com.project.admin.controller.CRUDController;
import com.project.admin.model.entity.OrderGroup;
import com.project.admin.model.network.request.OrderGroupApiRequest;
import com.project.admin.model.network.response.OrderGroupApiResponse;
import com.project.admin.service.OrderGroupApiLogicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;

@RestController
@RequestMapping("/api/orderGroup")
public class OrderGroupApiController extends CRUDController<OrderGroupApiRequest, OrderGroupApiResponse, OrderGroup> {
}
//    @Autowired
//    private OrderGroupApiLogicService orderGroupApiLogicService;
//
//    @PostConstruct
//    public void init(){
//        this.baseService = orderGroupApiLogicService;
//    }
//}
