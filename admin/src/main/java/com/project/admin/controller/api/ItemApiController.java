package com.project.admin.controller.api;

import com.project.admin.controller.CRUDController;
import com.project.admin.model.entity.Item;
import com.project.admin.model.network.request.ItemApiRequest;
import com.project.admin.model.network.response.ItemApiResponse;
import com.project.admin.service.ItemApiLogicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;

@RestController
@RequestMapping("/api/item")
public class ItemApiController extends CRUDController<ItemApiRequest, ItemApiResponse, Item> {

}

// 변경전....
//    @Autowired
//    private ItemApiLogicService itemApiLogicService;
//
//    //클래스가 만들어질때 호출되어 의존성을 자동으로 설정
//    @PostConstruct
//    public void init(){
//        this.baseService = itemApiLogicService;
//    }
//}